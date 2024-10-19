package ch.makery.address.adressapp.model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Класс-модель для футболиста (Player).
 */
public class person {

    private final StringProperty playerName;
    private final StringProperty position;
    private final ObjectProperty<LocalDate> birthDate;
    private final IntegerProperty height;  // в сантиметрах
    private final IntegerProperty weight;  // в килограммах

    /**
     * Конструктор по умолчанию.
     */
    public person() {
        this(null, null);
    }

    /**
     * Конструктор с начальными данными.
     *
     * @param playerName Имя игрока
     * @param position Позиция на поле
     */
    public person(String playerName, String position) {
        this.playerName = new SimpleStringProperty(playerName);
        this.position = new SimpleStringProperty(position);

        // Фиктивные начальные данные
        this.birthDate = new SimpleObjectProperty<>(LocalDate.of(2000, 1, 1));
        this.height = new SimpleIntegerProperty(180);  // по умолчанию 180 см
        this.weight = new SimpleIntegerProperty(75);   // по умолчанию 75 кг
    }

    // Геттеры и сеттеры для всех полей

    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public StringProperty playerNameProperty() {
        return playerName;
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public StringProperty positionProperty() {
        return position;
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public int getHeight() {
        return height.get();
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public IntegerProperty weightProperty() {
        return weight;
    }
}
