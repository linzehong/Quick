package com.cvte.game.game.cell;




public class CellData {

	public final static int MAX_CELL_NUM_IN_LINE = 9;//һ�������Ӹ���

	public static int getCellNumInLine(int level) {
		int num = 0;
		num = level + 2;
		if (num > MAX_CELL_NUM_IN_LINE) {
			num = MAX_CELL_NUM_IN_LINE;
		}
		return num;
	}
	
}
