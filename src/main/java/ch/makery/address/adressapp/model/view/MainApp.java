//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.makery.address.adressapp.model.view;

import ch.makery.address.adressapp.model.person;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<person> PersonData = FXCollections.observableArrayList();

    public MainApp() {
        this.PersonData.add(new person("Diego Maradona", "attack"));
        this.PersonData.add(new person("Edson Arantes do Nascimento", "attack"));
        this.PersonData.add(new person("Zinedine Zidan", "attack"));
    }

    public ObservableList<person> getPersonData() {
        return this.PersonData;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PersonApp");
        this.initRootLayout();
        this.showPersonOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/ch/makery/adress/adressapp/view/RootLayout.fxml"));
            this.rootLayout = (BorderPane)loader.load();
            Scene scene = new Scene(this.rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException var3) {
            IOException e = var3;
            e.printStackTrace();
        }

    }

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/ch/makery/adress/adressapp/view/PersonOverview.fxml"));
            AnchorPane PersonOverview = (AnchorPane)loader.load();
            this.rootLayout.setCenter(PersonOverview);
            PersonOverviewController controller = (PersonOverviewController)loader.getController();
            controller.setMainApp(this);
        } catch (IOException var4) {
            IOException e = var4;
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Открывает диалоговое окно для изменения деталей указанного игрока.
     * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте игрока и возвращается значение true.
     *
     * @param player - объект игрока, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showPlayerEditDialog(person player) {
        try {
            // Загружаем fxml-файл и создаём новую сцену для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/ch/makery/adress/adressapp/view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Player");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём игрока в контроллер.
            PlayerEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPlayer(player); // Изменение на setPlayer

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
