package com.fangju.mvpsample.user_sample1;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fangju.mvpsample.R;
import com.fangju.mvpsample.base.BaseMvpActivity;
import com.fangju.mvpsample.inject.InjectPresenter;

public class UserActivity extends BaseMvpActivity
        implements UserContract.UserView, View.OnClickListener {
    private Button mStartBtn;
    private TextView mShowTv;

    @InjectPresenter
    UserPresenter mUserPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
        super.initView();
        mStartBtn = findViewById(R.id.start_btn);
        mShowTv = findViewById(R.id.show_tv);

        mStartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mStartBtn == v) {
            mUserPresenter.getSelfInfo();
        }
    }

    @Override
    public void showSelfInfo(User self) {
        mShowTv.setText(self.toString());
    }

    @Override
    public void showError(String error) {
        super.showError(error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
