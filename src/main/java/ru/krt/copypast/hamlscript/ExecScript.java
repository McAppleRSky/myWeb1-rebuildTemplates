package ru.krt.copypast.hamlscript;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.json.JSONObject;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class ExecScript {
    private static
    String json = "hamlsQueue.json"
        ,hamlSourcesDir = "templates/"
        ,HtmlOutputDir = "rebuild/"
        ;

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
            require = Require.enable(engine, FilesystemFolder.create(new File(""), "UTF-8"));
            engine.eval("load('node_modules/haml/lib/haml.js');");
            engine.eval("function process(srcHaml) {return Haml.render(srcHaml);}");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        while(iterator.hasNext()){
            String hamlFilenameKey = iterator.next().toString()
                ,htmlFilenameValue
                ,srcHaml = null
                ,srcHtml = null
                ;
            byte[] srcHtml1 = null;
            if(!hamlFilenameKey.trim().isEmpty()){
                try {
                    srcHaml = new String(Files.readAllBytes(Paths.get(hamlSourcesDir+hamlFilenameKey)));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                htmlFilenameValue = jsonObj.get(hamlFilenameKey).toString();


//        engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/lib/haml.js');");
                try {
                    srcHtml = (String)((Invocable)engine).invokeFunction("process", srcHaml);
                } catch (ScriptException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                srcHtml1 = srcHtml.getBytes(StandardCharsets.UTF_8);

                try {
                    Files.write(Paths.get(HtmlOutputDir+htmlFilenameValue), srcHtml1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("");
    }
}
