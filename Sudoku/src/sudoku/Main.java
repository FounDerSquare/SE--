package sudoku;

import java.io.File;

public class Main {

	public static boolean isNumber(String str){
		String reg = "^[0-9]+?$";
		return str.matches(reg);
		}
	
	public static void main(String[] argv) {
		long startTime=System.currentTimeMillis();
		int argc = argv.length;
		if(argc!=2)	//指令长度有误，指令不合法 
		{
			System.out.println("Argument amount error.");
		}
		else {
			if(argv[0].compareTo("-c")==0||argv[0].compareTo("-C")==0) {
				boolean bl = isNumber(argv[1]);
				if(!bl) {
					System.out.println("Argument is invalid!");
				}
				else {
					int num = Integer.parseInt(argv[1]);
					if(num > 1000000 || num < 1)
						System.out.println("Invalid amount!");
					else {
						try
						{
							new SudokuGenerator(num).generateSudoku();
							System.out.println(num+" sudokus have been generated.");
						}
						catch(Exception e)
						{
							System.out.println("Main receives error in SudokuGenerator: "+e.getMessage()); 
						}
					}
				}
			}
			else if(argv[0].compareTo("-s")==0||argv[0].compareTo("-S")==0) {
				File f = new File(argv[1]);
				if(!f.exists()||!f.isFile())
					System.out.println("URL is invalid!");
				else
					new SudokuSolver(argv[1]).solveSudoku();
			}
			else {//输入指令无效
				System.out.println("Command is invalid!");
			}
		}
		long endTime=System.currentTimeMillis();
		System.out.println("Total time used: "+(float)(endTime-startTime)/1000+"s");
	}
}
