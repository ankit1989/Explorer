package com.explorer.about

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.explorer.R
import com.explorer.util.ActivityUtils

/**
 * Created by ankitpatel on 3/4/18.
 */

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        var aboutFragment = supportFragmentManager.findFragmentById(R.id.aboutContentFrame)
        if (aboutFragment == null) {
            aboutFragment = AboutFragment.newInstance()
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager, aboutFragment, R.id.aboutContentFrame)
        }
    }
}
