package ru.krt.copypast.npm;

import org.junit.Test;
import ru.krt.copypast.util.ToolFunc;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class envProcessBuilderTest{

    //final
    Process execFromWin = null
            , execFromLin = null
            ;
    ProcessBuilder processBuilder;
    Map<String, String> env;

    @Test
    public void envProcessBuilderTest(){
        try {
            execFromWin = new ProcessBuilder("CMD", "/C", "ver").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            execFromLin = new ProcessBuilder("cat", "/etc/*-release", "").start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        processBuilder = new ProcessBuilder();
        env = processBuilder.environment();

        assertTrue(ToolFunc.notNull(execFromWin) ^ ToolFunc.notNull(execFromLin));

//        System.out.println("");
    }

}
