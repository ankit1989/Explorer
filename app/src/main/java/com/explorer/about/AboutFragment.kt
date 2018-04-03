package com.explorer.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.explorer.R

/**
 * Created by ankitpatel on 3/4/18.
 */
class AboutFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    companion object {
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_about, container, false)

        recyclerView = root.findViewById(R.id.recyclerView)
        progressBar = root.findViewById(R.id.progressBar)

        return root

    }

}