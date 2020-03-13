package com.fangju.mvpsample.proxy;

import com.fangju.mvpsample.base.BaseContract;

/**
 * Created by FangJu on 2020/3/13.
 */
public class IMvpActivityProxyImpl<V extends BaseContract.BaseView> extends IMvpProxyImpl<V>{

    public IMvpActivityProxyImpl(V view) {
        super(view);
    }

    @Override
    public void bindAndCreatePresenter() {
        // 可重写
        super.bindAndCreatePresenter();
    }

    @Override
    public void unbindPresenter() {
        // 可重写
        super.unbindPresenter();
    }
}
