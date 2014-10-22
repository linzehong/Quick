package com.cvte.game;

import java.util.Random;

public class ComRandom {

	private static Random mRdm = new Random(System.currentTimeMillis());

	/**
	 * [0,num)
	 * 
	 * @param num
	 * @return
	 */
	public static int getARandom(int num) {
		if (num <= 0) {
			return 0;
		}
		return (Math.abs(mRdm.nextInt() % num));
	}

	/**
	 * [startNum,endNum] endNum-startNum+1
	 * 
	 * @param saveArray
	 * @param startNum
	 * @param endNum
	 */
	public static void getRandomArrayWithNoRepeat(int[] saveArray, int startNum, int endNum) {
		int i;
		int j;
		int data;
		boolean isExisted = false;
		int length = endNum - startNum + 1;
		
		for (i = 0; i < length; i++) {
			saveArray[i] = -1;
		}

		Random rdm = new Random(System.currentTimeMillis());
		for (i = 0; i < length; i++) {
			data = Math.abs(rdm.nextInt()) % length + startNum;
			do {
				isExisted = false;
				for (j = 0; j < length; j++) {
					if (saveArray[j] == data) {
						isExisted = true;
						if (data == startNum) {
							data = endNum;
						}
						else {
							data--;
						}
						break;
					}
				}
			}
			while (isExisted);

			saveArray[i] = data;
		}
	}

}
