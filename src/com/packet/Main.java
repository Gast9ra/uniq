package com.packet;


import java.io.FileNotFoundException;
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
        ArrayList<String> result = new ArrayList<String>();
        if (text.size() != 0) {
            int j = 0;
            int c = 0; //переменая колличества одинаковых строк
            result.add(text.get(0));
            for (int i = 1; i < text.size(); i++) {

                if (comparison(result.get(j), text.get(i), argPosition.isI(),argPosition.getsCount())) {
                    c++;
                } else {
                    if (!argPosition.isU()) {

                        //добавление аргумента флага С в массив результата
                        if (result.size() >= 1 && argPosition.isC())
                            result.set(j, "\"" + c + "\"" + result.get(j));

                        result.add(text.get(i));
                        j++;

                    } else if (c < 1) {

                        if (text.size() - 1 > i + 1 &&
                                !comparison(result.get(j), text.get(i + 1), argPosition.isI(),argPosition.getsCount())) {
                            result.add(text.get(i));
                            j++;
                        }else result.add(text.get(i));

                    }
                    c = 0;
                }
            }
            if (!argPosition.isO()) {
                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i));
                }
            }else{
                fileDo.writeLine(argPosition.getOutput(),result);
            }

        }
    }

    private static boolean comparison(String first, String second, Boolean flagI,int sCount) {
        if (flagI) {
            if (first.length() > sCount && second.length() > sCount) {
                return first.substring(sCount).toLowerCase().equals(second.substring(sCount).toLowerCase());
            }else return false;
        } else
        if (first.length() > sCount && second.length() > sCount) {
            return first.substring(sCount).equals(second.substring(sCount));
        }else return false;
    }
}