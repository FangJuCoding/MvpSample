package com.fangju.mvpsample.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by FangJu on 2020/3/13.
 */
public abstract class BaseMvpActivity<P extends BaseContract.BasePresenter> extends AppCompatActivity
        implements BaseContract.BaseView {
    private P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = createPresenter();
        mPresenter.attach(this);
        initView();
        initData();
    }

    public P getPresenter() {
        return mPresenter;
    }

    // 实例化当前MvpActivity所对应的Presenter
    @NonNull
    protected abstract P createPresenter();

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
        super.onDestroy();
        mPresenter.detach(this);
    }
}
