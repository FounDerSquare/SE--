package sudoku;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class SudokuGeneratorTest {


	@Test
	final void testRefreshSeed() {
	    SudokuGenerator gen=new SudokuGenerator(1);
        int[] temp={2,1,3,4,5,6,7,8,9};
        for(int i=0;i<9;i++)
        	Assert.assertEquals(temp[i],gen.getSeed()[i]);
	}

	@Test
	final void testCreateFirstBlock() {
		SudokuGenerator gen=new SudokuGenerator(1);
		gen.createFirstBlock();
		boolean status = true;
		int numCrit = 0;
		Sudoku su = gen.getSudoku();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				numCrit+=su.get(i, j);
			}
		}
		if(numCrit!=45 || su.get(0, 0)!=2) status = false;
		Assert.assertEquals(true, status);
		
	}



	@Test
	final void testSwap() {
		int[] array = {1,2};
		new SudokuGenerator().swap(array, 0, 1);
		boolean status = (array[0]==2&&array[1]==1);
		Assert.assertEquals(true, status); 
	}

	

}
