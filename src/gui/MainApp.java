package gui;

import java.io.IOException;

import gui.controller.PhantomController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane overallView;
    private BorderPane rootLayout;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Phantom");
        this.primaryStage.centerOnScreen();
        this.primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        initRootLayout();
        showPhantomOverallView();
    }
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            rootLayout.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });
            
            this.primaryStage.getIcons().add(new Image("file:Resources/images/ghost.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showPhantomOverallView() {
        try {  	
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OverallView.fxml"));
            overallView = (AnchorPane) loader.load();
            System.out.println("overall view loaded");

            rootLayout.setCenter(overallView);

            PhantomController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            controller.setOverallView(overallView);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}