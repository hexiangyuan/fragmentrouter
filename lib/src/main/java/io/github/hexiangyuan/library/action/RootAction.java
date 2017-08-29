package io.github.hexiangyuan.library.action;

/**
 * Created by hexiangyuan on 2017/8/29.
 * Feature:请一定要补全功能
 */

public class RootAction implements Action{
    public String screenName;
    public Object bundle;

    public RootAction(String screenName, Object bundle) {
        this.screenName = screenName;
        this.bundle = bundle;
    }
}
