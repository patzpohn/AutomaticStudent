package at.htl;

import javafx.scene.control.TextArea;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @timeline .
 * 21.01.2016: PON 001  created class
 * 21.01.2016: PON 090  writing files
 */
public class Writer {
    public void startWritingFromFile(File sourceFile, File target, TextArea ta) throws IOException {

        File f = new File(target.getPath() + "/" + sourceFile.getName());

        if(!f.exists())
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

                while( (s = bs.readLine()) != null) {
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
}
