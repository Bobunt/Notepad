package com.example.notepad

import android.webkit.CookieSyncManager.createInstance
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.notepad.data.Item
import com.example.notepad.fragments.AddScroll
import com.example.notepad.fragments.ScrollsFragment

object Router {
    private fun showFragment(currentFragment: Fragment, fm: FragmentManager?, container: Int, addToBackStack: Boolean = true) {
        try {
            if (fm != null) {
                val transaction = fm.beginTransaction()
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                transaction.replace(container, currentFragment, currentFragment::class.java.simpleName)
                if (addToBackStack) transaction.addToBackStack(currentFragment::class.java.simpleName)
                transaction.commit()
            }
        } catch (e: Exception) {
            e
        }
    }

    private fun clearBackStack(fm: FragmentManager?){
        val count = fm?.backStackEntryCount ?: 0
        for (i in 0 until count) {
            fm?.popBackStack()
        }
    }

    private fun showInFragmentMainFragent(fragment: Fragment, fm: FragmentManager?, addToBackStack: Boolean = true){
        showFragment(fragment, fm, R.id.mainFragment, addToBackStack)
    }

    fun showChangeScroll( fm: FragmentManager?, id: String, addToBackStack: Boolean = true,){
        showFragment(AddScroll.createInstance(id), fm, R.id.mainFragment, addToBackStack)
    }

    fun showMainFragmentMain(fm: FragmentManager?){
        clearBackStack(fm)
        showInFragmentMainFragent(ScrollsFragment(), fm, addToBackStack = false)
    }

    fun showAddScroll(fm: FragmentManager?){
        clearBackStack(fm)
        showInFragmentMainFragent(AddScroll(), fm, addToBackStack = false)
    }


}