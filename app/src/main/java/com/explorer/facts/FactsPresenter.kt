package com.explorer.facts

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

    init {
        mFactsView.setPresenter(this)
    }

    override fun start() {
        getFacts()
    }

    override fun getFacts() {
        mFactsView.showProgress()
        mAPIService.facts.enqueue(object : Callback<FactResponse> {
            override fun onResponse(call: Call<FactResponse>, response: Response<FactResponse>) {
                mFactsView.hideProgress()
                if (response.isSuccessful) {
                    mFactsView.showFacts(response.body())
                } else {
                    mFactsView.showUnableToFetchFactsError()
                }
            }
            override fun onFailure(call: Call<FactResponse>?, t: Throwable?) {
                mFactsView.hideProgress()
                mFactsView.showGenericNetworkError()
            }
        })
    }


}