package com.example.mscproject;

/*
Title: Create a Simple Pedometer and Step Counter in Android
Author: Pillai, A
Date: 2017
Code version: 2.0
Available at: http://www.gadgetsaint.com/android/create-pedometer-step-counter-android/#.X3gxH2hKiUm
*/

// This class receives updates from the accelerometer sensor to detect steps
public class StepDetector {

    // final = makes it a fixed value - two variables created
    private static final int ACCEL_RING_SIZE = 50;
    private static final int VEL_RING_SIZE = 10;

    // variable for the step sensitivity threshold
    private static final float STEP_THRESHOLD = 50f;
    // variable for the step delay time
    private static final int STEP_DELAY_NS = 250000000;
    // variable with an int data type
    private int accelRingCounter = 0;

    // three arrays that contain 'ACCEL_RING_SIZE' objects as the values
    private float[] accelRingX = new float[ACCEL_RING_SIZE];
    private float[] accelRingY = new float[ACCEL_RING_SIZE];
    private float[] accelRingZ = new float[ACCEL_RING_SIZE];
    // variable with an int data type
    private int velRingCounter = 0;
    // array that contains the 'VEL_RING_SIZE' object value
    private float[] velRing = new float[VEL_RING_SIZE];
    // variable with a long data type
    private long lastStepTimeNs = 0;
    // variable with a float data type
    private float oldVelocityEstimate = 0;

    // creates a new object using the StepListener.java class
    private StepListener listener;
    // registerListener method with the listener parameter from the StepListener class
    public void registerListener(StepListener listener) {
        this.listener = listener; //---ASK AL-------------------
    }

    // updateAccel method: contains 4 variables in its parameter
    public void updateAccel(long timeNs, float x, float y, float z) {
        // a new array object is created with 3 values
        float[] currentAccel = new float[3];
        currentAccel[0] = x;
        currentAccel[1] = y;
        currentAccel[2] = z;

        // The first step is to update the guess of where the global z vector is
        // accelRingCounter = accelRingCounter + 1
        accelRingCounter++;
        // % = divides 'accelRingCounter' by 'ACCEL_RING_SIZE' and returns remainder
        accelRingX[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[0];
        accelRingY[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[1];
        accelRingZ[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[2];

        // a new float array object with three elements
        float[] worldZ = new float[3];
        /* sum = returns the sum of the values
           Math.min = returns the smaller of two values 'accelRingCounter' and 'ACCEL_RING_SIZE'
           Each element in the array = sum of the SensorFilter divided by the lowest value of the accelRingCounter/ACCEL_RING_SIZE
        */
        worldZ[0] = SensorFilter.sum(accelRingX) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
        worldZ[1] = SensorFilter.sum(accelRingY) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
        worldZ[2] = SensorFilter.sum(accelRingZ) / Math.min(accelRingCounter, ACCEL_RING_SIZE);

        // float variable that uses the 'norm' method from the SensorFilter class
        float normalization_factor = SensorFilter.norm(worldZ); // ------ ASK AL -------------

        // each array element = value divided by 'normalization_factor'
        worldZ[0] = worldZ[0] / normalization_factor;
        worldZ[1] = worldZ[1] / normalization_factor;
        worldZ[2] = worldZ[2] / normalization_factor;

        float currentZ = SensorFilter.dot(worldZ, currentAccel) - normalization_factor; // ------ ASK AL -------------

        // accelRingCounter = accelRingCounter + 1
        velRingCounter++;
        // velRing array = divides 'velRingCounter' by 'VEL_RING_SIZE' and returns remainder to currentZ
        velRing[velRingCounter % VEL_RING_SIZE] = currentZ;

        float velocityEstimate = SensorFilter.sum(velRing);

        /* if the 'velocityEstimate' is greater than the 'STEP_THRESHOLD'
           AND the 'oldVelocityEstimate' is  less than or equal to 'STEP_THRESHOLD'
           AND the 'timeNs' minus 'lastStepTimeNs' is greater than 'STEP_DELAY_NS'
         */
        if (velocityEstimate > STEP_THRESHOLD && oldVelocityEstimate <= STEP_THRESHOLD
                && (timeNs - lastStepTimeNs > STEP_DELAY_NS)) {
            listener.step(timeNs);
            lastStepTimeNs = timeNs;
        }
        // else
        oldVelocityEstimate = velocityEstimate;
    }
}
