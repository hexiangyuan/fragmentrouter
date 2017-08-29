package io.github.hexiangyuan.fragmentrouter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.github.hexiangyuan.library.fragmentRouter.FragmentNavigator;
import io.github.hexiangyuan.library.fragmentRouter.FragmentRouter;


public class MainActivity extends AppCompatActivity {
    int index = 1;
    private FragmentRouter router;
    private String screenFormat = "screen_%d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentNavigator navigator = new FragmentNavigator(
                getSupportFragmentManager(),
                R.id.container) {
            @Override
            public Fragment instantiateFragment(String screen, Object Bundle, boolean isRootFragment) {
                SingleFragment singleFragment;
                singleFragment = SingleFragment.newInstance((Integer) Bundle);
                index++;
                return singleFragment;
            }
        };
        router = new FragmentRouter(navigator);
        router.initRootScreen(String.format(screenFormat, index), index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("abcd","onResume");
        router.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("abcd","onPauce");
        router.onPause();
    }

    public void start() {
        router.start(String.format(screenFormat, index), index);
    }

    public void replace() {
        router.replace(String.format(screenFormat, index), index);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            router.back();
        } else {
            super.onBackPressed();
        }
    }
}
