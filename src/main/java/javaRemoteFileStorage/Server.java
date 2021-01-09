package javaRemoteFileStorage;

import javaRemoteFileStorage.logging.History;
import javaRemoteFileStorage.logging.LoggingEvent;
import javaRemoteFileStorage.logging.LoggingManager;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class Server {

    private static LoggingManager loggingManager;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        MainWindow mainWindow = new MainWindow();
        History serverSocketHistory = new History(System.getenv("LOG_FILE_LOCATION"));
        ServerSocket serverSocket;
        loggingManager = new LoggingManager();
        try {
            serverSocket = new ServerSocket(10000);
        }
        catch (IOException exception) {
            String loggingMessage = "Encountered IOException when creating ServerSocket: " + exception.getMessage();
            LoggingEvent event = new LoggingEvent(Level.SEVERE, loggingMessage, "main()", Server.class.getName());
            serverSocketHistory.addEvent(event);
            loggingManager.addHistory(serverSocketHistory);
            loggingManager.logHistories();
            return;
        }

        while(true) {
            try {
                Socket connection = serverSocket.accept();
                loggingManager.logHistories();
            }
            catch (IOException exception) {
                String loggingMessage = "Encountered IOException when attempting to accept connection: " + exception.getMessage();
                LoggingEvent event = new LoggingEvent(Level.SEVERE, loggingMessage, "main()", Server.class.getName());
                serverSocketHistory.addEvent(event);
                loggingManager.addHistory(serverSocketHistory);
            }
        }
    }

    public void addLoggingHistory(History history) {
        loggingManager.addHistory(history);
    }
}
