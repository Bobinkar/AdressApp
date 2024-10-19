package ch.makery.address.adressapp.model.view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.adressapp.model.person;
import ch.makery.address.adressapp.model.view.DateUtil;
/**
 * Окно для изменения информации об адресате.
 *
 * @author Marco Jakob
 */
public class PlayerEditDialogController {

    @FXML
    private TextField playerNameField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField birthDateField;

    private Stage dialogStage;
    private person player; // Изменено на Player
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт футболиста, информацию о котором будем менять.
     *
     * @param player
     */
    public void setPlayer(person player) {
        this.player = player;

        playerNameField.setText(player.getPlayerName());
        positionField.setText(player.getPosition());
        heightField.setText(Integer.toString(player.getHeight()));
        weightField.setText(Integer.toString(player.getWeight()));
        birthDateField.setText(DateUtil.format(player.getBirthDate()));
        birthDateField.setPromptText("dd.MM.yyyy");
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            player.setPlayerName(playerNameField.getText());
            player.setPosition(positionField.getText());
            player.setHeight(Integer.parseInt(heightField.getText()));
            player.setWeight(Integer.parseInt(weightField.getText()));
            player.setBirthDate(DateUtil.parse(birthDateField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (playerNameField.getText() == null || playerNameField.getText().length() == 0) {
            errorMessage += "No valid player name!\n";
        }
        if (positionField.getText() == null || positionField.getText().length() == 0) {
            errorMessage += "No valid position!\n";
        }
        if (heightField.getText() == null || heightField.getText().length() == 0) {
            errorMessage += "No valid height!\n";
        } else {
            try {
                Integer.parseInt(heightField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid height (must be an integer)!\n";
            }
        }

        if (weightField.getText() == null || weightField.getText().length() == 0) {
            errorMessage += "No valid weight!\n";
        } else {
            try {
                Integer.parseInt(weightField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid weight (must be an integer)!\n";
            }
        }

        if (birthDateField.getText() == null || birthDateField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthDateField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.MM.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
