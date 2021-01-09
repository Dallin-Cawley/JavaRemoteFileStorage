package remoteStorageServer;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;
import remoteStorageServer.Communication.Security.KeyManager;
import remoteStorageServer.FileSystem.ServerFile;
import remoteStorageServer.GUI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainWindow mainWindow = new MainWindow();

//        PlatformImpl.startup(() -> {
//            FileChooser d = new FileChooser();
//            d.showOpenDialog(null);
//        });
//
//        new JFXPanel();
//        Platform.runLater(() -> {
//            FileChooser d = new FileChooser();
//            d.showOpenDialog(null);
//        });

        KeyManager keyManager = new KeyManager();
        ServerFile serverFile = new ServerFile("C:\\Users\\lette\\Documents\\ECEN 340\\Final Project.docx", keyManager);

        System.out.println("Digital Signature: " + Arrays.toString(serverFile.getSignature()));
    }
}
