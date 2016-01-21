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
                        new FileInputStream(sourceFile), Charset.forName("UTF-8")));
                PrintWriter pw = new PrintWriter(f, "UTF-8");
                String s;

                while( (s = bs.readLine()) != null) {
                        pw.println(s);
                        char[] c = s.toCharArray();
                        System.out.println(s);
                        Thread.sleep(1000L);
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
