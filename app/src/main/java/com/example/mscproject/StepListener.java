package com.example.mscproject;
/*
 Classes using this interface need to use the step attribute
 StepListener class listens for alerts when steps are detected
 */
public interface StepListener {
        void step(long timeNs);
}
