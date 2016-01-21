package at.htl;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by patrickpohn on 21/01/16.
 */
public class Writer {
    public void startWriting(File sourceFile, File target) throws IOException {

        File f = new File(target.getPath() + "/" + sourceFile.getName());

        if(!f.exists())
            f.createNewFile();

        new Thread(() -> {
            try {
                BufferedReader bs = new BufferedReader(new InputStreamReader(
                        new FileInputStream(sourceFile), Charset.forName("UTF-16")));
                PrintWriter pw;

                for (char c : bs.readLine().toCharArray()) {
                    pw = new PrintWriter(f,"UTF-8");
                    pw.print(c);
                    Thread.sleep(1000L);
                    pw.close();
                }

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
