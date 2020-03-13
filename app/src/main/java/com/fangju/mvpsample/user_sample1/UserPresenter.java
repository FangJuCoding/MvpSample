package com.fangju.mvpsample.user_sample1;

import com.fangju.mvpsample.base.BaseMvpPresenter;

/**
 * Created by FangJu on 2020/3/13.
 */
public class UserPresenter extends BaseMvpPresenter<UserContract.UserView>
        implements UserContract.Presenter {

    @Override
    public void getSelfInfo() {
        if (getView() == null) {
            return;
        }
        getView().loading();
        User self = new User("张三", 200);

        if (self.getAge() > 100) {
            getView().showError("年龄不可能这么大");
            return;
        }
        getView().showSelfInfo(self);
    }
}
