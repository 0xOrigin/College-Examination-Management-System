package CEMS.Views;

import CEMS.Views.ViewControllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

/**
 * The class Utilities.
 */
public class Utilities {

    /**
     * Change scene.
     *
     * @param event      the event
     * @param fxmlFile   the fxml file
     * @param stageTitle the stage title
     */
    protected void changeScene(ActionEvent event, String fxmlFile, String stageTitle){
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = getParent(loader);
        setStagePreferences(root, event, stageTitle);
    }

    /**
     * Change scene and set controller data.
     *
     * @param event      the event
     * @param fxmlFile   the fxml file
     * @param stageTitle the stage title
     * @param username   the username
     * @param name       the name
     */
    protected void changeSceneAndSet(ActionEvent event, String fxmlFile, String stageTitle, String username, String name){
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = getParent(loader);
        setControllerData(loader.getController(), username, name);
        setStagePreferences(root, event, stageTitle);
    }

    /**
     * Center on screen.
     *
     * @param stage the stage
     */
    public static void centerOnScreen(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private void setControllerData(Controller controller, String username, String name){
        controller.setUsername(new StringBuilder(username));
        controller.setNameOfUser(name);
    }

    private FXMLLoader getLoader(String fxmlFile){
        return new FXMLLoader(Utilities.class.getResource("FXML/" + fxmlFile));
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

    /**
     * Handle alert.
     *
     * @param alert     the alert
     * @param title     the title
     * @param message   the message
     * @param alertType the alert type
     */
    protected void handleAlert(Alert alert, String title, String message, Alert.AlertType alertType){
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Set alert owner.
     *
     * @param event the event
     * @param alert the alert
     */
    protected void setAlertOwner(ActionEvent event, Alert alert){
        alert.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    /**
     * Clear table view.
     *
     * @param tableView the table view
     */
    protected void clearTableView(TableView<Map> tableView){
        tableView.getItems().clear();
    }

}
