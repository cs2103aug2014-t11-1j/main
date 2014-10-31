package gui.controller;

import com.TentativeNode;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TentativeViewController {
	@FXML
	TreeTableView<TentativeNode> treeTableView;
	
	@FXML
	TreeTableColumn<TentativeNode,  String> numColumn;
	@FXML
	TreeTableColumn<TentativeNode, String> tentativeColumn;

	TreeItem<TentativeNode> root;
	ObservableList<testTentativeTask> tentativeList;
	@FXML
	private void initialize() {
		tentativeList = FXCollections.observableArrayList();
		tentativeList.add(new testTentativeTask("test", "date1", "date2", 1));
		tentativeList.add(new testTentativeTask("test2", "date1", "date2", 2));
		
		root = new TreeItem<>(new TentativeNode(null,null));
		root.setExpanded(true);
		
		for(testTentativeTask task : tentativeList){
			root.getChildren().add(task.getTentativeNode());
		}
		
		treeTableView.setShowRoot(false);
	    treeTableView.setRoot(root);
	    treeTableView.setTreeColumn(tentativeColumn);
	    
		numColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<TentativeNode, String> param) -> 
				new ReadOnlyStringWrapper(param.getValue().getValue().getPositionStringProperty().get())
				);
		tentativeColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<TentativeNode, String> param) -> 
				new ReadOnlyStringWrapper(param.getValue().getValue().getTaskCol().get())
				);
		
		treeTableView.setPlaceholder(new Label(""));
	}


}
