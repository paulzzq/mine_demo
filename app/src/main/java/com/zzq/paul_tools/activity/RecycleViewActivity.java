package com.zzq.paul_tools.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.adapter.NormalAdapter;
import com.zzq.paul_tools.bean.MainToolBean;
import com.zzq.paul_tools.utils.SimpleItemTouchCallback;
import com.zzq.paul_tools.view.HeaderRecyclerView;
import com.zzq.paul_tools.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RecycleViewActivity extends BaseActivity implements NormalAdapter.OnclicListener {

    @BindView(R.id.title)
    TitleView title;
    @BindView(R.id.recycleview)
    HeaderRecyclerView recyclerView;
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.edit_btn)
    Button editBtn;
    @BindView(R.id.ll)
    LinearLayout ll;

    private String name;
    public final int TYPE_1 = 1;
    public final int TYPE_2 = 2;

    //private QuickAdapter recycleAdapter;
    private NormalAdapter recycleAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_recycle_view;
    }

    @Override
    protected void init() {
        initData();
        initView();
    }

    public void initData() {
        if (getIntent() != null) {
            name = getIntent().getStringExtra("data");
        }
    }

    public void initView() {
        title.setLeftToBack(this);
        title.mTitleCenterTV.setText(name);
        List<MainToolBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MainToolBean bean1 = new MainToolBean();
            bean1.setName("測試" + i);
            bean1.setBitmap(R.mipmap.ic_launcher);
            list.add(bean1);
        }
        recycleAdapter = new NormalAdapter(list, this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

//        NormalAdapterWrapper newAdapter = new NormalAdapterWrapper(recycleAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_layout, ll, false);
//        View view = LayoutInflater.from(this).inflate()
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_layout, ll, false);
//        newAdapter.addFooterView(footerView);
//        newAdapter.addHeaderView(headerView);


//         recycleAdapter = new QuickAdapter<MainToolBean>(list) {
//            @Override
//            public int getLayoutId(int viewType) {
//                return R.layout.adapter_grid_item;
//            }
//
//            @Override
//            public int getItemViewType(int position) {
//                if (position % 2 == 0) {
//                    return TYPE_1;
//                } else {
//                    return TYPE_2;
//                }
//            }
//
//            @Override
//            public void convert(ViewHolder holder, MainToolBean data, int position) {
//                int type = getItemViewType(position);
//                switch (type) {
//                    case TYPE_1:
//                        holder.setText(R.id.title_tv, data.getName());
//                        holder.setDrawble(R.id.photo_iv, data.getBitmap());
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(RecycleViewActivity.this, "onClick", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                            @Override
//                            public boolean onLongClick(View view) {
//                                Toast.makeText(RecycleViewActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();
//                                return true;
//                            }
//                        });
//
//                        break;
//
//                    case TYPE_2:
//                        holder.setText(R.id.title_tv, "你好么？");
//                        holder.setDrawble(R.id.photo_iv, data.getBitmap());
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(RecycleViewActivity.this, "onClick", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                            @Override
//                            public boolean onLongClick(View view) {
//                                Toast.makeText(RecycleViewActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();
//                                return true;
//                            }
//                        });
//                        break;
//                }
//
//
//            }
//        };


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);

        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager = new GridLayoutManager(this, 3);
        //设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //设置为垂直布局，这也是默认的
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //解决局部刷新闪屏问题
//        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        //设置普通 adapter
//        recyclerView.setAdapter(recycleAdapter);
        //带头部尾部

        recyclerView.addHeaderView(headerView);
        recyclerView.addFooterView(footerView);
        recyclerView.setAdapter(recycleAdapter);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //设置分隔线
//         recyclerView.addItemDecoration( new DividerGridItemDecoration(this ));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchCallback(recycleAdapter, list));
        helper.attachToRecyclerView(recyclerView);


    }

    @OnClick({R.id.add_btn, R.id.delete_btn, R.id.edit_btn})
    void onclick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                recycleAdapter.addNewItem();
                // 由于Adapter内部是直接在首个Item位置做增加操作，增加完毕后列表移动到首个Item位置
                mLayoutManager.scrollToPosition(0);
                break;
            case R.id.delete_btn:
                recycleAdapter.deleteItem();
                // 由于Adapter内部是直接在首个Item位置做删除操作，删除完毕后列表移动到首个Item位置
                mLayoutManager.scrollToPosition(0);
                break;
            case R.id.edit_btn:
                recycleAdapter.updateItem();
                mLayoutManager.scrollToPosition(0);
                break;
        }
    }

    @Override
    public void OnItemOnClick() {
        Log.e("eee", "--OnItemOnClick");

    }

    @Override
    public void OnLongOnClick() {
        Log.e("eee", "--OnLongOnClick");


    }
}
