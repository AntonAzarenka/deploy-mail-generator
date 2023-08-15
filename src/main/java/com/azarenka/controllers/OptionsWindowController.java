package com.azarenka.controllers;

import com.azarenka.domain.MailProperty;
import com.azarenka.javafx.SceneChanger;
import com.azarenka.service.PropertiesService;
import com.azarenka.windows.WindowsInitializer;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Represents controller for {@link com.azarenka.windows.OptionsWindow}
 */
@Component
public class OptionsWindowController {

    public TextField toTextField;
    public TextField ccTextField;
    public TextField subjectTextField;
    public TextField linkTextField;
    public TextField yearTextField;
    public TextField mothTextField;
    @Autowired
    private WindowsInitializer initializer;
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private PropertiesService propertiesService;

    public void backToMainWindow(ActionEvent actionEvent) {
        propertiesService.saveProperties(createProperty());
        sceneChanger.setNewScene(initializer.getMainWindow());
    }

    public void loadProperties() {
        MailProperty mailProperty = propertiesService.loadProperty();
        toTextField.setText(mailProperty.getToEmail());
        ccTextField.setText(mailProperty.getCcEmail());
        subjectTextField.setText(mailProperty.getSubject());
        linkTextField.setText(mailProperty.getReleaseNotesLink());
        yearTextField.setText(mailProperty.getYearRelease());
        mothTextField.setText(mailProperty.getMonthRelease());
    }

    private MailProperty createProperty() {
        return new MailProperty(
                "Hi,",
                toTextField.getText(),
                ccTextField.getText(),
                subjectTextField.getText(),
                linkTextField.getText(),
                mothTextField.getText(),
                yearTextField.getText()
        );
    }
}
