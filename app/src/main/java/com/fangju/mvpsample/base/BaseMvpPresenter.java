package com.fangju.mvpsample.base;

/**
 * Created by FangJu on 2020/3/13.
 */
public abstract class BaseMvpPresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {

    private V mView;

    public final V getView() {
        return mView;
    }

    @Override
    public void attach(V view) {
        this.mView = view;
    }

    @Override
    public void detach(V view) {
        this.mView = null;
    }
}
