package com.company;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<File> incomingFiles = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket svrsocket = new ServerSocket(8080);
            Socket ss = svrsocket.accept();

            int fileCount = 0;
            boolean loop = true;
            while(loop){
                Scanner scanner = new Scanner(ss.getInputStream());
                long size = scanner.nextLong();
                byte[] file = new byte[Integer.parseInt(String.valueOf(size))];
                InputStream stream = ss.getInputStream();
                //DataInputStream Dstream = new DataInputStream(stream);

                //Stream may be reading too much. Need to limit how much it reads
                int count = stream.read(file);

                Scanner scannera = new Scanner(ss.getInputStream());
                String fileName = scannera.nextLine();

                System.out.println(fileName);
                File fileIntoArray = new File("C:\\TempFile\\", fileName);
                FileOutputStream fos = new FileOutputStream(fileIntoArray);
                fos.write(file);
                incomingFiles.add(fileIntoArray);

                if(scanner.nextLine().equals("End")){
                    loop = false;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
