package storage;

import java.util.ArrayList;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

public class ModelTentativeTask extends ModelTask{

	private ArrayList<String> dates;
    
    private TreeItem<TentativeNode> node;
	
	public ModelTentativeTask(String event, int position){
		super(event, null, null, null, null, position, false, false);
		dateStringProperty.set("Tentative");
		timeStringProperty.set("Tentative");
		
		dates = new ArrayList<String>();
		node = new TreeItem<TentativeNode>(new TentativeNode(event, String.valueOf(position)));
        node.setExpanded(true);
	}
	
	@Override
	public ModelTask copyTask(){
		ModelTentativeTask temp = new ModelTentativeTask(getEvent(), position);
		return temp;
	}
	
	//mutators
	@Override
	public void setEvent(String event){
		super.setEvent(event);
		node.getValue().setPosition(event);
	}
	
	@Override 
	public void setPosition(int position){
		super.setPosition(position);
		node.getValue().setPosition(String.valueOf(position));
	}
	
	public void addDate(String date){
		dates.add(date);
		setChild(date);
	}
	
	//accessors
	public ArrayList<String> getDates(){
		return dates;
	}
	
	public TreeItem<TentativeNode> getNode(){
		return node;
	}
	
	//private methods
	private void setChildren(){
		for(String date : dates){
			TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
			node.getChildren().add(leaf);
			node.setExpanded(true);
		}
	}
	
	private void setChild(String date){
		TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
		node.getChildren().add(leaf);
		node.setExpanded(true);
	}

}
