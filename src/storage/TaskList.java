package storage;

import java.util.ArrayList;
import java.util.Iterator;


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
        
        public Iterator<Task> getIterator(){
            return list.iterator();
        }
	
	/**
	 * Mutators
	 */
	public void add(Task task){
		list.add(task);
	}
        
        public void add(Task task, int index){
            list.add(index, task);
        }
	
	public void remove(int index){
		list.remove(index);
	}
        
        public void clear(){
            list.clear();
        }
        
        public void setList(ArrayList<Task> list){
            this.list = list;
        }
        
        public void set(int index, Task task){
            list.set(index, task);
        }
}
