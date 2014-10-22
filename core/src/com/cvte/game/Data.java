package com.cvte.game;

public class Data {

	public final static int SCREEN_WIDTH = 640;
	public final static int SCREEN_HEIGHT = 960;
	
	
	//用户称号
	private final static String[] USER_NAMES = {
		"瞎子","色盲","色郎","色狼","色鬼","色魔","超级色魔","变态色魔","孤独求色"
	};
	
	public static int mCurLevel = 0;//当前关卡
	public static int mCurScore = 0;//当前分数
	
	/**
	 * 获得用户称号
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
