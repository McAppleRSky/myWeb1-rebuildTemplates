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
    public void envRubyTest() throws IOException {
//        String
//                program = "haml"
//                hamlInputDirArg = "index.haml"
//                ,outputDir =  "rebuild"
//        alt_attribs.haml
//                ,htmlOutputDirArg = Paths.get("..", outputDir,"tmp.html").toString()
//                ,workdir = "node_modules/haml/test";
//                ;
        Process processWin = null;
        processWin = new ProcessBuilder("CMD", "/C", "ver").start();
        if ( ToolFunc.notNull(processWin) ){
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "CMD", "/C"
                    ,"haml"
                    ,"node_modules/haml/test/alt_attribs.haml"
                    ,"tmp.htm"
            );
//            processBuilder.directory(new File(workdir));

            // перенаправляем стандартный поток ошибок на
            // стандартный вывод
            processBuilder.redirectErrorStream(true);

            // запуск программы
            Process process = null;
            process = processBuilder.start();

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
    }

    @Test
//    @Ignore
    public void envProcessBuilderTest(){
        Process processWin = null, processLin = null;
        ProcessBuilder processBuilder;
        try {
            processWin = new ProcessBuilder("CMD", "/C", "ver").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            processLin = new ProcessBuilder("cat", "/etc/*-release", "").start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        processBuilder = new ProcessBuilder();
        Map<String, String> env = processBuilder.environment();

        assertTrue(ToolFunc.notNull(processWin) ^ ToolFunc.notNull(processLin));

//        System.out.println("");
    }

}
