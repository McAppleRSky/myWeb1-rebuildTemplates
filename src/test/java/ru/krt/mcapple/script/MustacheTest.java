package ru.krt.mcapple.script;
import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MustacheTest {
    String json, html;
    ScriptEngineManager factory ;
    ScriptEngine engine;
    String string;

    @Before
    public void beforeTest() throws IOException {
            json = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/mustache/input.json")));
            html = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/mustache/input.html")));

        factory = new ScriptEngineManager();
        engine = factory.getEngineByName("nashorn");
    }

    @Test
    @Ignore
    public void simpleTest(){
        try {
            engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/mustache/mustache.js');");
            engine.eval("function progress(content, parametrs {return Mustache.render(content, JSON.parce(parameters));}");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        Invocable invocable = (Invocable) engine;
//        System.out.println(invocable.invokeFunction("process", html, json));
        try {
            string = (String) invocable.invokeFunction("process", html, json);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(string);
    }

}
