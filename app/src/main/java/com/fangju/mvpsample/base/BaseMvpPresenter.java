package com.fangju.mvpsample.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by FangJu on 2020/3/13.
 */
public abstract class BaseMvpPresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {

    private V mView;
    // 为了避免每次获取mView的时候都需要进行一次判空处理，加个代理对象
    private V mProxyView;

    public final V getView() {
        return mProxyView;
    }

    @Override
    public void attach(V view) {
        this.mView = view;
        this.mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (mView == null) {
                    return null;
                }
                return method.invoke(mView, args);
            }
        });
    }

    @Override
    public void detach(V view) {
        this.mView = null;
        this.mProxyView = null;
    }
}
