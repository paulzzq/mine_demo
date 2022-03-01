package com.zzq.paul_tools.activity;

import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.view.NoticeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: 广告轮播
 * @Author: zzq
 * @Date: 2020-11-26 14:05
 */
public class NoticeViewActivity extends BaseActivity {


    @BindView(R.id.notice_view)
    NoticeView noticeView;

    @Override
    protected int getLayout() {
        return R.layout.activity_notice_view;
    }

    @Override
    protected void init() {

        // 首先，模拟一个公告的集合。需要字符串泛型的list
        final List<String> list = new ArrayList<>();
        list.add("推荐歌曲：Eyelis - 絆にのせて");
        list.add("挺好听的，我听了快100遍了");
        list.add("好想回宿舍打游戏=。=");
        // 然后设置进去。
        noticeView.setNoticeList(list);
        // 这里可以设置一个动画集合，如果不想要动画可以设置成null
        // 不过这里设置动画我设计的不太友好，需要的直接改源码可能更快捷。
//        notice_view.setEnterAnimation(null);
//        notice_view.setExitAnimation(null);

        // 默认动画效果就是渐变和位移，可以通过这个设置动画的时长，默认是1000
//        notice_view.setAnimationDuration(1000);

        // 公告切换一次是3秒，可以通过这个方法设置,设置的比动画的长就好。默认是3000
//        notice_view.setNoticeDuration(2000);

        // 这里就是监听点击事件，TextView是点中的那个公告，position是位置。
        // 比如点击之后想该条公告边灰色，就可以view.setTextColor();实现了
        noticeView.setOnItemClickListener(new NoticeView.OnItemClickListener() {
            @Override
            public void onItemClick(TextView view, int position) {
                String s = list.get(position);
                view.setTextColor(Color.GRAY);
                Toast.makeText(NoticeViewActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        noticeView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        noticeView.pause();
    }
}