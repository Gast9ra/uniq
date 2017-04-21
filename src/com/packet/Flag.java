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
                        if (argList.length >= l) {
                            output = argList[l + 1];
                            if (output.length() < 0 || output == null) {
                                output = null;
                                throw new IllegalArgumentException("not valid name");
                            }
                        } else
                            throw new IllegalArgumentException("not valid name");
                    default:
                        break;
                }
            }
        }
        input = argList[argList.length - 1];
        if (input.length() < 1 || input == null) {
            input = null;
            throw new IllegalArgumentException("not valid name");
        }
        if (!new File(output).canWrite())
            throw new IllegalArgumentException("not valid name");
        if (!new File(input).canRead())
            throw new IllegalArgumentException("not valid name");
        if (output.equals(input)) throw new IllegalArgumentException("input=output");

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

    public boolean isS() {
        return s;
    }

    public boolean isO() {
        return o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flag)) return false;

        Flag flag = (Flag) o;

        if (!getInput().equals(flag.getInput())) return false;
        return getOutput().equals(flag.getOutput());
    }

    @Override
    public int hashCode() {
        int result = getInput().hashCode();
        result = 31 * result + getOutput().hashCode();
        return result;
    }
}
