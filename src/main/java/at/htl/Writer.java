package at.htl;

import javafx.scene.control.TextArea;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * @timeline .
 * 21.01.2016: PON 001  created class
 * 21.01.2016: PON 090  writing files
 */
public class Writer {

    private List<File> files = new LinkedList<>();

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void startWritingFromFile(File sourceFile, File target, TextArea ta) throws IOException {

        File f = new File(target.getPath() + "/" + sourceFile.getName());

        if (!f.exists())
            f.createNewFile();

        Logger log = new Logger();
        log.AddLine("Start writing " + sourceFile.getName());
        log.List(ta);

        new Thread(() -> {
            try {
                BufferedReader bs = new BufferedReader(new InputStreamReader(
                        new FileInputStream(sourceFile), Charset.forName("UTF-8")));
                PrintWriter pw = new PrintWriter(f, "UTF-8");
                String s;

                while ((s = bs.readLine()) != null) {
                    pw.println(s);

                    //System.out.println(s);
                    Thread.sleep(100L);
                    pw.flush();
                }

                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startWritingRandomText(File target) throws IOException {

        File f = new File(target.getPath() + "/" + "randomtext.java");

        if (!f.exists())
            f.createNewFile();

        new Thread(() -> {
            BufferedReader bs = null;

            try {
                bs = new BufferedReader(new InputStreamReader(
                        new FileInputStream("ViewController.java"), Charset.forName("UTF-8")));
                PrintWriter pw = new PrintWriter(f, "UTF-8");
                String s;

                while ((s = bs.readLine()) != null) {
                    pw.println(s);

                    //System.out.println(s);

                    pw.flush();
                    Thread.sleep(100L);
                }
                System.out.println("Finished!");
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startWritingFromProject(File folder, File sourceDirectory) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                startWritingFromProject(fileEntry, sourceDirectory);
            } else {
                for (EnumEndings ending : EnumEndings.values()) {
                    if (fileEntry.getName().endsWith("." + ending.toString())) {
                        System.out.println("FILE NAME: "+fileEntry.getAbsoluteFile());
                        files.add(fileEntry);
                        System.out.println("ITS A FILE");
                    }
                }
            }
        }
    }


}
