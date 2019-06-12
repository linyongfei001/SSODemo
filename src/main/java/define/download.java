package define;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class download {
    public static void main(String[] args) throws IOException {
//        String pathIn = "E:\\\\软件\\\\FeiQ.1060559168.exe";
//        String pathOut = "D:\\out\\feiq.exe";
//        move(pathIn,pathOut);
//        moveBuff(pathIn,pathOut);
        String pathIn = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2383503185,2949228134&fm=26&gp=0.jpg";
        String pathOut = "D:/out/test.jpg";
        downFromNet(pathIn,pathOut);




    }

    private static void downFromNet(String pathIn, String pathOut) throws IOException {
        URL url = new URL(pathIn);
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        FileOutputStream fout = new FileOutputStream(pathOut);
        BufferedInputStream bfin = new BufferedInputStream(in);
        BufferedOutputStream bfout = new BufferedOutputStream(fout);
        int temp;
        while ((temp = bfin.read()) != -1){
            bfout.write(temp);
        }
        bfin.close();
        bfout.close();

    }

    private static void moveBuff(String pathIn, String pathOut) throws IOException {
        FileInputStream fin = new FileInputStream(pathIn);
        FileOutputStream fout = new FileOutputStream(pathOut);
        BufferedInputStream bfin = new BufferedInputStream(fin);
        BufferedOutputStream bfout = new BufferedOutputStream(fout);
        int temp;
        while ((temp = bfin.read()) != -1){
            bfout.write(temp);
        }
        bfin.close();
        bfout.close();


    }

    public static void move(String pathIn, String pathOut) throws IOException {
        File f = new File(pathIn);
        FileInputStream fin = new FileInputStream(f);
        FileOutputStream fout = new FileOutputStream(pathOut);
//        byte[] b = new byte[(int) f.length()+1];
        byte[] b = new byte[1024];
        int temp;
        while ((temp = fin.read(b)) != -1){
            fout.write(b);
        }

        fin.close();
        fout.close();
    }
}
