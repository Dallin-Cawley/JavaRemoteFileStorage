package javaRemoteFileStorage;

import com.google.gson.Gson;
import javaRemoteFileStorage.Communication.Request.Headers;
import javaRemoteFileStorage.Communication.Request.RequestBody;
import javaRemoteFileStorage.Communication.Response.ResponseBody;
import javaRemoteFileStorage.logging.History;
import javaRemoteFileStorage.logging.LoggingEvent;
import javaRemoteFileStorage.logging.LoggingManager;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class Server {

    private static LoggingManager loggingManager;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        MainWindow mainWindow = new MainWindow();
        History serverSocketHistory = new History(System.getenv("LOG_FILE_LOCATION"));
        ServerSocket serverSocket;
        loggingManager = new LoggingManager();
        Gson gson = new Gson();
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
                System.out.println("Accepted connection from: " + connection.getInetAddress().getHostName());
                DataInputStream inputStream = new DataInputStream(connection.getInputStream());

                byte[] headerBytes = new byte[50];
                int bytesRead = inputStream.read(headerBytes, 0, headerBytes.length);
                String headersJSON = new String(headerBytes, StandardCharsets.UTF_8);
                headersJSON = headersJSON.trim();
                System.out.println(headersJSON);

                Headers headers = gson.fromJson(headersJSON, Headers.class);

                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                ResponseBody responseBody = new ResponseBody();
                responseBody.status = "ready";
                String responseJSON = gson.toJson(responseBody);
                outputStream.write(responseJSON.getBytes(StandardCharsets.UTF_8));

                byte[] requestBodyBytes = new byte[headers.size];
                bytesRead = inputStream.read(requestBodyBytes, 0, requestBodyBytes.length);
                String requestBodyJSON = new String(requestBodyBytes, StandardCharsets.UTF_8);
                System.out.println(requestBodyJSON);

                RequestBody requestBody = gson.fromJson(requestBodyJSON, RequestBody.class);

                System.out.println("RequestBody File location: " + requestBody.fileLocation);
                loggingManager.logHistories();
                break;
            }
            catch (IOException exception) {
                System.out.println("Encountered an IOException when receiving data");
                exception.printStackTrace();
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
