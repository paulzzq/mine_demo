package com.zzq.paul_tools.view.floatwindow;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zzq.paul_tools.R;

/**
 * 
 * @author tr
 * @time 2014-2-25
 * @description ����ɹ�״̬��
 */
public class FloatWindowSuccessView extends LinearLayout{
	

	/**
	 * ��¼С�������Ŀ��
	 */
	public static int viewWidth;

	/**
	 * ��¼С�������ĸ߶�
	 */
	public static int viewHeight;
	
	/**����ɹ�view�Ĳ���*/
	private WindowManager.LayoutParams successViewParams;
	
	private WindowManager windowManager;

	
	public FloatWindowSuccessView(Context context) {
		super(context);
		//��ȡWindowManager����
		windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		LayoutInflater.from(context).inflate(R.layout.floatwindowsuccess, this);
		View view = findViewById(R.id.floatwindowsuccesslayout);
		
		
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
		
		new successViewTask().execute();
		
	}

	/**
	 * ������ɹ��Ĳ������룬���ڸ��·���ɹ���λ��
	 * @param params ����ɹ�view�Ĳ���
	 */
	public void setParams(WindowManager.LayoutParams params){
		successViewParams = params;
	}
	
	class successViewTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				while(successViewParams.y < 0){
						successViewParams.y += 10;
						System.out.println("y:"+successViewParams.y);
						/**����publishProgress(Progress... values)�����½�����Ϣ*/
						publishProgress();
						Thread.sleep(20);
				}
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}

		/**�ڵ���publishProgress(Progress... values)ʱ���˷�����ִ�У�ֱ�ӽ�������Ϣ���µ�UI�����*/
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			windowManager.updateViewLayout(FloatWindowSuccessView.this, successViewParams);
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			MyWindowManager.removeSuccessWindow(getContext());
		}
		
	}

}
