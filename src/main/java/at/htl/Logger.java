package at.htl;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 * @timeline .
 * 2016-01-21: PON 001  created class
 * 2016-01-21: PON 020  implemented basic methods
 */
public class Logger {

    public List<String> lines;

    public Logger() {
        this.lines = new ArrayList<>();
    }

    public void AddLine(String line) {
        lines.add(line);
    }

    public void List(TextArea textArea) {
        textArea.setText("");
        String text = "";

        for (int i = lines.size(); i < 0; i--) {
            text+=lines.get(i) + "\n";
        }

        textArea.setText(text);

    }
}
