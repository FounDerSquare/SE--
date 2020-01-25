package sudoku;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SudokuGenerator {
	private int aimcnt;
	private int nowcnt;
	private Sudoku sudoku,endSudoku;
	private int[] seed = new int[]{2,1,3,4,5,6,7,8,9}; 
	private int[] index={0,1,2,3,4,5,6,7,8,0,1,2,3,4,5,6,7,8};
	private File file;
	
	public int[] getSeed() {return seed;}
	public Sudoku getSudoku() {return sudoku;}
	public Sudoku getEndSudoku() {return endSudoku;}
	
	public SudokuGenerator() {
		// Default
		aimcnt = 0;
		nowcnt = 0;
	}
	public SudokuGenerator(int num) {
		aimcnt = num;
		nowcnt = 0;
		if(sudoku == null) sudoku = new Sudoku();
		else sudoku.refresh();
		if(endSudoku == null) endSudoku = new Sudoku();
		else endSudoku.refresh();
		file = new File("sudoku.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void generateSudoku() {
		refreshSeed(1);
	}
	
	/*
	 * @Title:refreshSeed
	 * @Declaretion:ˢ��seed,ͬʱ���õ�һ������
	 * @Param:cursor�ݹ���ö�λ��
	 * @Return:
	 */
	public void refreshSeed(int cursor) {
		if(cursor==8)
		{
			createFirstBlock();
		}else {
			for (int i = cursor; i <= 8; i++) {
				swap(seed,cursor,i);
				refreshSeed(cursor + 1);
				if (nowcnt == aimcnt) break;
				swap(seed, cursor, i);
			}
		}
	}
	
	/*
	 * @Title:createFirstBlock
	 * @Declaretion:���ɵ�һ��
	 * @Param:
	 * @Return:
	 */
	public void createFirstBlock() {
		int k=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sudoku.set(seed[k++], i, j); 
				for (int l = 0; l < 3; l++) {
					for (int m = 0; m < 3; m++) {
						sudoku.set(sudoku.get(i, j), (i + m) % 3 + 3 * l, (j + l) % 3 + m * 3);
						//sudoku[(i + m) % 3 + 3 * l][(j + l) % 3 + m * 3]=sudoku[i][j]
					}
				}
			}
		}
		displacement(3,5);
	}
	/*
	 * @Title:createWhole
	 * @Declaretion:�ڽ��ɵ�һ���Ļ����ϣ�ͨ�����������滻����ȫ����
	 * @Param:
	 * @Return:
	 */
	public void displacement(int start,int end) {
		int i;
		//�ֶν���ȫ����
		if (start == end) {
			if (end == 5) {
				displacement( 6, 8);
			}else if(end==8){
				displacement( 12, 14);
			}else if(end==14){
				displacement( 15, 17);
			}
			else {
				for(int k=0;k<9;k++) {
					for(int j=0;j<9;j++) {
						endSudoku.set(sudoku.get(index[k], index[j+9]), k, j);
					}
				}	
				printSingleSudoku();
			}
		}
		else {
			for (i = start; i <= end; i++) {
				swap(index, start, i);
				displacement( start + 1, end);
				if (nowcnt == aimcnt) break;
				swap(index, start, i);
			}
		}
	}
	
	/*
	 * @Title:swap
	 * @Declaretion:����������a[i]��a[j]��ֵ
	 * @Param:���飬�±�1���±�2
	 * @Return:
	 */
	public void swap(int[] a,int i,int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/*
	 * @Title:printSingleSudoku
	 * @Declaretion:���ļ�д�뵱ǰ���ɵĵ�������
	 * @Param:
	 * @Return:
	 */
	public void printSingleSudoku() {
		nowcnt++;
		if(nowcnt==1) {//��ǰ���վ������еĵ�һ����������Ҫ��д�ļ�
			try {
				FileWriter fWriter = new FileWriter(file);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				bWriter.write(endSudoku.printToFile()+"\n");
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				FileWriter fWriter = new FileWriter(file,true);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				if(nowcnt==aimcnt)
					bWriter.write(endSudoku.printToFile());
				else
					bWriter.write(endSudoku.printToFile()+"\n");
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	

}
