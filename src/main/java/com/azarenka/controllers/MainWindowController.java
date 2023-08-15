package com.azarenka.controllers;

import com.azarenka.domain.ApplicationEnum;
import com.azarenka.javafx.SceneChanger;
import com.azarenka.service.MailService;
import com.azarenka.windows.WindowsInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Represents controller for {@link com.azarenka.windows.MainWindow}
 */
@Component
public class MainWindowController implements Initializable {

    @Autowired
    private WindowsInitializer initializer;
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private MailService mailService;
    @Autowired
    private OptionsWindowController optionsWindowController;

    public TextField versionTextField;
    public ComboBox<ApplicationEnum> applicationCombobox;
    public Button secondMailButton;
    public Button firstMailButton;

    public void initialize() {
        applicationCombobox.setItems(FXCollections.observableList(Arrays.asList(ApplicationEnum.values())));
        firstMailButton.setOnMouseClicked(event -> {
            if (!versionTextField.getText().isEmpty()) {
                mailService.generateMail(getApplicationName(), versionTextField.getText(), true);
            } else {
                showErrorMessage();
            }
        });
        secondMailButton.setOnMouseClicked(event -> {
            if (!versionTextField.getText().isEmpty()) {
                mailService.generateMail(getApplicationName(), versionTextField.getText(), false);
            } else {
                showErrorMessage();
            }
        });
    }

    public void options(ActionEvent actionEvent) {
        sceneChanger.setNewScene(initializer.getOptionsWindow());
        optionsWindowController.loadProperties();
    }

    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Version can't be empty");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicationCombobox.setItems(FXCollections.observableList(Arrays.asList(ApplicationEnum.values())));
        applicationCombobox.getSelectionModel().selectFirst();
        firstMailButton.setOnMouseClicked(event -> {
            if (!versionTextField.getText().isEmpty()) {
                mailService.generateMail(getApplicationName(), versionTextField.getText(), true);
            } else {
                showErrorMessage();
            }
        });
        secondMailButton.setOnMouseClicked(event -> {
            if (!versionTextField.getText().isEmpty()) {
                mailService.generateMail(getApplicationName(), versionTextField.getText(), false);
            } else {
                showErrorMessage();
            }
        });
    }

    private String getApplicationName() {
        return applicationCombobox.getSelectionModel().getSelectedItem().name();
    }
}
