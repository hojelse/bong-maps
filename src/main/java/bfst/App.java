/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bfst;

import java.io.IOException;

import bfst.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.primaryStage = primaryStage;
        Parent root = App.loadFXML("main");
        primaryStage.setTitle("LLamp");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("bfst/views/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Parent loadFXML(String filename) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + filename + ".fxml"));
        MainController mainController = new MainController(primaryStage);
        fxmlLoader.setController(mainController);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        App.launch(args);
    }


}
