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
 * Represents window for applied setting of application.
 */
@Component
public class OptionsWindow extends CommonWidget implements IFxmlWindow {

    @Value("classpath:fxml/options-window-deploy-mail-generator.fxml")
    private Resource resource;
    private Scene scene;

    public OptionsWindow(ApplicationContext applicationContext) {
        super(applicationContext);
        setSize(620,350);
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
