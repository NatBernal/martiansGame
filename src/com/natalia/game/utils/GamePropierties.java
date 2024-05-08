package com.natalia.game.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GamePropierties {
    Properties gameProperties;
    InputStream inputStream;

    public GamePropierties() {
        gameProperties = new Properties();
        try {
            inputStream = new FileInputStream("resources\\game.propierties");
            gameProperties.load(inputStream);
        } catch (FileNotFoundException e) { } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCannonImagePath() {
        return gameProperties.getProperty("image.spaceship");
    }

    public String getMartianImagePath() {
        return gameProperties.getProperty("image.martian");
    }
}
