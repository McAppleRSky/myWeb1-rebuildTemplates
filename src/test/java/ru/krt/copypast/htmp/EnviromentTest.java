package ru.krt.copypast.htmp;

import org.junit.Ignore;
import org.junit.Test;
import ru.krt.copypast.util.ToolFunc;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class EnviromentTest {

    @Test
    @Ignore
    public void envRubyTest() throws IOException {
        Process processWin = null;
        processWin = new ProcessBuilder("CMD", "/C", "ver").start();
        if ( ToolFunc.notNull(processWin) ){
        }
    }

    @Test
//    @Ignore
    public void envProcessBuilderTest() //throws IOException
    {
        Process process = null
            ,tstWinProcess = null
            ,tstLinProcess = null
            ;
        ProcessBuilder processBuilder;

        try {
            tstWinProcess = new ProcessBuilder("CMD", "/C", "ver").start();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        try {
            tstLinProcess = new ProcessBuilder("cat", "/etc/*-release", "").start();
        } catch (IOException e) {
//            e.printStackTrace();
        }

        processBuilder = new ProcessBuilder();
        Map<String, String> env = processBuilder.environment();

        assertTrue(ToolFunc.notNull(tstWinProcess) ^ ToolFunc.notNull(tstLinProcess));

        if(ToolFunc.notNull(tstWinProcess)) {
            processBuilder = new ProcessBuilder(
                    "CMD", "/C"
                    , "haml"
                    , "node_modules/haml/test/alt_attribs.haml"
                    , "tmp.htm"
            );
        }else{
            if(ToolFunc.notNull(tstLinProcess)){
                processBuilder = new ProcessBuilder(
//                        "CMD", "/C",
                        "haml"
                        , "node_modules/haml/test/alt_attribs.haml"
                        , "tmp.htm"
                );
            }else{
                System.out.println("Sys env wrong");
            }
            // перенаправляем стандартный поток ошибок на
            // стандартный вывод
            processBuilder.redirectErrorStream(true);
            // запуск программы
//            Process process = null;
            try {
                process = processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // читаем стандартный поток вывода
            // и выводим на экран
            InputStream stdout = process.getInputStream();
            InputStreamReader isrStdout = new InputStreamReader(stdout);
            BufferedReader brStdout = new BufferedReader(isrStdout);
            String line = null;
            while(true) {
                try {
                    if (!((line = brStdout.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(line);
            }
            // ждем пока завершится вызванная программа
            // и сохраняем код, с которым она завершилась в
            // в переменную exitVal
            try {
                int exitVal = process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue( ToolFunc.notNull(process) );
        }
        //        System.out.println("");
    }

}
