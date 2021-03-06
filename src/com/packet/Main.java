package com.packet;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] a) throws Exception {
        try {
            command(a);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void command(String[] com) throws FileNotFoundException {
        Flag argPosition = new Flag(com); //разбор командной строки
        FileWork fileDo = new FileWork();
        List<String> text = fileDo.readList(argPosition.getInput()); //разбитие файла на массив строк
        ArrayList<String> result = new ArrayList<>();
        if (text.size() != 0) {
            int j = 0;
            int c = 0; //переменая колличества одинаковых строк
            result.add(text.get(0));
            for (int i = 1; i < text.size(); i++) {

                if (comparison(result.get(j)
                        , text.get(i)
                        , argPosition.isI()
                        , argPosition.getsCount())) { //проверка на валидность
                    c++;
                } else {
                    if (!argPosition.isU()) {

                        //добавление аргумента флага С в массив результата
                        if (result.size() >= 1 && argPosition.isC())
                            result.set(j, "\"" + c + "\"" + result.get(j));

                        result.add(text.get(i));
                        j++;
                    }else{
                        if (c < 1){
                            result.add(text.get(i));
                            j++;
                        } else{
                            result.add(text.get(i));
                            result.remove(j);
                        }

                    }

                    c = 0;
                }
            }
            if (c>0 && argPosition.isC()) result.set(j, "\"" + c + "\"" + result.get(j));

            if (argPosition.isO()){
                PrintWriter out=new PrintWriter(argPosition.getOutput());
                writeLine(out,result);
            } else {
                PrintWriter out = new PrintWriter(System.out);
                writeLine(out,result);
            }
        }
    }

    private static boolean comparison(String first, String second, Boolean flagI, int sCount) {
        if (flagI) {
            return first.length() > sCount
                    && second.length() > sCount
                    && first.substring(sCount).toLowerCase()
                    .equals(second.substring(sCount).toLowerCase());
        } else
            return first.length() > sCount
                    && second.length() > sCount
                    && first.substring(sCount)
                    .equals(second.substring(sCount));
    }


    private static void writeLine(PrintWriter fileName, List<String> text) {
        for (int i = 0; i < text.size(); i++) {
            fileName.println(text.get(i));
        }
        fileName.close();
    }

}