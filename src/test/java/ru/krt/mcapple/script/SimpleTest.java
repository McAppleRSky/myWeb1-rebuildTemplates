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

import static org.junit.Assert.assertEquals;

public class SimpleTest implements Expected{
    String json = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/mustache/input.json")))
            ,html = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/mustache/input.html")))
            ,actual
//            ,expected = "<html>\r\n<head>\r\n\t<title>duke</title>\r\n</head>\r\n<body>\r\n\t<h1>hey java</h1>\r\n</body>\r\n</html>"
            ;
    ScriptEngineManager factory ;
    ScriptEngine engine;

    @Test
    public void mustacheTest() throws ScriptException {

        factory = new ScriptEngineManager();
        engine = factory.getEngineByName("nashorn");

        engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/mustache/mustache.js');");
        engine.eval("function process(content,parameters){return Mustache.render(content, JSON.parse(parameters));}");

        Invocable invocable = (Invocable) engine;
//        System.out.println(invocable.invokeFunction("process", html, json));
        try {
            actual = (String) invocable.invokeFunction("process", html, json);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }

    public SimpleTest() throws IOException {
    }

}
