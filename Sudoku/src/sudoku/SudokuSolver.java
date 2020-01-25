package sudoku;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SudokuSolver {
	private String src;
	private int nowcnt;
	private int aimcnt;
	private Sudoku end;
	private int[][] map;
	private int[] data;
	private int index;
	private int[][] numberSet;
	private final int[] exr2 = new int[]{1,2,4,8,16,32,64,128,256};
	private File foutput;
	
	public int getNumberSet(int i,int j) {return numberSet[i][j];}
	public int[] getData() {return data;}
	
	public SudokuSolver(){
		
	}
	public SudokuSolver(String url){
		src = new String(url);
		nowcnt = 0;
		aimcnt = 0;
		map = new int[9][9];
		end = new Sudoku();
		data=new int[1_000_000*81*2];
		index = 0;
		numberSet = new int[3][9];
		foutput = new File("sudoku.txt");
		if(!foutput.exists()) {
			try {
				foutput.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void solveSudoku() {
		aimcnt = readSudokuFile(src);
		for (nowcnt = 0; nowcnt < aimcnt; nowcnt++) {
			initNumberSet();
			searchVacancy(0,0);
			end.set(map);
			printSingleSudoku();
		}
	}
	
	/*
	 * @Title:searchVacancy
	 * @Declaretion:遍历寻找空位
	 * @Param:
	 * @Return:
	 */
	public boolean searchVacancy(int row,int col) {
		boolean state = false;
		for (; row < 9; row++) {
			for (; col < 9; col++) {
				if (map[row][col] == 0) {
					state = true;
					break;
				}
			}
			if (state)
				break;
			col = 0;
		}
		if (!state) {
			//全图已经没有0了，所有的0解决完毕
			return true;
		}
		
		return findSolution(row, col);//发现row有0，寻找解决方案
	}
	
	/*
	 * @Title:findSolution
	 * @Declaretion:回溯法寻找空位最优解
	 * @Param:
	 * @Return:
	 */
	public boolean findSolution(int row,int col) {
		int val;
		boolean state = false;
		for (int k = 0; k < 9; k++) {
			if(!state) {
				val = k + 1;
				if (filled(row, col, val)) {	//判断周围使用过val,不能填val
					continue;
				}
				map[row][col] = val;
				setNumberSet(row, col, val);
				state = searchVacancy(row, col);
				if (!state) {
					unsetNumberSet(row, col, val);
					map[row][col] = 0;
				}
			}
		}
		return state;
	}
	
	/*
	 * @Title:initNumberSet
	 * @Declaretion:初始化数字记录numberSet，所有数值均未使用
	 * @Param:
	 * @Return:
	 */
	public void initNumberSet() {
		for (int i=0;i<3;i++) {
			//二进制表示刚好9位，每一位都是1,1表示选过，0表示没有选过
			for(int j=0;j<9;j++)
				numberSet[i][j] = exr2[8]*2-1;
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				map[i][j]=data[index++];
				if (map[i][j] != 0) {
					setNumberSet(i , j , map[i][j]);
				}
			}
		}
	}
	
	/*
	 * @Title:setNumberSet
	 * @Declaretion:记录当前数字使用情况，用过标0
	 * @Param:行，列
	 * @Return:
	 */
	public void setNumberSet(int row,int col,int value) {
		int blockId = 3 * (row / 3) + (col / 3);
		numberSet[0][row] = numberSet[0][row] & (~exr2[value - 1]);
		numberSet[1][col] = numberSet[1][col] & (~exr2[value - 1]);
		numberSet[2][blockId] = numberSet[2][blockId]  & (~exr2[value - 1]);
	}
	/*
	 * @Title:unsetNumberSet
	 * @Declaretion:恢复到数字未使用的状态
	 * @Param:行，列
	 * @Return:
	 */
	public void unsetNumberSet(int row,int col,int value) {
		int blockId = 3 * (row / 3) + (col / 3);
		numberSet[0][row] = numberSet[0][row] | exr2[value - 1];
		numberSet[1][col] = numberSet[1][col] | exr2[value - 1];
		numberSet[2][blockId] = numberSet[2][blockId] | exr2[value - 1];
	}
	
	/*
	 * @Title:filled
	 * @Declaretion:检查该行该列该小宫是否填过值value
	 * @Param:
	 * @Return:
	 */
	public boolean filled(int row,int col,int value) {
		int flg = numberSet[0][row] & numberSet[1][col] & numberSet[2][3 * (row / 3) + (col / 3)] & exr2[value - 1];
		if (flg==0) return true;
		else return false;
	}
	
	
	/*
	 * @Title:readSudokuFile
	 * @Declaretion:从文件中读取数独
	 * @Param:输入文件
	 * @Return:
	 */
	public int readSudokuFile(String path) {
		int len = 0;
		int number = 0;
		try (BufferedReader buffer = new BufferedReader(new FileReader(new File(path)))){
			String str = new String();
			str = buffer.readLine();
			while (str != null) {
				boolean flag = true;
				for (int i = 0; i < str.length(); i++) {
					flag = false;
					if (str.charAt(i) == ' ')
						continue;
					if (str.charAt(i) == '0') {
						data[len++] = 0;
					} else
					{
						data[len++] = str.charAt(i) - '0';
					}
				}
				if (flag)
					number++;
				str = buffer.readLine();
			}
		} catch (IOException e) {
			Logger logger=Logger.getLogger("SolveTest");
			logger.setLevel(Level.INFO);
			logger.info(e.getMessage());
		}
		number++;
		return number;
	}
	
	/*
	 * @Title:printSingleSudoku
	 * @Declaretion:向文件写入当前生成的单个数独
	 * @Param:
	 * @Return:
	 */
	public void printSingleSudoku() {
		nowcnt++;
		if(nowcnt==1) {//当前是终局生成中的第一个数独，需要重写文件
			try {
				FileWriter fWriter = new FileWriter(foutput);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				if(nowcnt==aimcnt)
					bWriter.write(end.printToFile());
				else
					bWriter.write(end.printToFile()+"\n");
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				FileWriter fWriter = new FileWriter(foutput,true);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				if(nowcnt==aimcnt)
					bWriter.write(end.printToFile());
				else
					bWriter.write(end.printToFile()+"\n");
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
