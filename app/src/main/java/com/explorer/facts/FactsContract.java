package com.explorer.facts;

import com.explorer.BasePresenter;
import com.explorer.BaseView;
import com.explorer.facts.model.FactResponse;

import java.util.List;

/**
 * Created by ankitpatel on 3/4/18.
 */

public interface FactsContract {

    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void showFacts(FactResponse factResponse);
        void showNoFactsView();
        void showGenericNetworkError();
        void showUnableToFetchFactsError();
    }

    interface Presenter extends BasePresenter {
        void loadFacts(boolean forceUpdate);
    }

}
