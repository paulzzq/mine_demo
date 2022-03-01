package com.zzq.paul_tools.view.floatwindow;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzq.paul_tools.R;

/**
 * 
 * @author tr
 * @time 2014-2-17
 * @description С����������
 */
public class FloatWindowSmallView extends LinearLayout {
	/**
	 * ��¼С�������Ŀ��
	 */
	public static int viewWidth;

	/**
	 * ��¼С�������ĸ߶�
	 */
	public static int viewHeight;
	/**
	 * ��¼ϵͳ״̬���ĸ߶�
	 */
	private static int statusBarHeight;
	/**
	 * ���ڸ���С��������λ��
	 */
	private WindowManager windowManager;
	 /** 
     * С�������Ĳ��� 
     */  
    private WindowManager.LayoutParams mParams;  
  
    /** 
     * ��¼��ǰ��ָλ������Ļ�ϵĺ�����ֵ 
     */  
    private float xInScreen;  
  
    /** 
     * ��¼��ǰ��ָλ������Ļ�ϵ�������ֵ 
     */  
    private float yInScreen;  
  
    /** 
     * ��¼��ָ����ʱ����Ļ�ϵĺ������ֵ 
     */  
    private float xDownInScreen;  
  
    /** 
     * ��¼��ָ����ʱ����Ļ�ϵ��������ֵ 
     */  
    private float yDownInScreen;  
  
    /** 
     * ��¼��ָ����ʱ��С��������View�ϵĺ������ֵ 
     */  
    private float xInView;  
  
    /** 
     * ��¼��ָ����ʱ��С��������View�ϵ��������ֵ 
     */  
    private float yInView;  
    
    /**С����������*/
    private View view;

	public FloatWindowSmallView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		//��ȡWindowManager����
		windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		//��ȡ�����ļ�
		LayoutInflater.from(context).inflate(R.layout.floatwindowsmall, this);
		view = findViewById(R.id.smallwindowlayout);
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
		TextView percentView = (TextView) findViewById(R.id.percent);
		percentView.setText(MyWindowManager.getUsedPercentValue(context));

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//��ָ����ʱ��¼��Ҫ���ݣ��������ֵ����Ҫ��ȥ״̬���ĸ߶�
				xInView = event.getX();
				yInView = event.getY();
				
				
				xDownInScreen = event.getRawX();
				yDownInScreen = event.getRawY() - getStatusBarHeight();
				
				xInScreen = event.getRawX();
				yInScreen = event.getRawY() - getStatusBarHeight();
				//������������޸Ĵ��屳����ɫ
				view.setBackgroundColor(Color.GREEN);
				break;
			case MotionEvent.ACTION_MOVE:
				xInScreen = event.getRawX();  
	            yInScreen = event.getRawY() - getStatusBarHeight();  
	            // ��ָ�ƶ���ʱ�����С��������λ��  
	            updateViewPosition();  
	            
	            if(!MyWindowManager.isLauncherWindowShowing()){
		            openLauncherWindow();
	            }
				break;
			case MotionEvent.ACTION_UP:
				
				if(MyWindowManager.isReadyToLaunch()){
					new launchTask().execute();
				}
				
				// �����ָ�뿪��Ļʱ��xDownInScreen��xInScreen��ȣ���yDownInScreen��yInScreen��ȣ�����Ϊ�����˵����¼���  
	            if (xDownInScreen == xInScreen && yDownInScreen == yInScreen) {  
	                openBigWindow();  
	            }  
				removeLauncherWindow();
				break;

		}
		return true;
	}
	
	/**
	 * ��С�������Ĳ������룬���ڸ���С��������λ��
	 * @param params С�������Ĳ���
	 */
	public void setParams(WindowManager.LayoutParams params){
		mParams = params;
	}
	
	/**����С����������Ļ�е�λ��*/
	private void updateViewPosition(){
		mParams.x = (int)(xInScreen - xInView);
		mParams.y = (int)(yInScreen - yInView);
		windowManager.updateViewLayout(this, mParams);
		MyWindowManager.updateLauncher();
	}
	
	/**�򿪴���������ͬʱ�ر�С������*/
	private void openBigWindow(){
		MyWindowManager.createBigWindow(getContext());
		MyWindowManager.removeSmallWindow(getContext());
	}
	
	/**�򿪳ɹ�����view��ͬʱ�ر�С������*/
	private void openSuccessWindow(){
		MyWindowManager.createSuccessWindow(getContext());
		MyWindowManager.removeSmallWindow(getContext());
	}
	/**�򿪷���̨view*/
	private void openLauncherWindow(){
		MyWindowManager.createLauncherWindow(getContext());
	}
	
	/**�Ƴ�����̨view*/
	private void removeLauncherWindow(){
		MyWindowManager.removeLauncherWindow(getContext());
	}
	
	
	class launchTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			int height = 10;
			while(mParams.y > 0){
				mParams.y = mParams.y - height;
				/**����publishProgress(Progress... values)�����½�����Ϣ*/
				publishProgress();
				try{
					height =+ 10;
					Thread.sleep(6);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			return null;
		}

		/**�ڵ���publishProgress(Progress... values)ʱ���˷�����ִ�У�ֱ�ӽ�������Ϣ���µ�UI�����*/
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			windowManager.updateViewLayout(FloatWindowSmallView.this, mParams);
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			mParams.x = (int) (xDownInScreen - xInView);  
	        mParams.y = (int) (yDownInScreen - yInView);  
			windowManager.updateViewLayout(FloatWindowSmallView.this, mParams);
			openSuccessWindow();
		}
		
		
		
	}
	
	/**
	 * ���ڻ�ȡ״̬���ĸ߶�
	 * @return ����״̬���߶ȵ�����ֵ
	 */
	private int getStatusBarHeight(){
		if(statusBarHeight == 0){
			try{
				Class<?> c = Class.forName("com.android.internal.R$dimen");
				Object o = c.newInstance();
				Field field = c.getField("status_bar_height");
				int x = (Integer) field.get(o);
				statusBarHeight = getResources().getDimensionPixelSize(x);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return statusBarHeight;
	}

}
