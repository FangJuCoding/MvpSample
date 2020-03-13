package com.fangju.mvpsample.user_sample1;

import com.fangju.mvpsample.base.BaseContract;

/**
 * Created by FangJu on 2020/3/13.
 */
public class UserModel extends BaseContract.BaseModel implements UserContract.UserInfoModel {
    @Override
    public User getSelfInfo() {
        return new User("张三", 20);
    }
}
