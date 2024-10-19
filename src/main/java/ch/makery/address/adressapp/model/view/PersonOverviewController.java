package ch.makery.address.adressapp.model.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.adressapp.model.person;
import ch.makery.address.adressapp.model.view.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<person> personTable; // Изменил название переменной для соответствия модели
    @FXML
    private TableColumn<person, String> playerNameColumn;
    @FXML
    private TableColumn<person, String> positionColumn;

    @FXML
    private Label playerNameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label birthDateLabel;
    @FXML
    private Label heightLabel;
    @FXML
    private Label weightLabel;

    // Ссылка на главное приложение.
    private MainApp mainApp; // Изменил имя переменной с MainApp на mainApp

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public PersonOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы игроков с двумя столбцами.
        playerNameColumn.setCellValueFactory(cellData -> cellData.getValue().playerNameProperty());
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty());

        // Очистка подробной информации об игроке.
        showPlayerDetails(null);

        // Слушаем изменения выбора, и при изменении показываем
        // подробную информацию об игроке.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPlayerDetails(newValue));
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Заполняет все текстовые поля для отображения подробной информации
     * об игроке, если игрок выбран.
     * Если игрок не выбран, очищает текстовые поля.
     *
     * @param player — выбранный игрок, или null
     */
    private void showPlayerDetails(person player) {
        if (player != null) {
            // Заполнение лейблов информацией из объекта Player.
            playerNameLabel.setText(player.getPlayerName());
            positionLabel.setText(player.getPosition());
            birthDateLabel.setText(DateUtil.format(player.getBirthDate())); // Используем форматирование даты
            heightLabel.setText(Integer.toString(player.getHeight()));
            weightLabel.setText(Integer.toString(player.getWeight()));


        } else {
            // Если игрок не выбран, очищаем все лейблы.
            playerNameLabel.setText("");
            positionLabel.setText("");
            birthDateLabel.setText("");
            heightLabel.setText("");
            weightLabel.setText("");
        }
    }
    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeletePlayer() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleNewPlayer() {
        person tempPlayer = new person();
        boolean okClicked = mainApp.showPlayerEditDialog(tempPlayer); // Изменено на showPlayerEditDialog
        if (okClicked) {
            mainApp.getPersonData().add(tempPlayer); // Изменено на getPlayerData
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке Edit...
     * Открывает диалоговое окно для изменения выбранного игрока.
     */
    @FXML
    private void handleEditPlayer() {
        person selectedPlayer = personTable.getSelectionModel().getSelectedItem(); // Изменено на playerTable
        if (selectedPlayer != null) {
            boolean okClicked = mainApp.showPlayerEditDialog(selectedPlayer); // Изменено на showPlayerEditDialog
            if (okClicked) {
                showPlayerDetails(selectedPlayer); // Изменено на showPlayerDetails
            }
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Player Selected");
            alert.setContentText("Please select a player in the table.");

            alert.showAndWait();
        }
    }


}
