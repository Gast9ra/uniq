package com.packet;

import java.io.File;

/**
 * Created by Gast9ra on 20.04.2017.
 */
public class Flag {
    private String input, output;
    private int sCount = 0;
    private boolean i = false, u = false, c = false, s = false, o = false;

    public Flag(String[] argList) {
        if (argList.length == 0) throw new ArrayIndexOutOfBoundsException("have not command");
        for (int l = 0; l < argList.length; l++) {
            if (argList[l].length() != 0) {
                switch (argList[l]) {
                    case "-i":
                        i = true;
                        break;
                    case "-u":
                        u = true;
                        break;
                    case "-c":
                        c = true;
                        break;
                    case "-s":
                        s = true;
                        sCount = Integer.parseInt(argList[l + 1]);
                        if (sCount < 0) {
                            sCount = 0;
                            throw new IllegalArgumentException("not valid number");
                        }
                        break;
                    case "-o":
                        o = true;
                            output = argList[l + 1];
                            if (output == null || output.length() < 0) {
                                output = null;
                                throw new IllegalArgumentException("not valid name");
                            }
                    default:
                        break;
                }
            }
        }
        input = argList[argList.length - 1];
        if (input == null || input.length() < 1) {
            input = null;
            throw new IllegalArgumentException("not valid name");
        }
        if (output.equals(input)) throw new IllegalArgumentException("input=output");
        if (!new File(input).canRead())
            throw new IllegalArgumentException("not valid name");
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public int getsCount() {
        return sCount;
    }

    public boolean isI() {
        return i;
    }

    public boolean isU() {
        return u;
    }

    public boolean isC() {
        return c;
    }

    public boolean isO() {
        return o;
    }


}
