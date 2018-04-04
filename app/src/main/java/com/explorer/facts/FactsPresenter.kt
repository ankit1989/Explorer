package com.explorer.facts

import android.text.TextUtils
import com.explorer.facts.model.Fact
import com.explorer.facts.model.FactResponse
import com.explorer.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ankitpatel on 3/4/18.
 */
class FactsPresenter (factsView: FactsContract.View) : FactsContract.Presenter {

    private var mFactsView = factsView
    private var mAPIService = ApiUtils.getAPIService()
    private var mFactsResponse:FactResponse? = null
    private var mFirstLoad = true

    init {
        mFactsView.setPresenter(this)
    }

    override fun start() {
        loadFacts(false)
    }

    override fun loadFacts(forceUpdate: Boolean) {
        loadFacts(forceUpdate || mFirstLoad, true)
        mFirstLoad = false
    }

    private fun loadFacts(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (!forceUpdate && mFactsResponse != null) {
            processFacts(mFactsResponse)

        } else {
            if (showLoadingUI)
                mFactsView.showProgress()

            mAPIService.facts.enqueue(object : Callback<FactResponse> {
                override fun onResponse(call: Call<FactResponse>, response: Response<FactResponse>) {
                    if (showLoadingUI)
                        mFactsView.hideProgress()

                    if (response.isSuccessful) {
                        processFacts(response.body())
                    } else {
                        mFactsView.showUnableToFetchFactsError()
                    }
                }

                override fun onFailure(call: Call<FactResponse>?, t: Throwable?) {
                    if (showLoadingUI)
                        mFactsView.hideProgress()

                    mFactsView.showGenericNetworkError()
                }
            })
        }
    }

    private fun processFacts(factsResponse: FactResponse?) {
        if (factsResponse != null) {
            mFactsResponse = factsResponse
            mFactsView.showTitle(factsResponse.title)
            mFactsView.showFacts(removeEmptyFacts(factsResponse.facts))
        }
    }

    override fun removeEmptyFacts(facts: MutableList<Fact>?): MutableList<Fact>? {
        if (facts != null && !facts.isEmpty()) {
            val iterator = facts.iterator()
            while(iterator.hasNext()) {
                val fact = iterator.next()
                if (TextUtils.isEmpty(fact.title) && TextUtils.isEmpty(fact.description)
                        && TextUtils.isEmpty(fact.imageUrl)) {
                    iterator.remove()
                }
            }
        }
        return facts
    }

}