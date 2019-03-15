package com.example.modules.util;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public final class FragmentUtility {
    private static FragmentTransaction fragmentTransaction;

    private static FragmentManager getFragmentManager(Activity activity) {
        return ((AppCompatActivity) activity).getSupportFragmentManager();
    }

    public static void addFragment(Activity activity, Fragment fragment, int containerId, boolean isAddToBackStack) {
        String tag = fragment.getClass().getName();
        fragmentTransaction = getFragmentManager(activity).beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        } else fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void replaceFragment(Activity activity, Fragment fragment, int containerId, boolean isAddToBackStack) {
        String tag = fragment.getClass().getName();
        fragmentTransaction = getFragmentManager(activity).beginTransaction();
        Fragment checkAlreadyExistingFragment = getFragmentManager(activity).findFragmentByTag(tag);

        if (checkAlreadyExistingFragment == null) {
            fragmentTransaction.replace(containerId, fragment, tag);
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(tag);
            } else fragmentTransaction.addToBackStack(null);
        } else {
            fragmentTransaction.replace(containerId, checkAlreadyExistingFragment, tag);
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(tag);
            } else fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
