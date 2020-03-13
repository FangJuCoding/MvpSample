package com.fangju.mvpsample.base;

/**
 * Created by FangJu on 2020/3/13.
 */
public interface BaseContract {

    public interface BaseView {
        void loading();

        void showError(String error);
    }

    public interface BasePresenter<V extends BaseView, M extends BaseModel> {
        void attach(V view);

        void detach(V view);
    }

    public class BaseModel {

    }
}
