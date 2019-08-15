package com.stonetree.imdbnews.core.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.stonetree.imdbnews.R

inline fun <reified F : Fragment> launchFragmentScenario(
    bundle: Bundle?,
    fragment: F,
    navController: NavController
): FragmentScenario<F>
{
    return launchFragmentInContainer(bundle, R.style.Theme_AppCompat) {
        fragment.also { fragment ->
            fragment.viewLifecycleOwnerLiveData.observeForever { lifeCycleOwner ->
                if (lifeCycleOwner != null) {
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }
    }
}