package com.packet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.exists;


/**
 * Created by Gast9ra on 30.03.2017.
 */
public class FileWork {





    public static void main(String[] args) throws FileNotFoundException {
        String inFile = "output\\filetest.txt";
        String text ="тестовый тект который нужен \ntes15295 \n";
        File way = new File (inFile);
        FileWork kio=new FileWork();
        kio.write(new File (inFile).getPath(),text);
        System.out.println(kio.read(way.getPath()));
        way.delete();
    }


    public  void write(String fileName, String text) {
        //Определяем файл
        File file = new File(fileName);
        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) file.createNewFile();
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String fileName,List<String> text){
        //Определяем файл
        File file = new File(fileName);
        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) file.createNewFile();
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                for (int i=0;i<text.size();i++) {
                    out.println(text.get(i));
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  String read(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);

        if (!exists(file.toPath()))
            throw new FileNotFoundException("File not found");

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {

                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public  List<String> readList(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        ArrayList<String> stringList=new ArrayList<>();

        if (!exists(file.toPath()))
            throw new FileNotFoundException("File not found");

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    stringList.add(s);
                }
            } finally {

                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return stringList;
    }

    public  void update(String nameFile, String newText) throws FileNotFoundException {
        File file= new File(nameFile);
        if(!exists(file.toPath()))
            throw new FileNotFoundException("File not found");
        StringBuilder sb = new StringBuilder();
        String oldFile = read(nameFile);
        sb.append(oldFile);
        sb.append(newText);
        write(nameFile, sb.toString());
    }

}
