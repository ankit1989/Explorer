package com.explorer.facts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.explorer.R
import com.explorer.facts.model.FactResponse
import com.explorer.facts.model.FactsAdapter
import com.explorer.util.SnackbarUtils
import kotlinx.android.synthetic.main.fragment_facts.*

/**
 * Created by ankitpatel on 3/4/18.
 */
class FactsFragment : Fragment(), FactsContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: FactsContract.Presenter
    private lateinit var adapter:FactsAdapter

    companion object {
        fun newInstance(): FactsFragment {
            return FactsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FactsAdapter(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_facts, container, false)
        progressBar = root.findViewById(R.id.progressBar)

        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        return root
    }

    override fun setPresenter(presenter: FactsContract.Presenter) {
        this.presenter = presenter
    }

    override fun showFacts(factResponse: FactResponse?) {
        if (factResponse != null) {
            if (factResponse.facts != null && !factResponse.facts.isEmpty()) {
                adapter.setFacts(factResponse.facts)
            } else {
                //show no facts view
            }
        }
    }

    override fun showGenericNetworkError() {
        SnackbarUtils.showSnackBar(root_view, getString(R.string.error_something_went_wrong_try_again))
    }

    override fun showUnableToFetchFactsError() {
        SnackbarUtils.showSnackBar(root_view, getString(R.string.error_something_went_wrong_try_again))
    }

    override fun showProgress() {
        if (!progressBar.isShown) {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        if (progressBar.isShown) {
            progressBar.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

}