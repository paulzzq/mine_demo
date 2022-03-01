package com.zzq.paul_tools.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.utils.DisplayUtil;
import com.zzq.paul_tools.view.CenterAlignImageSpan;
import com.zzq.paul_tools.view.CheckView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhuzaiqing
 * @describe 自定义textview  让第一行最左侧显示一个图标
 * @time 2019/12/4 9:40
 */
public class TextViewActivity extends BaseActivity {


    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayout() {
        return R.layout.activity_text_view;
    }

    @Override
    protected void init() {
        initView();

    }

    public void initView() {
        String str1 = "  ";
        String str2 = "我爱我的祖国，希望祖国繁荣昌盛，" + "其实大家都生活不错的，就很不错的了";
        String str = str1 + str2;
        SpannableString spannableString = new SpannableString(str);
        Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
        int iconwidth = DisplayUtil.dip2px(this, 25);//27
        int iconHeight = DisplayUtil.dip2px(this, 16);//15

        d.setBounds(0, 0, iconwidth, iconHeight);
        //居中对齐imageSpan
        CenterAlignImageSpan imageSpan1 = new CenterAlignImageSpan(d);
        spannableString.setSpan(imageSpan1, 0, 1, ImageSpan.ALIGN_BASELINE);
        tv.setLineSpacing(12f, 1);
        tv.setText(spannableString);
    }
}
