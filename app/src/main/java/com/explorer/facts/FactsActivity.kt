package com.explorer.facts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.explorer.R
import com.explorer.util.ActivityUtils

/**
 * Created by ankitpatel on 3/4/18.
 */

class FactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        var aboutFragment = supportFragmentManager.findFragmentById(R.id.aboutContentFrame)
        if (aboutFragment == null) {
            aboutFragment = FactsFragment.newInstance()
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager, aboutFragment, R.id.aboutContentFrame)
        }
    }
}
