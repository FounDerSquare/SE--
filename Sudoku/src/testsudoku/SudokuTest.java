package sudoku;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class SudokuTest {
	
	int[][] a =new int[][] {
		{2,1,3,7,8,9,4,5,6},
		{4,5,6,2,1,3,7,8,9},
		{7,8,9,4,5,6,2,1,3},
		{3,2,1,9,7,8,6,4,5},
		{6,4,5,3,2,1,9,7,8},
		{9,7,8,6,4,5,3,2,1},
		{1,3,2,8,9,7,5,6,4},
		{5,6,4,1,3,2,8,9,7},
		{8,9,7,5,6,4,1,3,2}
	};
	int[][] b =new int[][] {
		{2,1,3,7,8,9,4,6,5},
		{4,5,6,2,1,3,7,9,8},
		{7,8,9,4,5,6,2,3,1},
		{3,2,1,9,7,8,6,5,4},
		{6,4,5,3,2,1,9,8,7},
		{9,7,8,6,4,5,3,1,2},
		{1,3,2,8,9,7,5,4,6},
		{5,6,4,1,3,2,8,7,9},
		{8,9,7,5,6,4,1,2,3}
	};
	
	Sudoku su = new Sudoku(a);

	@Test
	final void testGet() {
		Assert.assertEquals(1, su.get(0, 1));
		Assert.assertEquals(-1, su.get(8, 0));
	}

	@Test
	final void testSetIntIntInt() {
		su.set(0,1,1);
		Assert.assertEquals(0, su.get(1, 1));
		su.set(5,1,1);
		su.set(0,8,0);
		Assert.assertEquals(0, su.get(8, 0));
	}

	@Test
	final void testSetIntArrayArray() {
		su.set(b);
		boolean status = true;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(su.get(i, j)!=b[i][j]) {
					status = false;
					break;
				}
			}
			if(!status) break;
		}
		Assert.assertEquals(true, status);
	}

	@Test
	final void testRefresh() {
		su.refresh();
		boolean status = true;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(su.get(i, j)!=0) {
					status = false;
					break;
				}
			}
			if(!status) break;
		}
		Assert.assertEquals(true, status);
	}

	@Test
	final void testPrintToFile() {
		su.set(a);
		String s = new String("2 1 3 7 8 9 4 5 6\n" + 
				"4 5 6 2 1 3 7 8 9\n" + 
				"7 8 9 4 5 6 2 1 3\n" + 
				"3 2 1 9 7 8 6 4 5\n" + 
				"6 4 5 3 2 1 9 7 8\n" + 
				"9 7 8 6 4 5 3 2 1\n" + 
				"1 3 2 8 9 7 5 6 4\n" + 
				"5 6 4 1 3 2 8 9 7\n" + 
				"8 9 7 5 6 4 1 3 2\n"
		);
		Assert.assertEquals(s,su.printToFile());
	}

}
