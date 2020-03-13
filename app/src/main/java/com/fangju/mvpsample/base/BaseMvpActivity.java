package com.fangju.mvpsample.base;

import android.os.Bundle;

import com.fangju.mvpsample.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by FangJu on 2020/3/13.
 */
public abstract class BaseMvpActivity extends AppCompatActivity
        implements BaseContract.BaseView {
    private List<BaseMvpPresenter> mPresenterList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        injectPresenter();
        initView();
        initData();
    }

    private void injectPresenter() {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                Class<? extends BaseMvpPresenter> presenter = null;
                // 判断是否是继承自BasePresenter
//                if (field.getType().isAssignableFrom(BaseMvpPresenter.class)) {
                    presenter = (Class<? extends BaseMvpPresenter>) field.getType();
                    BaseMvpPresenter baseMvpPresenter = null;
                    try {
                        baseMvpPresenter = presenter.newInstance();
                        baseMvpPresenter.attach(this);
                        field.setAccessible(true);
                        field.set(this, baseMvpPresenter);
                        mPresenterList.add(baseMvpPresenter);
                    } catch (IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
//                }
            }
        }
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
        super.onDestroy();
        for (BaseMvpPresenter baseMvpPresenter : mPresenterList) {
            baseMvpPresenter.detach(this);
        }
    }
}
