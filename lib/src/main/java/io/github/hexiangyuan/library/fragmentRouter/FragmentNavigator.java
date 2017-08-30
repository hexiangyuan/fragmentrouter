package io.github.hexiangyuan.library.fragmentRouter;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import io.github.hexiangyuan.library.INavigator;
import io.github.hexiangyuan.library.action.Action;
import io.github.hexiangyuan.library.action.BackAction;
import io.github.hexiangyuan.library.action.PushAction;
import io.github.hexiangyuan.library.action.ReplaceAction;
import io.github.hexiangyuan.library.action.RootAction;


/**
 * Created by hexiangyuan on 2017/8/28.
 * Feature:请一定要补全功能
 */

public abstract class FragmentNavigator implements INavigator {
    private FragmentManager fragmentManager;
    private
    @IdRes
    int containerResId;

    public FragmentNavigator(FragmentManager fragmentManager, int containerResId) {
        this.fragmentManager = fragmentManager;
        this.containerResId = containerResId;
    }

    @Override
    public void doAction(Action action) {
        if (action instanceof RootAction) {
            String screenName = ((RootAction) action).screenName;
            Object bundle = ((RootAction) action).bundle;
            Fragment fragment = fragmentManager.findFragmentByTag(screenName);
            if (fragment == null) {
                fragment = instantiateFragment(screenName, bundle, true);
                FragmentTransaction fragmentTransaction = customerFragmentTranscation(screenName, bundle, true);
                fragmentTransaction.add(containerResId, fragment, screenName)
                        .addToBackStack(screenName)
                        .commit();
            }
        } else if (action instanceof PushAction) {
            String screenName = ((PushAction) action).screenName;
            Object bundle = ((PushAction) action).bundle;
            Fragment fragment = instantiateFragment(screenName, bundle, false);
            FragmentTransaction fragmentTransaction = customerFragmentTranscation(screenName, bundle, true);
            fragmentTransaction.add(containerResId, fragment)
                    .addToBackStack(screenName)
                    .commit();
        } else if (action instanceof BackAction) {
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            if (backStackEntryCount > 0) {
                destroyFragment();
            }
        } else if (action instanceof ReplaceAction) {
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            if (backStackEntryCount > 0) {
                destroyFragment();
                String screenName = ((ReplaceAction) action).screenName;
                Object bundle = ((ReplaceAction) action).bundle;
                FragmentTransaction fragmentTransaction = customerFragmentTranscation(screenName, bundle, true);
                Fragment fragment = instantiateFragment(screenName, bundle, false);
                fragmentTransaction.add(containerResId, fragment)
                        .addToBackStack(screenName)
                        .commit();
            } else {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                String screenName = ((ReplaceAction) action).screenName;
                Object bundle = ((ReplaceAction) action).bundle;
                Fragment fragment = instantiateFragment(screenName, bundle, false);
                fragmentTransaction.add(containerResId, fragment)
                        .addToBackStack(screenName)
                        .commit();
            }
        }
    }

    public abstract Fragment instantiateFragment(String screen, Object Bundle, boolean isRootFragment);

    public FragmentTransaction customerFragmentTranscation(String screen,Object bundle,boolean isRootFragment){
        return this.fragmentManager.beginTransaction();
    }

    public void destroyFragment() {
        fragmentManager.popBackStack(null, 0);
    }
}
