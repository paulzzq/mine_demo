package com.zzq.paul_tools.activity;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.view.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurfaceViewActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleView title;



    @Override
    protected int getLayout() {
        return R.layout.activity_surface_view;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initView();
        initView();
    }

    public void initData() {

    }

    public void initView() {
        title.mTitleCenterTV.setText("涂鸦签名功能");
        title.setLeftToBack(this);
    }
}
