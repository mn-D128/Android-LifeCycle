package com.m_d128.android_lifecycle

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import android.support.v4.app.FragmentTransaction
import android.util.Log


class BlankFragment : Fragment() {

    private val PARAMKEY_ID: String = "PARAMKEY_ID"

    private var fragmentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.arguments?.let {
            this.fragmentId = it.getInt(PARAMKEY_ID)
        }

        Log.v("A", "Fragment onCreate " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.v("A", "Fragment onCreateView " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())

        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.v("A", "Fragment onViewCreated " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())

        val activityBtn: Button = view.findViewById<Button>(R.id.activityBtn)
        activityBtn.setOnClickListener(this.clickListener)

        val fragmentBtn: Button = view.findViewById<Button>(R.id.fragmentBtn)
        fragmentBtn.setOnClickListener(this.clickListener)

        val fragmentCloseBtn: Button = view.findViewById<Button>(R.id.fragmentCloseBtn)
        fragmentCloseBtn.setOnClickListener(this.clickListener)


        val entryCount: Int = fragmentManager?.backStackEntryCount ?: 0
        fragmentCloseBtn.isEnabled = 0 < entryCount
    }

    override fun onStart() {
        super.onStart()

        Log.v("A", "Fragment onStart " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())
    }

    override fun onStop() {
        super.onStop()

        Log.v("A", "Fragment onStop " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())
    }

    override fun onResume() {
        super.onResume()

        Log.v("A", "Fragment onResume " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())

        this.onFragmentResume()
    }

    override fun onPause() {
        super.onPause()

        Log.v("A", "Fragment onPause " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())
    }

    private val clickListener: View.OnClickListener = View.OnClickListener {
        when {
            it.id == R.id.activityBtn -> this.pushActivity()
            it.id == R.id.fragmentBtn -> this.pushFragment()
            it.id == R.id.fragmentCloseBtn -> this.popFragment()
        }
    }

    private fun pushActivity() {
        val activity: MainActivity = this.activity as MainActivity

        val intent: Intent = Intent(activity, MainActivity::class.java)
        intent.putExtra(MainActivity.PARAMKEY_ACTIVITY, activity.getActivityId() + 1)
        startActivity(intent)
    }

    private fun pushFragment() {
        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        transaction?.let {
            val fragment: BlankFragment = BlankFragment.newInstance(this.fragmentId + 1)
            it.add(R.id.fragment_root, fragment)
            it.addToBackStack(null)
            it.commit()
        }
    }

    private fun popFragment() {
        fragmentManager?.popBackStack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.v("A", "Fragment onAttach " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())
    }

    override fun onDetach() {
        super.onDetach()

        Log.v("A", "Fragment onDetach " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.v("A", "Fragment onDestroy " + (this.activity as MainActivity).getActivityId().toString() + " " + this.fragmentId.toString())
    }

    fun onFragmentResume() {
        val activity: MainActivity = this.activity as MainActivity
        val activityId: Int = activity.getActivityId()

        activity.title = activityId.toString() + " " + this.fragmentId.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
                BlankFragment().apply {
                    arguments = Bundle().apply {
                        putInt(PARAMKEY_ID, id)
                    }
                }
    }

}
