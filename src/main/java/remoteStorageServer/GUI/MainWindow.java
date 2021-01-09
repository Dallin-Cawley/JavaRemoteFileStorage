package remoteStorageServer.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {
    List<Window> childWindows;

    public MainWindow() {
        JButton button = new JButton("click");
        button.setBounds(130,100,100, 40);//x axis, y axis, width, height
        button.setText("Open Directory");

        setTitle("Remote File Storage");
        setSize(600,400);

        InvertedTextArea textArea = new InvertedTextArea();

        textArea.setBounds(400, 350, 200, 0);
        textArea.appendFromBottom("Line 1\n");
        textArea.appendFromBottom("Line 2");
        add(textArea);
//        add(button);//adding button in JFrame

        setLayout(null); //using no layout managers
        setVisible(true); //making the frame visible
    }
}
