package com.zzq.paul_tools.view.floatwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zzq.paul_tools.R;

/**
 * ����̨��
 * @author tr
 * @time 2014-2-25
 * @description
 */
public class FloatWindowLauncher extends LinearLayout{

	/**��¼���������Ŀ��*/
	public static int viewWidth;
	/**��¼���������ĸ߶�*/
	public static int viewHeight;
	/**�ؼ�����*/
	private View view;
	/**�жϵ�ǰ�Ƿ���Ҫ�𶯣���Ϊtrue��ʾ��Ҫ�𶯣���Ϊfalse����Ҫ*/
	private boolean vibratorStatus = false;
	/**��ʵ������*/
	private Vibrator vib;
	public FloatWindowLauncher(final Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.floatwindowlauncher, this);
		view = findViewById(R.id.floatwindowlauncherlayout);
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
	}
	
	 /** 
     * ���»������̨����ʾ״̬�����С���������ϵ�����̨�ϣ�����ʾ���䡣 
     */  
    public void updateLauncherStatus(boolean isReadyToLaunch) {  
		if(isReadyToLaunch){
			view.setBackgroundColor(Color.WHITE);
			if(!vibratorStatus){
				openVibrator();
			}
		}else{
			view.setBackgroundColor(Color.parseColor("#232323"));
		}
		vibratorStatus = isReadyToLaunch;
	}
    
	
	/**��С�������뷢��̨��Ӵ���������*/
	@SuppressLint("MissingPermission")
	private void openVibrator(){
			vib = (Vibrator)getContext().getSystemService(getContext().VIBRATOR_SERVICE);
			vib.vibrate(700);
	}
}
