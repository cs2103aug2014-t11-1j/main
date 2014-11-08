package gui.controller.view;
/**
 * @author A0116018R
 * Unused: not fully developed.
 * 
 * This class is the controller for tentativeView.
 * It sets the tentative tasks and dates in the view.
 */
import gui.controller.testTentativeTask;
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

	TreeItem<TentativeNode> root_;
	
	//for isolated testing purposes
	ObservableList<testTentativeTask> tentativeList_;
	@FXML
	private void initialize() {
		tentativeList_ = FXCollections.observableArrayList();
		tentativeList_.add(new testTentativeTask("test", "date1", "date2", 1));
		tentativeList_.add(new testTentativeTask("test2", "date1", "date2", 2));
		
		root_ = new TreeItem<>(new TentativeNode(null,null));
		root_.setExpanded(true);
		
		for(testTentativeTask task : tentativeList_){
			root_.getChildren().add(task.getTentativeNode());
		}
		
		treeTableView.setShowRoot(false);
	    treeTableView.setRoot(root_);
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
