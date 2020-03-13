package com.fangju.mvpsample.proxy;

/**
 * Created by FangJu on 2020/3/13.
 */
public interface IMvpProxy {

    void bindAndCreatePresenter();

    void unbindPresenter();
}
