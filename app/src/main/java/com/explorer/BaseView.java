package com.explorer;

/**
 * Created by ankitpatel on 3/4/18.
 */

public interface BaseView <T extends BasePresenter> {

    void setPresenter(T presenter);

}
