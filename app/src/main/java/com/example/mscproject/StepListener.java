package com.example.mscproject;

/*
Title: Create a Simple Pedometer and Step Counter in Android
Author: Pillai, A
Date: 2017
Code version: 2.0
Available at: http://www.gadgetsaint.com/android/create-pedometer-step-counter-android/#.X3gxH2hKiUm
*/


/*
 Classes using this interface need to use the step attribute
 StepListener class listens for alerts when steps are detected
 */
public interface StepListener {
        void step(long timeNs);
}
