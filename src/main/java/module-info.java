module ch.makery.address.adressapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.makery.address.adressapp to javafx.fxml;
    exports ch.makery.address.adressapp;
    exports ch.makery.address.adressapp.model.view;
    opens ch.makery.address.adressapp.model.view to javafx.fxml;
}