package com.packet;

import java.io.*;

import static java.nio.file.Files.exists;


/**
 * Created by Gast9ra on 30.03.2017.
 */
public class FileWork {
    private static String inFile = "C:\\Users\\Gast9ra\\IdeaProjects\\uniq\\uniq\\filetest.txt";
    private static String text ="ghutjgut \ntesy15295 \n";



    public static void main(String[] args) throws FileNotFoundException {
        File way = new File (inFile);
        write(new File (inFile).getPath(),text);
        System.out.println(read(way.getPath()));
        way.delete();
    }


    public static void write(String fileName, String text) {
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


    public static String read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
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

        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    public static void update(String nameFile, String newText) throws FileNotFoundException {
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
