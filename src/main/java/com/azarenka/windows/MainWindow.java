package com.azarenka.windows;

import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.FxmlFileLoader;
import com.azarenka.javafx.load.IFxmlWindow;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Represents main application window.
 */
@Component
public class MainWindow extends CommonWidget implements IFxmlWindow {

    @Value("classpath:fxml/main-window-deploy-mail-generator.fxml")
    private Resource resource;
    private Scene scene;

    public MainWindow(ApplicationContext applicationContext) {
        super(applicationContext);
        setSize(350, 240);
        setTitle("Mail Generator");
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void load() {
        scene = loadBean(new FxmlFileLoader(resource));
    }
}
