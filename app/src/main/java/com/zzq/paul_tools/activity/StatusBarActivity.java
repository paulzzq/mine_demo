package com.zzq.paul_tools.activity;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.view.TitleView;

import butterknife.BindView;

/**
 * @author zhuzaiqing
 * @describe 修改状态栏字体颜色
 * @time 2018/11/2 17:08
 */
public class StatusBarActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleView title;


    @Override
    protected int getLayout() {
        return R.layout.activity_status_bar;
    }

    @Override
    protected void init() {
        initData();
        initView();
    }

    public void initData() {

    }
    public void initView() {
        title.mTitleCenterTV.setText("状态栏文字颜色修改");
        title.setLeftToBack(this);
    }


}
