package com.fangju.mvpsample.proxy;

import com.fangju.mvpsample.base.BaseContract;
import com.fangju.mvpsample.base.BaseMvpPresenter;
import com.fangju.mvpsample.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FangJu on 2020/3/13.
 */
public class IMvpProxyImpl<V extends BaseContract.BaseView> implements IMvpProxy {
    private V mView;
    private List<BaseContract.BasePresenter> mPresenterList;

    public IMvpProxyImpl(V view) {
        this.mView = view;
        mPresenterList = new ArrayList<>();
    }

    @Override
    public void bindAndCreatePresenter() {
        Field[] declaredFields = mView.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                Class<? extends BaseContract.BasePresenter> presenter = null;
                // 判断是否是继承自BasePresenter
//                if (field.getType().isAssignableFrom(BaseMvpPresenter.class)) {
                presenter = (Class<? extends BaseContract.BasePresenter>) field.getType();
                BaseContract.BasePresenter baseMvpPresenter = null;
                try {
                    baseMvpPresenter = presenter.newInstance();
                    baseMvpPresenter.attach(mView);
                    field.setAccessible(true);
                    field.set(mView, baseMvpPresenter);
                    mPresenterList.add(baseMvpPresenter);
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
//                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        for (BaseContract.BasePresenter basePresenter : mPresenterList) {
            basePresenter.detach(mView);
        }
        mView = null;
    }
}
