/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package es.francho.lab.gameoflife;


/**
 * @author francho
 *
 */
public class World {
	
	Cell[][] cells;

	/**
	 * 
	 * @param rows
	 * @param cols
	 */
	public World(int rows, int cols) {
		cells=new Cell[rows][cols];
		
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				cells[r][c] = new Cell();
			}
		}
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public Cell getCell(int row, int col) {
		return cells[row][col];
	}

	/**
	 * @return
	 */
	public int getNumRows() {
		return cells.length;
	}
	
	/**
	 * @return
	 */
	public int getNumCols() {
		return cells[0].length;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public int getLiveNeighbors(int row, int col) {
		int liveNeighbords=0;
		
		for(int r=getFromRow(row); r <= getToRow(row); r++) {
			for(int c=getFromCol(col); c <= getToCol(col); c++) {
				final Cell cell = getCell(r, c);
				if(cell.isAlive() && !(r==row && c==col)) {
					liveNeighbords++;
				}
			}
		}
		return liveNeighbords;
	}

	

	/**
	 * @param row
	 * @return
	 */
	private int getFromRow(int row) {
		return (row-1 < 0) ? 0 : row-1;
	}

	/**
	 * @param row
	 * @return
	 */
	private int getToRow(int row) {
		return (row+1 < getNumRows()) ? row+1 : getNumRows()-1;
	}

	/**
	 * @param col
	 * @return
	 */
	private int getFromCol(int col) {
		return (col-1 < 0) ? 0 : col-1;
	}
	
	/**
	 * @param col
	 * @return
	 */
	private int getToCol(int col) {
		return (col+1 < getNumCols()) ? col+1 : getNumCols()-1;
	}
	
	/**
	 * 
	 */
	public void nextGeneration() {
		prepareNextGeneration();
		fixNextGeneration();	
	}

	/**
	 * 
	 */
	private void prepareNextGeneration() {
		for(int r=0; r<getNumRows(); r++) {
			for(int c=0; c<getNumCols(); c++) {
				final int neighbors = getLiveNeighbors(r, c);
				
				final Cell cell = getCell(r, c);
				cell.prepareNextGeneration(neighbors);
			}
		}
	}

	/**
	 * 
	 */
	private void fixNextGeneration() {
		for(int r=0; r<getNumRows(); r++) {
			for(int c=0; c<getNumCols(); c++) {
				final Cell cell = getCell(r, c);
				cell.fixNextGeneration();
			}
		}
	}
}
