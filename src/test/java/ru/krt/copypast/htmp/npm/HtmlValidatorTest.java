package ru.krt.copypast.htmp.npm;

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

public class HtmlValidatorTest{

    @SuppressWarnings("removal")
    static NashornScriptEngine engine;
    static Module require;

    String
          srcStandard = new String(Files.readAllBytes(Paths.get("node_modules/haml/test/standard.html")))
        ;

    public HtmlValidatorTest() throws IOException {
    }

    @SuppressWarnings("removal")
    @Test
//    @Ignore
    public void simpleTest() throws ScriptException, FileNotFoundException {
        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        require = Require.enable(engine, FilesystemFolder.create(new File(""), "UTF-8"));
        engine.eval("load('node_modules/html-validator/lib/w3c-validator.js');");
//        engine.eval(new FileReader("src/javascript/valid.js"));
//        engine.eval("function process(srcHtml) {return Haml.render(srcHaml);}");
        try {
            ((Invocable)engine).invokeFunction("request", srcStandard);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("");
    }

}
