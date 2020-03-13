package com.fangju.mvpsample.user_sample1;

import com.fangju.mvpsample.base.BaseContract;

/**
 * Created by FangJu on 2020/3/13.
 */
public interface UserContract {

    public interface UserView extends BaseContract.BaseView {
        void showSelfInfo(User self);
    }

    public interface Presenter extends BaseContract.BasePresenter<UserView> {
        void getSelfInfo();
    }
}
