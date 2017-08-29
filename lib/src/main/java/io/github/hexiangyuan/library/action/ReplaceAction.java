package io.github.hexiangyuan.library.action;

/**
 * Created by hexiangyuan on 2017/8/29.
 * Feature:请一定要补全功能
 */

public class ReplaceAction implements Action {
    public String screenName;
    public Object bundle;

    public ReplaceAction(String screen, Object bundle) {
        this.screenName = screen;
        this.bundle = bundle;
    }
}
