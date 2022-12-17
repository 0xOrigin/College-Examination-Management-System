package CEMS.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The class Main stage.
 */
public class MainStage extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource("FXML/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("College Examination Management System");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    @Override
    public void stop(){
        System.exit(0);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }

}
