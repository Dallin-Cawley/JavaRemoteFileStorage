package remoteStorageServer.GUI;

import javax.swing.*;
import java.awt.*;

public class InvertedTextArea extends JTextArea{
    private Font font;
    int numLines;

    InvertedTextArea() {
        super();
        font = new Font("SansSerif", Font.PLAIN, 12);
        numLines = 0;

        setFont(font);
        setEditable(false);
    }

    InvertedTextArea(Font font) {
        super();
        this.font = font;
        numLines = 0;

        setFont(this.font);
        setEditable(false);
    }

    public void appendFromBottom(String text) {
        FontMetrics fontMetrics = getFontMetrics(font);
        numLines++;

        append(text);

        int textAreaHeight = numLines * fontMetrics.getHeight();
        Rectangle bounds = getBounds();
        bounds.height = textAreaHeight;
//        bounds.y = 400 - bounds.height;
        setBounds(bounds);
    }

}
