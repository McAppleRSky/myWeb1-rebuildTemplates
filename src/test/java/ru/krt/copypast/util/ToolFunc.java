package ru.krt.copypast.util;

public class ToolFunc {

    public static boolean notNull(Object object){
        boolean result = true;
        if (object == null) result = false;
        return result;
    }

}
