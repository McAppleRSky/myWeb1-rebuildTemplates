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

public class HtmlDifferInvoker {
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
                    ,string
                ;
            String[] arrayString;
//            byte[] srcHtml1 = null;
            if(!hamlFilenameKey.trim().isEmpty()){
//                srcHaml = new String(Files.readAllBytes(Paths.get(hamlSourcesDir+hamlFilenameKey)));
                htmlFilenameValue = jsonObj.get(hamlFilenameKey).toString().split(Pattern.quote("."))[0];
//        engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/lib/haml.js');");
                try {
                    srcActual = new String(Files.readAllBytes(Paths.get(htmExpectedDir,htmlFilenameValue+".htm")));
                    srcExpected = new String(Files.readAllBytes(Paths.get(HtmlActualDir,htmlFilenameValue+".html")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    invocable.invokeFunction("process" ,srcActual ,srcExpected);
                } catch (ScriptException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println("");
    }
}
