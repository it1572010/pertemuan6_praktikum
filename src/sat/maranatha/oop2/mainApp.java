package sat.maranatha.oop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Anthony-1572010
 */
public class mainApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PBO2 #6 Praktikum");
        FXMLLoader loader = new FXMLLoader();
        loader.
                setLocation(mainApp.class.
                        getResource("view/mainLayoutView.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
