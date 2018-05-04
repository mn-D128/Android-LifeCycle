package com.m_d128.android_lifecycle

import android.support.v4.app.FragmentTransaction
import android.support.v4.app.FragmentManager.OnBackStackChangedListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var activityId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.v("A", "Activity onCreate " + this.activityId.toString())

        this.activityId = this.intent.getIntExtra(PARAMKEY_ACTIVITY, 0)

        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener(this.backStackChangedListener)

        val transaction: FragmentTransaction? = supportFragmentManager.beginTransaction()
        transaction?.let {
            val fragment: BlankFragment = BlankFragment.newInstance(0)
            it.add(R.id.fragment_root, fragment)
            it.commit()
        }
    }

    override fun onResume() {
        super.onResume()

        Log.v("A", "Activity onResume " + this.activityId.toString())
    }

    override fun onPause() {
        super.onPause()

        Log.v("A", "Activity onPause " + this.activityId.toString())
    }

    override fun onStop() {
        super.onStop()

        Log.v("A", "Activity onStop " + this.activityId.toString())
    }

    override fun onStart() {
        super.onStart()

        Log.v("A", "Activity onStart " + this.activityId.toString())
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        Log.v("A", "Activity onSaveInstanceState " + this.activityId.toString())
    }

    override fun onRestart() {
        super.onRestart()

        Log.v("A", "Activity onRestart " + this.activityId.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.v("A", "Activity onRestoreInstanceState " + this.activityId.toString())
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.v("A", "Activity onDestroy " + this.activityId.toString())
    }

    private val backStackChangedListener: OnBackStackChangedListener = OnBackStackChangedListener {
        val manager = supportFragmentManager
        val fragment = manager.findFragmentById(R.id.fragment_root) as BlankFragment
        fragment.onFragmentResume()
    }

    fun getActivityId(): Int {
        return this.activityId
    }

    companion object {

        @JvmStatic
        val PARAMKEY_ACTIVITY: String = "PARAMKEY_ACTIVITY"

    }

}
