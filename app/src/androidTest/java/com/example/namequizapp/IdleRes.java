package com.example.namequizapp;


import androidx.test.espresso.IdlingResource;

public class IdleRes {
    private IdlingResource.ResourceCallback callback;


    public String getName() {
        return IdleRes.class.getName();
    }


    public boolean isIdleNow() {
        callback.onTransitionToIdle();
        return true;
    }


    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
        this.callback = callback;
    }
}
