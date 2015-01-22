package customComponents;

import java.util.ArrayList;

public class Filedata {
	private ArrayList<ArrayList<Integer>> cordinates;
	private ArrayList<Character> steps;
	private int rows, columns;
	public Filedata(){;
	}
	public ArrayList<ArrayList<Integer>> getCordinates() {
		return cordinates;
	}
	public void setCordinates(ArrayList<ArrayList<Integer>> cordinates) {
		this.cordinates = cordinates;
	}
	public ArrayList<Character> getSteps() {
		return steps;
	}
	public void setSteps(ArrayList<Character> steps) {
		this.steps = steps;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
}
