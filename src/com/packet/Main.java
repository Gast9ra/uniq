package com.packet;


import java.io.FileNotFoundException;
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
        List text = fileDo.readList(argPosition.getInput()); //разбитие файла на массив строк
        for (int i = 0; i < text.size(); i++) {
            int c = 1; //переменая колличества одинаковых строк
            if (i + 1 < text.size() - 1) {
                if (text.get(i).equals(text.get(i + 1))) {
                    c++;
                    text.remove(i + 1);
                    for (int j = i + 1; j < text.size(); j++) //поиск одинаковых подряд идущих
                        if (text.get(i).equals(text.get(j))) {
                            text.remove(j);
                            c++;
                            j--;
                        } else {
                            if (argPosition.isC()) {
                                String intervalRes = "\"" + c + "\"" + text.get(i);
                                text.set(i, intervalRes);
                            }
                            c = 1;
                            break;
                        }
                }
            }
        }
        if(!argPosition.isO()) {
            for (int i = 0; i < text.size(); i++) {
                System.out.println(text.get(i));
            }
        }
    }


}
