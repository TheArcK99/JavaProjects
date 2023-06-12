public class KansupadaA25
{

	public static void main(String args[])
	{
		PetriDish p = new PetriDish();
		p.showGrid();
		int a = (int)(Math.random()*p.gRow());
		int b = (int)(Math.random()*p.gCol());
		System.out.println(p.numNeighbors(a,b));
		p.updateGrid();
		p.showGrid();
		//System.out.print(a+" "+b);
	}
}


class PetriDish
{
	private boolean[][] grid;

	public PetriDish(){
		int r = (int)((Math.random()*26)+5);
		int c = (int)((Math.random()*26)+5);
		grid = new boolean[r][c];
		for(int r1 = 0; r1 < grid.length; r1++){
			for(int c1 = 0; c1 < grid[r1].length; c1++){
				if(((int)((Math.random()*10)+1))<= 3){
					grid[r1][c1] = true;
				}else{
					grid[r1][c1] = false;
				}
			}

		}
	}

	public int gRow(){
		return grid.length;
	}
	public int gCol(){
		return grid[0].length;
	}

	 public void showGrid()
	 {
		for(int r1 = 0; r1 < grid.length; r1++){
			for(int c1 = 0; c1 < grid[r1].length; c1++){
				if(grid[r1][c1] == true){
					System.out.print("O");
				}else{
					System.out.print(".");
				}
			}
			System.out.println();
		}
	 }

	 public boolean updateCell(int r, int c){
	 	if(grid[r][c] == true){
	 		if(numNeighbors(r,c)<2 || numNeighbors(r,c) >3){
	 			return false;
	 		}else{
	 			return true;
	 		}
	 	}
	 	if(grid[r][c] == false){
	 		if(numNeighbors(r,c) != 3){
	 			return false;
	 		}else{
	 			return true;
	 		}
	 	}
	 	return false;
	 }

	 public void updateGrid(){
	 	boolean[][] gridCop = new boolean[grid.length][grid[0].length];
	 	for(int r = 0; r < grid.length; r++){
	 		for(int c = 0; c < grid[0].length; c++){
	 			gridCop[r][c] = updateCell(r, c);
	 		}
	 	}
	 	grid = gridCop;
	 }

	 public int numNeighbors(int row, int col)
	 {
	 	int num = 0;
		if(row+1 < grid.length && col+1 < grid[row].length && grid[row+1][col+1]==true){
			num++;
		}
		if(row < grid.length && col+1 < grid[row].length && grid[row][col+1]==true){
			num++;
		}
		if(row+1 < grid.length && col < grid[row].length && grid[row+1][col]==true){
			num++;
		}
		if(row-1 >= 0 && col-1 >= 0 && grid[row-1][col-1]==true){
			num++;
		}
		if(row-1 >= 0 && col >= 0 && grid[row-1][col]==true){
			num++;
		}
		if(row >= 0 && col-1 >= 0 && grid[row][col-1]==true){
			num++;
		}
		if(row-1 >= 0 && col+1 < grid[row].length && grid[row-1][col+1]==true){
			num++;
		}
		if(row+1 < grid.length  && col-1 >= 0 && grid[row+1][col-1]==true){
			num++;
		}


		return num;
	 }


}