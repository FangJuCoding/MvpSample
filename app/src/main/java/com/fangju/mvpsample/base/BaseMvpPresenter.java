package com.fangju.mvpsample.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by FangJu on 2020/3/13.
 */
public abstract class BaseMvpPresenter<V extends BaseContract.BaseView, M extends BaseContract.BaseModel>
        implements BaseContract.BasePresenter<V, M> {

    private V mView;
    // 为了避免每次获取mView的时候都需要进行一次判空处理，加个代理对象
    private V mProxyView;

    private M mModel;

    public final V getView() {
        return mProxyView;
    }

    public final M getModel() {
        return mModel;
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

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        if (type != null) {
            // 次数组代表泛型的个数，从左到右
            Type[] arguments = type.getActualTypeArguments();
            if (arguments.length > 0) {
                try {
                    this.mModel = (M) ((Class) arguments[1]).newInstance();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void detach(V view) {
        this.mView = null;
        this.mProxyView = null;
    }
}
