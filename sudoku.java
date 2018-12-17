package sudoku;

public class sudoku {
	
	private static int[][] SUDOKU_ORIGINAL={
											{8,0,0,0,0,0,0,0,0},
											{0,0,3,6,0,0,0,0,0},
											{0,7,0,0,9,0,2,0,0},
											{0,5,0,0,0,7,0,0,0},
											{0,0,0,0,4,5,7,0,0},
											{0,0,0,1,0,0,0,3,0},
											{0,0,1,0,0,0,0,6,8},
											{0,0,8,5,0,0,0,1,0},
											{0,9,0,0,0,0,4,0,0}
											};
	
	
	private static int[][] SUDOKU_SOLUTION={
											{8,1,2,7,5,3,6,4,9},
											{9,4,3,6,8,2,1,7,5},
											{6,7,5,4,9,1,2,8,3},
											{1,5,4,2,3,7,8,9,6},
											{3,6,9,8,4,5,7,2,1},
											{2,8,7,1,6,9,5,3,4},
											{5,2,1,9,7,4,3,6,8},
											{4,3,8,5,2,6,9,1,7},
											{7,9,6,3,1,8,4,5,2}
											};
	
	private static int MAX_VALUE = 9;
	private int[][] board;
	private int sizeRows;
	private int sizeColumns;
	

	public sudoku(int[][] original_board){
		sizeRows = original_board.length;
		sizeColumns = original_board[0].length;
				
		this.board = new int[sizeRows][sizeColumns];
		
		for (int row=0; row<sizeRows; row++){
			for (int column=0; column<sizeColumns; column++){
				this.board[row][column] = original_board[row][column];
			}
		}		
	}
	
	public static void print_sudoku(int[][] board){
		for (int row=0 ; row<board.length ; row++){
			for (int column=0 ; column<board.length ; column++){
				System.out.print(board[row][column] + " ");
			}
			System.out.println();
		}
	}
	
	public int get_rows(){
		return sizeRows;
	}
	
	public int get_columns(){
		return sizeColumns;
	}
	
	public boolean isValidRow(int row, int column, int value){
		for (int i=0; i<sizeRows; i++){
			if (value == board[row][i] && i != column){
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidColumn(int row, int column, int value){
		for (int i=0; i<sizeColumns; i++){
			if (value == board[i][column] && i != row){
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidGrid(int row, int column, int value){
		int startGridRow=(row / 3) * 3;
		int startGridColumn=(column / 3) * 3;
		for (int i=startGridRow; i<startGridRow+3; i++){
			for (int j=startGridColumn; j<startGridColumn+3; j++){
				if (value == board[i][j] && i != row && j != column){
					return false;
				}
			}
		}
		return true;
	}
	
	
	public boolean isValidNumber(int row, int column, int value){
		return isValidGrid(row, column, value) && isValidRow(row, column, value) && isValidColumn(row, column, value);
	}	
	
	public boolean solve(){
		for (int r = 0; r < sizeRows; r++){
			for (int c = 0; c < sizeColumns; c++){
				if (board[r][c] == 0){
					for (int i = 1; i<=MAX_VALUE; i++){						
						if (isValidNumber(r, c, i)){	
							board[r][c] = i;
							if (solve()){
								return true;
							} else {
								board[r][c] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		sudoku game = new sudoku (SUDOKU_ORIGINAL);
		if (game.solve()){
			System.out.println("Sudoku solved!!!");
			System.out.println("Default solution");
			sudoku.print_sudoku(SUDOKU_SOLUTION);
			System.out.println("My solution");
			sudoku.print_sudoku(game.board);
		} else {
			System.out.println("The sudoku can't be solved");
		}
		
		

	}

}

