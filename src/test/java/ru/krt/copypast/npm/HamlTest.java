package ru.krt.copypast.npm;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.junit.Ignore;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

//import jdk.nashorn.api.scripting.JSObject;
//import netscape.javascript.JSObject;
//import javax.script.*;

import static org.junit.Assert.assertEquals;

public class HamlTest{

    @SuppressWarnings("removal")
    static NashornScriptEngine engine;
    static Module require;

    String
//        srcHaml4actual = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/test/alt_attribs.haml")))
//        ,expected = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/test/alt_attribs.html")))
    srcHaml4actual = new String(Files.readAllBytes(Paths.get("node_modules/haml/test/alt_attribs.haml")))
            ,expected = new String(Files.readAllBytes(Paths.get("node_modules/haml/test/alt_attribs.html")))
//        ,result
        ;

    public HamlTest() throws IOException {
    }

    @SuppressWarnings("removal")
    @Test
    @Ignore
    public void simpleTest() throws ScriptException, NoSuchMethodException {
        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        require = Require.enable(engine, FilesystemFolder.create(new File(""), "UTF-8"));
//        engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/lib/haml.js');");
        engine.eval("load('node_modules/haml/lib/haml.js');");
        engine.eval("function process(srcHaml) {return Haml.render(srcHaml);}");
        assertEquals(expected, (String) ((Invocable)engine).invokeFunction("process", srcHaml4actual));
    }

}
