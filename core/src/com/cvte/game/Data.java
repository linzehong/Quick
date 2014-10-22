package com.cvte.game;

public class Data {

	public final static int SCREEN_WIDTH = 640;
	public final static int SCREEN_HEIGHT = 960;
	
	
	//�û��ƺ�
	private final static String[] USER_NAMES = {
		"Ϲ��","ɫä","ɫ��","ɫ��","ɫ��","ɫħ","����ɫħ","��̬ɫħ","�¶���ɫ"
	};
	
	public static int mCurLevel = 0;//��ǰ�ؿ�
	public static int mCurScore = 0;//��ǰ����
	
	/**
	 * ����û��ƺ�
	 * @param level
	 * @return
	 */
	public static String getUserName(int level) {
		int index = level>>1;
		if (index > USER_NAMES.length - 1) {
			index = USER_NAMES.length - 1;
		}
		return USER_NAMES[index];
	}
	
}
