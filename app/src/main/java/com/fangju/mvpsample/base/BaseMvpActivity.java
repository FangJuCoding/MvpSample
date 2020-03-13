package com.fangju.mvpsample.base;

import android.os.Bundle;

import com.fangju.mvpsample.proxy.IMvpActivityProxyImpl;
import com.fangju.mvpsample.proxy.IMvpProxy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by FangJu on 2020/3/13.
 */
public abstract class BaseMvpActivity extends AppCompatActivity
        implements BaseContract.BaseView {
    private IMvpProxy mActivityProxy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mActivityProxy = createActivityProxy();
        mActivityProxy.bindAndCreatePresenter();
        initView();
        initData();
    }

    private IMvpProxy createActivityProxy() {
        return new IMvpActivityProxyImpl<>(this);
    }

    // 初始化数据，交给子类选择复写
    protected void initData() {

    }

    // 初始化视图，交给子类选择复写
    protected void initView() {

    }

    // 获取一个布局id，必须为一个资源id
    protected abstract int getLayoutResId();

    // 加载loading
    @Override
    public void loading() {

    }

    // 显示错误
    @Override
    public void showError(String error) {

    }

    @Override
    protected void onDestroy() {
        mActivityProxy.unbindPresenter();
        super.onDestroy();
    }
}
