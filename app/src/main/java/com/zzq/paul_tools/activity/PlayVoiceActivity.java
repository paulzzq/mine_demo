package com.zzq.paul_tools.activity;

import android.view.View;
import android.widget.Button;
import com.zzq.paul_tools.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhuzaiqing
 * @describe 自定义播放声音控件
 * @time 2019/1/7 16:30
 */
public class PlayVoiceActivity extends BaseActivity {

    @BindView(R.id.close_btn)
    Button closeBtn;
    @BindView(R.id.open_btn)
    Button openBtn;
    @Override
    protected int getLayout() {
        return R.layout.activity_play_voice;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        try {
//            InputStream in=getAssets().open("");
//            Bitmap bitmap=BitmapFactory.decodeStream(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @OnClick({R.id.close_btn, R.id.open_btn})
    void onclick(View v) {
        switch (v.getId()) {
            case R.id.close_btn:
                break;
            case R.id.open_btn:
                break;
        }
    }


}
