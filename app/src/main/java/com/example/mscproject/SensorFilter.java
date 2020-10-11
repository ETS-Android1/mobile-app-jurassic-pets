package com.example.mscproject;

/*
Title: Create a Simple Pedometer and Step Counter in Android
Author: Pillai, A
Date: 2017
Code version: 2.0
Available at: http://www.gadgetsaint.com/android/create-pedometer-step-counter-android/#.X3gxH2hKiUm
*/


// This class determines what is counted as a step being recorded
public class SensorFilter {

        private SensorFilter() { // method
        }
        // sum = method with an array containing float data types
        public static float sum(float[] array) {
            // variable retval (return value) with a float data type
            float retval = 0;
            // iterate through each value in the array
            for (float v : array) {
                retval += v; //retval = retval + the i array
            }
            return retval; //returns the value for retval
        }

        //  static array method = cross: contains two elements arrayA and arrayB with the float data type
        public static float[] cross(float[] arrayA, float[] arrayB) {
            // declares an empty array object with three elements
            float[] retArray = new float[3];
            // assigns values to the elements in the array
            retArray[0] = arrayA[1] * arrayB[2] - arrayA[2] * arrayB[1];
            retArray[1] = arrayA[2] * arrayB[0] - arrayA[0] * arrayB[2];
            retArray[2] = arrayA[0] * arrayB[1] - arrayA[1] * arrayB[0];
            return retArray; //returns the value for thr retval array
        }

        // same as the sum method
        public static float norm(float[] array) {
            float retval = 0;
            for (float v : array) {
                retval += v * v;
            }
            return (float) Math.sqrt(retval); // Math.sqrt: returns the correctly rounded positive square root of a double value
        }

        // static method = dot: contains two elements 'a' and 'b' with the float data type
        public static float dot(float[] a, float[] b) {
            // the retval variable calculates the a and b values for the first 3 arrays
            return a[0] * b[0] + a[1] * b[1] + a[2] * b[2]; //returns the value for retval
        }

        // static method = normalize: contains a float element 'a'
        public static float[] normalize(float[] a) {
            // declare a new retval array object with the length of 'a'
            float[] retval = new float[a.length];
            float norm = norm(a);
            // iterate through each value in the array
            for (int i = 0; i < a.length; i++) {
                retval[i] = a[i] / norm;
            }
            return retval;
        }

}
