package ru.krt.copypast.htmp.npm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HtmlValidator {

    Process process = null;
    ProcessBuilder processBuilder;

    InputStream stdout;
    InputStreamReader isrStdout;
    BufferedReader brStdout;

    String line = null;
    int exitVal;

    public HtmlValidator(String path2html) {
        if(!path2html.isEmpty()){
            processBuilder = new ProcessBuilder(
                    "node_modules/.bin/html-validator"
                    ,"--file="+path2html
                    ,"--verbose"
            );

            // перенаправляем стандартный поток ошибок на
            // стандартный вывод
            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            try {
                process = processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                exitVal = process.waitFor();
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("");
        }

        System.out.println("");
    }

}
