package CEMS.Views;

import CEMS.Views.ViewController.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Utilities {

    protected void changeScene(ActionEvent event, String fxmlFile, String stageTitle){
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = getParent(loader);
        setStagePreferences(root, event, stageTitle);
    }

    protected void changeSceneAndSet(ActionEvent event, String fxmlFile, String stageTitle, String username, String name){
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = getParent(loader);
        setControllerData(loader.getController(), username, name);
        setStagePreferences(root, event, stageTitle);
    }

    public static void centerOnScreen(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private void setControllerData(Controller controller, String username, String name){
        controller.setUsername(username);
        controller.setNameOfUser(name);
    }

    private FXMLLoader getLoader(String fxmlFile){
        return new FXMLLoader(getClass().getResource("../FXML/" + fxmlFile));
    }

    private Parent getParent(FXMLLoader loader){
        try {
            return (Parent) loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setStagePreferences(Parent root, ActionEvent event, String stageTitle){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(stageTitle);
        centerOnScreen(stage);
        stage.show();
    }

    protected void handleAlert(Alert alert, String title, String message, Alert.AlertType alertType){
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    protected void setAlertOwner(ActionEvent event, Alert alert){
        alert.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
