package sudoku;

public class Sudoku{
	
	private int[][] data = new int[9][9];

	public Sudoku() {
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				this.data[i][j]=0;
	}
	
	public Sudoku(Sudoku sudoku) {
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				this.data[i][j]=sudoku.data[i][j];
	}
	
	public Sudoku(int[][] sudoku) {
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				this.data[i][j]=sudoku[i][j];
	}
	
	public int get(int i,int j) {
		if(i>8||j>8) return -1;
		return data[i][j];
	}
	
	public void set(int num,int i,int j) {
		if(i>8||j>8) return;
		data[i][j]=num;
	}
	
	public void set(int[][] array) {
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				this.data[i][j]=array[i][j];
	}
	
	public void refresh() {
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				this.data[i][j]=0;
	}
	
	
	public String printToFile() {
		StringBuffer sudoBuffer = new StringBuffer();
		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoBuffer.append(String.valueOf(data[i][j]));
				if(j!=8) sudoBuffer.append(" ");
			}
			sudoBuffer.append("\n");
		}
		return sudoBuffer.toString();
	}

}
