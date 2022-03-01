package com.zzq.paul_tools.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzq.paul_tools.R;

/**
 * @describe  自定义titleView
 * @author zhuzaiqing
 * @time 2018/10/31 16:22
 */
public class TitleView extends RelativeLayout{

	
	/** 容器 */
	public View mContainer;

    /** 居中标题 -- 当显示居中标题时不显示左侧标题 */
    public TextView mTitleCenterTV;
    /** 左侧返回按钮 */
    public ImageView mLeftButtonIV;
    /** 左侧图片 */
    public ImageView mLeftTitleIv;
    /** 左侧标题 */
    public TextView mLeftTitleTv;
    /** 右侧图片 */
    public ImageView mRightButtonIV;
    /** 右侧文字 */
    public TextView mRightButtonTV;
    
    /** 右侧第二个图片按钮 */
    public ImageView mRightTwoIv;
    /** 右侧第二个文字按钮 */
    public TextView mRightTwoTv;

    int background_color;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
    	super(context, attrs);
    	initView(context, attrs);
    }
    
    public TitleView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        initView(context, attrs);
    }
    
    public void setBackgroundResource (int resid) {
    	mContainer.setBackgroundResource(resid);
    }
    
	/**
	 * <li> 初始化 </li>
	 * @param context
	 * @param attrs
	 */
    @SuppressLint("InflateParams")
	private void initView(Context context, AttributeSet attrs) {
		mContainer = LayoutInflater.from(context).inflate(R.layout.title, null);
		addView(mContainer);

        RelativeLayout mrl = (RelativeLayout) mContainer.findViewById(R.id.title_bar);
		mTitleCenterTV = (TextView) mContainer.findViewById(R.id.tv_title);
		mLeftButtonIV = (ImageView) mContainer.findViewById(R.id.iv_title_left_button);
		
		/*标题显示在左侧*/
		mLeftTitleIv = (ImageView) mContainer.findViewById(R.id.iv_title_left);
		mLeftTitleTv = (TextView) mContainer.findViewById(R.id.tv_title_left);
		
		/*右侧显示*/
		mRightButtonIV = (ImageView) mContainer.findViewById(R.id.iv_title_right_button);
		mRightButtonTV = (TextView) mContainer.findViewById(R.id.tv_title_right_button);

		/*右侧第二个按钮区域*/
		mRightTwoIv = (ImageView) mContainer.findViewById(R.id.iv_title_right_button_two);
		mRightTwoTv = (TextView) mContainer.findViewById(R.id.tv_title_right_button_two);
		
		/*TypedArray是一个用来存放由context.obtainStyledAttributes获得的属性的数组*/
		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
		boolean titleLeftBack = typeArray.getBoolean(R.styleable.TitleView_title_left_back, true);
		
		/*标题文字及显示位置*/
		int titleText = typeArray.getResourceId(R.styleable.TitleView_title_name, 0);
		setTitle(
				titleText > 0 ? typeArray.getResources().getText(titleText)
						: typeArray.getString(R.styleable.TitleView_title_name));
		
		/*左侧图标*/
		int leftImg = typeArray.getResourceId(R.styleable.TitleView_title_left_image, 0);
		if (leftImg != 0) {
			setLeftImage(typeArray.getResources().getDrawable(leftImg), titleLeftBack);
		} else {
			mLeftTitleIv.setVisibility(View.GONE);
		}
		
		int leftText = typeArray.getResourceId(R.styleable.TitleView_title_left_text, 0);
		setLeftTitle(
				leftText > 0 ? typeArray.getResources().getText(leftText)
						: typeArray.getString(R.styleable.TitleView_title_left_text), titleLeftBack);
		
		/*右侧文字按钮*/
		int rightText = typeArray.getResourceId(R.styleable.TitleView_title_right_text, 0);
		setRightText(rightText > 0 ? typeArray.getResources().getText(rightText) : typeArray
				.getString(R.styleable.TitleView_title_right_text));

		/*右侧图片按钮*/
		int rightImg = typeArray.getResourceId(R.styleable.TitleView_title_right_image, 0);
		if (rightImg != 0) {
			setRightImage(typeArray.getResources().getDrawable(rightImg));
		}
		
		/*右侧文字按钮--第二个*/
		int rightTextTwo = typeArray.getResourceId(R.styleable.TitleView_title_right_text_two, 0);
		setRightTwoText(rightTextTwo > 0 ? typeArray.getResources().getText(rightTextTwo) : typeArray
				.getString(R.styleable.TitleView_title_right_text_two));

		/*右侧图片按钮--第二个*/
		int rightImgTwo = typeArray.getResourceId(R.styleable.TitleView_title_right_image_two, 0);
		if (rightImgTwo != 0) {
			setRightTwoImage(typeArray.getResources().getDrawable(rightImgTwo));
		}
		
		/*重设 高度*/
		android.view.ViewGroup.LayoutParams params = mContainer.getLayoutParams();
		int titleHeight = typeArray.getDimensionPixelSize(R.styleable.TitleView_title_height, getResources()
				.getDimensionPixelSize(R.dimen.x40));
        background_color = typeArray.getColor(R.styleable.TitleView_background_color, 0);
		params.height = titleHeight;
		mContainer.setLayoutParams(params);
		if(background_color!=0){
            mrl.setBackgroundColor(background_color);
        }
		/*一定要调用，否则这次的设定会对下次的使用造成影响*/
		typeArray.recycle();
	}

    /**
     * <li> 设置标题 </li>
     * @param title
     */
    public void setTitle(int title) {
    	if (title != 0) {
    		mTitleCenterTV.setText(title);
    	}
    }
    
    /**
     * <li> 设置标题 </li>
     * @param title
     */
    public void setTitle(CharSequence title) {
    		mTitleCenterTV.setText(title);
    }
    
    public void setLeftTitle (CharSequence title, boolean titleLeftBack) {
    	 if (!titleLeftBack) {
    		 findViewById(R.id.fl_spot_title).setVisibility(View.VISIBLE);
    		mLeftTitleTv.setText(title);
    	 }
    }

    /**
     * <li> 根据对齐方式设置标题 左---中 </li>
     * @param title
     * @param titleGravity
     */
    @SuppressLint("RtlHardcoded")
	public void setTitle(CharSequence title, int titleGravity) {
        if (titleGravity == Gravity.LEFT) {
        	findViewById(R.id.fl_spot_title).setVisibility(View.VISIBLE);
        	mLeftTitleTv.setText(title);
        } else {
        	mTitleCenterTV.setText(title);
        }
    }
    
    public void setTitleGravity (int gravity) {
    	mTitleCenterTV.setGravity(gravity);
    }
    
    /**
     * <li> 左边图片 </li>
     * @param image
     * @param titleLeftBack
     */
    public void setLeftImage(Drawable image, boolean titleLeftBack) {
    	if (!titleLeftBack) {
    		mLeftTitleTv.setVisibility(View.VISIBLE);
    	} else {
    		mLeftButtonIV.setImageDrawable(image);
    		mLeftButtonIV.setVisibility(View.VISIBLE);
    	}
    }
    
    /**
     * <li> 网络加载图片 </li>
     * @param uri
     */
    /*public void setLeftImageUrl (String uri) {
    	ImageLoader loader = ImageLoader.getInstance();
    	 BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
         bmpOptions.inSampleSize = 12;
         DisplayImageOptions options = new DisplayImageOptions.Builder()
         .decodingOptions(bmpOptions)
         .cacheOnDisc(true)
         .build();
		loader.displayImage(uri, mLeftTitleIv, options);
    }*/
    
    /**
     * <li> 左边本地图片 </li>
     * @param imageSelector
     */
    public void setLeftImage(int imageSelector) {
    	mLeftButtonIV.setImageResource(imageSelector);
    	mLeftButtonIV.setVisibility(View.VISIBLE);
    }

    /**
     * <li> 左边点击返回上一页面 </li>
     * @param activity
     */
    public void setLeftToBack(final Activity activity) {
    	mLeftButtonIV.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	activity.onBackPressed();
            	//KeyboardUtils.hideSoftInput(activity);
            }
        });

    	mLeftButtonIV.setVisibility(View.VISIBLE);
    }
    
    /**
     * <li> 右边显示图片资源id </li>
     * @param image
     */
    public void setRightImage(int image) {
        mRightButtonIV.setImageResource(image);
        mRightButtonIV.setVisibility(View.VISIBLE);
        mRightButtonTV.setVisibility(View.GONE);
    }
    
    /**
     * <li> 右边显示突变drawable  </li>
     * @param image
     */
    public void setRightImage(Drawable image) {
        mRightButtonIV.setVisibility(View.VISIBLE);
        mRightButtonIV.setImageDrawable(image);
        mRightButtonTV.setVisibility(View.GONE);
    }
    
    /**
     * <li> 右边显示图片资源id --two</li>
     * @param image
     */
    public void setRightTwoImage(int image) {
        mRightTwoIv.setImageResource(image);
        mRightTwoIv.setVisibility(View.VISIBLE);
        mRightTwoTv.setVisibility(View.GONE);
    }
    
    /**
     * <li> 右边显示突变drawable --two </li>
     * @param image
     */
    public void setRightTwoImage(Drawable image) {
    	 mRightTwoIv.setImageDrawable(image);
         mRightTwoIv.setVisibility(View.VISIBLE);
         mRightTwoTv.setVisibility(View.GONE);
    }
    
    /**
     * <li> 右边显示文本按钮资源ID </li>
     * @param text
     */
    public void setRightText(int text) {
        mRightButtonTV.setText(text);
        mRightButtonTV.setVisibility(View.VISIBLE);
        mRightButtonIV.setVisibility(View.GONE);
    }
    
    /**
     * <li> 右边显示文本按钮资源ID ---第二个</li>
     * @param text
     */
    public void setRightTwoText (int text) {
   	 mRightTwoTv.setText(text);
   	 mRightTwoTv.setVisibility(View.VISIBLE);
   	 mRightTwoIv.setVisibility(View.GONE);
   }
    
    /**
     * <li> 右边显示文本按钮第二个 </li>
     * @param text
     */
    public void setRightText(CharSequence text) {
        mRightButtonTV.setText(text);
        mRightButtonTV.setVisibility(View.VISIBLE);
        mRightButtonIV.setVisibility(View.GONE);
    }
    
    /**
     * <li> 右边显示文本按钮 </li>
     * @param text
     */
    public void setRightTwoText (CharSequence text) {
    	 mRightTwoTv.setText(text);
    	 mRightTwoTv.setVisibility(View.VISIBLE);
    	 mRightTwoIv.setVisibility(View.GONE);
    }
    
    /**
     * <li> 右边图片按钮是否可见 </li>
     * @param isVisible
     */
    public void setRightImageVisible(boolean isVisible) {
    	if(isVisible) {
    		mRightButtonIV.setVisibility(View.VISIBLE);
    	} else {
    		mRightButtonIV.setVisibility(View.GONE);
    	}
    }
    
    /**
     * <li> 右边第二个图片按钮是否可见 </li>
     * @param isVisible
     */
    public void setRightTwoImageVisible(boolean isVisible) {
    	if(isVisible) {
    		mRightTwoIv.setVisibility(View.VISIBLE);
    	} else {
    		mRightTwoIv.setVisibility(View.GONE);
    	}
    }
    
    /**
     * <li> 右边文字按钮是否可见 </li>
     * @param isVisible
     */
    public void setRightTextVisible(boolean isVisible) {
    	if(isVisible) {
    		mRightButtonTV.setVisibility(View.VISIBLE);
    	} else {
    		mRightButtonTV.setVisibility(View.GONE);
    	}
    }

}
