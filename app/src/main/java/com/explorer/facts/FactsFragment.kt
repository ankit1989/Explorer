package com.explorer.facts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.explorer.R
import com.explorer.facts.model.Fact
import com.explorer.facts.model.FactsAdapter
import com.explorer.util.SnackbarUtils
import com.explorer.widgets.ScrollChildSwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_facts.*

/**
 * Created by ankitpatel on 3/4/18.
 */
class FactsFragment : Fragment(), FactsContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var noContentTextView:TextView
    private lateinit var swipeRefreshLayout: ScrollChildSwipeRefreshLayout
    private lateinit var presenter: FactsContract.Presenter
    private lateinit var adapter:FactsAdapter
    private var isSwipeRefreshActive = false

    companion object {
        fun newInstance(): FactsFragment {
            return FactsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FactsAdapter(presenter)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_facts, container, false)
        progressBar = root.findViewById(R.id.progressBar)
        noContentTextView = root.findViewById(R.id.noContentTextView)

        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        val divider = DividerItemDecoration(context, LinearLayout.VERTICAL)
        recyclerView.addItemDecoration(divider)

        presenter.loadFacts(false)

        // Set up progress indicator
        swipeRefreshLayout = root.findViewById<ScrollChildSwipeRefreshLayout>(R.id.refresh_layout)
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(context!!, R.color.colorPrimary),
                ContextCompat.getColor(context!!, R.color.colorAccent),
                ContextCompat.getColor(context!!, R.color.colorPrimaryDark)
        )
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(recyclerView)
        swipeRefreshLayout.setOnRefreshListener({
            isSwipeRefreshActive = true
            presenter.loadFacts(true)
        })

        return root
    }

    override fun setPresenter(presenter: FactsContract.Presenter) {
        this.presenter = presenter
    }

    override fun showFacts(facts: List<Fact>?) {
        if (view == null) {
            return;
        }

        if (facts != null && !facts.isEmpty()) {
            adapter.setFacts(facts)
        } else {
            showNoFactsView()
        }
    }

    override fun showTitle(title: String?) {
        updateActionBarTitle(title)
    }

    override fun showNoFactsView() {
        noContentTextView.visibility = View.VISIBLE
    }

    override fun showGenericNetworkError() {
        SnackbarUtils.showSnackBar(root_view, getString(R.string.error_something_went_wrong_try_again))
    }

    override fun showUnableToFetchFactsError() {
        SnackbarUtils.showSnackBar(root_view, getString(R.string.error_something_went_wrong_try_again))
    }

    override fun showProgress() {
        if (!isSwipeRefreshActive && !progressBar.isShown) {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        if (isSwipeRefreshActive) {
            isSwipeRefreshActive = false
            swipeRefreshLayout.post({
                swipeRefreshLayout.isRefreshing = false
            })
        }

        if (progressBar.isShown) {
            progressBar.visibility = View.GONE
        }
    }

    private fun updateActionBarTitle (title: String? = getString(R.string.app_name)) {
        val actionBarTitle = if (!TextUtils.isEmpty(title)) title else getString(R.string.app_name)
        /**
         * TODO: Change this to use listener pattern using onAttach method to communicate with
         * activity to update the title
         *
         * Also as per requirement spec doc should be using toolbar instead of Action bar
         */
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.title = actionBarTitle
        }

    }

}