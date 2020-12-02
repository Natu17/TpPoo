package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class AppMenu extends MenuBar {

    public AppMenu(Stage primaryStage) {
        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir de la aplicación");
            alert.setContentText("¿Está seguro que desea salir de la aplicación?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });
        file.getItems().add(exitMenuItem);
        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Implementación Original: Laura Zabaleta (POO 2013).");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);

        Menu levels = new Menu("Niveles");
        MenuItem level1 = new MenuItem("Nivel 1");
        level1.setOnAction(event -> {
            setLevel(primaryStage);
        });
        MenuItem level2 = new MenuItem("Nivel 2");
        MenuItem level3 = new MenuItem("Nivel 3");

        levels.getItems().addAll(level1,level2,level3);

        getMenus().addAll(file, help, levels);
    }

    private void setLevel(Stage primaryStage) {
        CandyGame game = new CandyGame(Level1.class);
        CandyFrame frame = new CandyFrame(game, primaryStage);
        Scene scene = new Scene(frame);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
