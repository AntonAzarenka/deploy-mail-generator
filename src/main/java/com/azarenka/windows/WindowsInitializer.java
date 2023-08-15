package com.azarenka.windows;

import com.azarenka.javafx.StageInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * Initializer of windows.
 */
@Component
public class WindowsInitializer extends StageInitializer {

    @Autowired
    private MainWindow mainWindow;
    @Autowired
    private OptionsWindow optionsWindow;

    @PostConstruct
    public void init() {
        setCommonWidget(mainWindow);
        setIcon(Objects.requireNonNull(this.getClass().getResource("../generator.png")).toExternalForm());
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public OptionsWindow getOptionsWindow() {
        return optionsWindow;
    }
}
