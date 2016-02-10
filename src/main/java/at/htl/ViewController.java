package at.htl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @timeline .
 * 2016-01-21: PON 001  created class
 * 2016-01-21: PON 060  GUI implementation
 * 2016-01-21: PON 090  file and directory - chooser
 */
public class ViewController implements Initializable{

    @FXML
    private Button btnChooseDir;
    @FXML
    private Button btnFileToWrite;
    @FXML
    private Button btnStart;

    @FXML
    private TextField tfDirectory;
    @FXML
    private TextField tfFileToWrite;

    @FXML
    private TextArea taLogger,taFiles;

    private List<File> files, sourceFiles;

    private File targetDir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taLogger.setText("Welcome to Automatic Student!");
        files = new ArrayList<>();
    }

    public void handleStartAction(ActionEvent actionEvent) {
        Writer w = new Writer();
        try {
            for (File sourceFile : sourceFiles) {
                w.startWritingFromFile(sourceFile,targetDir,taLogger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void HandleDirChooser(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home")));
        dc.setTitle("Choose target directory");

        File choosedDir = dc.showDialog(new Stage());
        tfDirectory.setText(choosedDir.getAbsolutePath());
        targetDir = choosedDir;
    }

    public void HandleAddFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.setTitle("Choose your files to write");

        List<File> choosedFiles = fc.showOpenMultipleDialog(new Stage());

        for (File choosedFile : choosedFiles) {
            files.add(choosedFile);
        }

        sourceFiles = files;

        listFiles();
    }

    private void listFiles() {

        String text = "";

        for (File file : files) {
            text += file.getName() + "\n";
        }

        taFiles.setText(text);
    }

    public void AddProject(ActionEvent actionEvent) {

    }

    public void handleRandomText(ActionEvent actionEvent) {
    }
}
