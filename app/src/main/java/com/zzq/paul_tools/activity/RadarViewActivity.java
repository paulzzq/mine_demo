package com.zzq.paul_tools.activity;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.view.RadarView;
import com.zzq.paul_tools.view.TitleView;

import butterknife.BindView;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2018/11/14 9:46
 */
public class RadarViewActivity extends BaseActivity {

    @BindView(R.id.radioview)
    RadarView radioview;
    @BindView(R.id.title)
    TitleView title;


    private double[] data = {200, 100, 60, 60, 60, 60}; //各维度分值

    @Override
    protected int getLayout() {
        return R.layout.activity_radar_view;
    }

    @Override
    protected void init() {
        initView();

    }
    public void initView() {
        title.setLeftToBack(this);
        title.mTitleCenterTV.setText("雷达网格");
        radioview.setData(data);
        radioview.invalidate();
    }





}
