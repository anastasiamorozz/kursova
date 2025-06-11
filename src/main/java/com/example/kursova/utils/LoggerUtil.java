package com.example.kursova.utils;

import com.example.kursova.utils.handler.EmailHandler;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger("TourAppLogger");

    static {
        try {
            LogManager.getLogManager().reset();

            FileHandler fileHandler = new FileHandler("tour_app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);

            logger.setLevel(Level.ALL);

            // Додаємо обробник для надсилання e-mail
            logger.addHandler(new EmailHandler());

        } catch (IOException e) {
            System.err.println("Не вдалося ініціалізувати логування: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
