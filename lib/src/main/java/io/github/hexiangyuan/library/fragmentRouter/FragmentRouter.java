package io.github.hexiangyuan.library.fragmentRouter;


import io.github.hexiangyuan.library.IRouter;
import io.github.hexiangyuan.library.action.Action;
import io.github.hexiangyuan.library.action.BackAction;
import io.github.hexiangyuan.library.action.PushAction;
import io.github.hexiangyuan.library.action.ReplaceAction;
import io.github.hexiangyuan.library.action.RootAction;

/**
 * Created by hexiangyuan on 2017/8/28.
 * Feature:请一定要补全功能
 */
public class FragmentRouter implements IRouter {
    private io.github.hexiangyuan.library.fragmentRouter.FragmentNavigator navigator;
    private boolean hasCancel = false;

    public FragmentRouter(io.github.hexiangyuan.library.fragmentRouter.FragmentNavigator navigator) {
        this.navigator = navigator;
    }


    public void start(String screen) {
        start(screen, null);
    }

    public void initRootScreen(String screen) {
        initRootScreen(screen, null);
    }

    public void initRootScreen(String screen, Object bundle) {
        if (screen == null || screen.equals("")) {
            throw new NullPointerException("Screen name can't be null");
        }
        doAction(new RootAction(screen, bundle));
    }

    public void back() {
        doAction(new BackAction());
    }

    public void replace(String screen, Object object) {
        if (screen == null || screen.equals("")) {
            throw new NullPointerException("Screen name can't be null");
        }
        doAction(new ReplaceAction(screen, object));
    }

    public void start(String screen, Object bundle) {
        if (screen == null || screen.equals("")) {
            throw new NullPointerException("Screen name can't be null");
        }
        PushAction action = new PushAction(screen, bundle);
        doAction(action);
    }


    public void setNavigator(io.github.hexiangyuan.library.fragmentRouter.FragmentNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void doAction(Action action) {
        if (navigator == null) {
            throw new NullPointerException("FragmentNavigator can't be null,you must setNavigator first.");
        }
        if (!hasCancel) {
            navigator.doAction(action);
        }
    }

    public void onResume() {
        hasCancel = false;
    }

    public void onPause() {
        hasCancel = true;
    }
}
