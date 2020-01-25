package sudoku;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class SudokuSolverTest {

	@Test
	final void testSolveSudoku() {
		SudokuSolver ss = new SudokuSolver("test2.txt");
		ss.solveSudoku();
		for(int i=0;i<3;i++)
			for(int j=0;j<9;j++) {
				Assert.assertEquals(0, ss.getNumberSet(i, j));
			}	
	}

	@Test
	final void testSearchVacancy() {
		SudokuSolver ss = new SudokuSolver("test1.txt");
		ss.solveSudoku();
		Assert.assertEquals(true, ss.searchVacancy(0, 0));
	}

	@Test
	final void testFindSolution() {
		SudokuSolver ss = new SudokuSolver("test3.txt");
		Assert.assertEquals(false, ss.findSolution(0, 2));
	}

	@Test
	final void testInitNumberSet() {
		SudokuSolver ss = new SudokuSolver("test2.txt");
		ss.readSudokuFile("test2.txt");
		ss.initNumberSet();
		int[][] temp = new int[][]{{4,0,0,0,0,0,0,0,0},{0,0,4,0,0,0,0,0,0},{4,0,0,0,0,0,0,0,0}};
		for(int i=0;i<3;i++)
			for(int j=0;j<9;j++) {
				Assert.assertEquals(temp[i][j], ss.getNumberSet(i, j));
			}	
	}

	@Test
	final void testSetNumberSet() {
		SudokuSolver ss = new SudokuSolver("test2.txt");
		ss.readSudokuFile("test2.txt");
		ss.initNumberSet();
		ss.setNumberSet(0, 2, 3);
		Assert.assertEquals(0, ss.getNumberSet(0, 0));
		Assert.assertEquals(0, ss.getNumberSet(1, 2));
		Assert.assertEquals(0, ss.getNumberSet(2, 0));
	}

	@Test
	final void testFilled() {
		SudokuSolver ss = new SudokuSolver("test2.txt");
		ss.initNumberSet();
		Assert.assertEquals(false, ss.filled(0, 2, 3));
	}

	@Test
	final void testReadSudokuFile() {
		SudokuSolver ss = new SudokuSolver("test2.txt");
		ss.readSudokuFile("test2.txt");
		int[] temp = new int[] {
				2,1,0,7,8,9,4,5,6,
				4,5,6,2,1,3,7,8,9,
				7,8,9,4,5,6,2,1,3,
				3,2,1,9,7,8,6,4,5,
				6,4,5,3,2,1,9,7,8,
				9,7,8,6,4,5,3,2,1,
				1,3,2,8,9,7,5,6,4,
				5,6,4,1,3,2,8,9,7,
				8,9,7,5,6,4,1,3,2};
		for(int i=0;i<81;i++)
			Assert.assertEquals(temp[i], ss.getData()[i]);
		
	}

}
