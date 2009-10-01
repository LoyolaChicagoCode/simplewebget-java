package webget;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Simple example of downloading a document at a given URL to a local file.
 */
public class Main {

  public final static int SIZE = 1024;
  
  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.err.println("usage: java " + Main.class.getName() + " url");
      System.exit(1);
    }

    URL url = new URL(args[0]);
    String fname = new File(url.getPath()).getName();
    URLConnection connection = url.openConnection();
    int size = connection.getContentLength();
    BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
    FileOutputStream out = new FileOutputStream(fname);
    byte[] buffer = new byte[SIZE];
    int available;

    System.out.println("saving " + size + " bytes to " + fname);
    while ((available = in.read(buffer, 0, SIZE)) > 0) {
      System.out.print(".");
      out.write(buffer, 0, available);
    }
    in.close();
    out.close();
    System.out.println();
    System.out.println("done");
  }
}
