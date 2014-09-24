package storage;

import java.util.ArrayList;

public class TaskList {
	
	/**
	 * Class Attributes
	 */	
	private ArrayList<Task> list;
	
	/**
	 * Constructor
	 */	
	public TaskList() {
		list = new ArrayList<Task>();
	}
	
	/**
	 * Accessors
	 */
	public ArrayList<Task> getList() {
		return list;
	}
	
	public int getListSize(){
		return list.size();
	}
	
	public Task get(int index){
		return list.get(index);
	}
	
	/**
	 * Mutators
	 */
	public void add(Task task){
		list.add(task);
	}
	
	public void remove(int index){
		list.remove(index);
	}

}
