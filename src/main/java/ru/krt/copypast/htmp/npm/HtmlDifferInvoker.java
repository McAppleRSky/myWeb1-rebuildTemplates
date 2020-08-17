package ru.krt.copypast.htmp.npm;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.json.JSONObject;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class HtmlDifferInvoker {

//    private static final Logger logger = LogManager.getLogger();

    private static
    String json = "hamlsQueue.json"
        ,htmExpectedDir = "../myWeb1/"
        ,HtmlActualDir = "rebuild/"
        ;
    private static FileReader prepareJsReader, compareJsReader;

    @SuppressWarnings("removal")
    static NashornScriptEngine engine;
    static Module require;

    public static void main(String[] args){
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(new String(Files.readAllBytes(Paths.get(json))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator iterator = jsonObj.keys();

        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");

        try {
            prepareJsReader = new FileReader("src/javascript/prepare.js");
            compareJsReader = new FileReader("src/javascript/compare.js");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            require = Require.enable(engine, FilesystemFolder.create(new File(""), "UTF-8"));
            engine.eval(prepareJsReader);
            engine.eval(compareJsReader);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        Invocable invocable = (Invocable)engine;

        while(iterator.hasNext()){
            String hamlFilenameKey = iterator.next().toString()
                ,htmlFilenameValue
                ,srcActual = null
                ,srcExpected = null
                ,actualFilename
                ;
//            String[] arrayString;
//            byte[] srcHtml1 = null;
            if(!hamlFilenameKey.trim().isEmpty()){
                htmlFilenameValue = jsonObj.get(hamlFilenameKey).toString().split(Pattern.quote("."))[0];
                actualFilename = htmlFilenameValue+".html";
                try {
                    srcActual = new String(Files.readAllBytes(Paths.get(htmExpectedDir,htmlFilenameValue+".htm")));
                    srcExpected = new String(Files.readAllBytes(Paths.get(HtmlActualDir, actualFilename)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                logger.info("FILE: " + Paths.get(HtmlActualDir,htmlFilenameValue+".html"));
                try {
                    invocable.invokeFunction("process" ,srcActual ,srcExpected, actualFilename);
                } catch (ScriptException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
        }
//        System.out.println("");
    }
}
