package io.github.hexiangyuan.fragmentrouter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log

import io.github.hexiangyuan.library.fragmentRouter.FragmentNavigator
import io.github.hexiangyuan.library.fragmentRouter.FragmentRouter


class MainActivity : AppCompatActivity() {
    internal var index = 1
    private var router: FragmentRouter? = null
    private val screenFormat = "screen_%d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigator = object : FragmentNavigator(
                supportFragmentManager,
                R.id.container) {
            override fun instantiateFragment(screen: String, Bundle: Any, isRootFragment: Boolean): Fragment {
                Log.e("ABCD", "instantiateFragment")
                return HomeFragment()

            }

            override fun customerFragmentTranscation(screen: String, bundle: Any, isRootFragment: Boolean): FragmentTransaction {
                return supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }
        }
        router = FragmentRouter(navigator)
        router!!.initRootScreen(String.format(screenFormat, index), index)
    }

    override fun onResume() {
        super.onResume()
        Log.e("abcd", "onResume")
        router!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        Log.e("abcd", "onPauce")
        router!!.onPause()
    }

    fun start() {
        router!!.start(String.format(screenFormat, index), index)
    }

    fun replace() {
        router!!.replace(String.format(screenFormat, index), index)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            router!!.back()
        } else {
           finish()
        }
    }
}
