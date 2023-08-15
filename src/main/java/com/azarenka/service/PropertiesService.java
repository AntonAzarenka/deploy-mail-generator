package com.azarenka.service;

import com.azarenka.domain.MailProperty;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents class for manage of properties.
 */
@Service
public class PropertiesService {

    private final static String PROPERTIES_FILE_NAME = "properties.dat";

    public void saveProperties(MailProperty property) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PROPERTIES_FILE_NAME))) {
            outputStream.writeObject(property);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public MailProperty loadProperty() {
        MailProperty mailProperty = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PROPERTIES_FILE_NAME))) {
            mailProperty = (MailProperty) inputStream.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mailProperty;
    }
}
