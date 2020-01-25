package sudoku;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	final void testIsNumber() {
		Assert.assertEquals(true, Main.isNumber("100"));
		Assert.assertEquals(true, Main.isNumber("10a"));
		Assert.assertEquals(true, Main.isNumber("4.5"));
	}

	@Test
	final void testMain() {
		String[] a = new String[2];
		String[] b = new String[] {"one",};
		String[] c = new String[]{"one","two","three"};
		
		Main.main(null);
		Main.main(b);
		Main.main(c);
		
		a[0] ="-t";
		Main.main(a);
		a[0] ="0a";
		Main.main(a);
		
		a[0] = "-c";a[1] = "a";
		Main.main(a);
		a[0] = "-c";a[1] = "2.0";
		Main.main(a);
		a[0] = "-c";a[1] = "2ab3";
		Main.main(a);
		a[0] = "-c";a[1] = "-3";
		Main.main(a);
		a[0] = "-c";a[1] = "0";
		Main.main(a);
		a[0] = "-c";a[1] = "1";
		Main.main(a);
		a[0] = "-c";a[1] = "100";
		Main.main(a);
		a[0] = "-c";a[1] = "1000000";
		Main.main(a);
		a[0] = "-c";a[1] = "1000001";
		Main.main(a);
		a[0] = "-s";a[1] = "D:\\\\";
		Main.main(a);
		a[0] = "-s";a[1] = "D:\\\\null.txt";
		Main.main(a);
		a[0] = "-s";a[1] = "puzzle.txt";
		Main.main(a);
	}

}
