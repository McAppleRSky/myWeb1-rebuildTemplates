package ru.krt.mcapple.script;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//import jdk.nashorn.api.scripting.JSObject;
//import netscape.javascript.JSObject;
//import javax.script.*;

import static org.junit.Assert.assertEquals;

public class HamlTest extends MyUtils{

    @SuppressWarnings("removal")
    static NashornScriptEngine engine;
    static Module require;

    String
        srcHaml4actual = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/test/alt_attribs.haml")))
        ,expected = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/test/alt_attribs.html")))
//        ,result
        ;

    public HamlTest() throws IOException {
    }

    @SuppressWarnings("removal")
    @Test
    public void simpleTest() throws ScriptException, NoSuchMethodException {
        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        require = Require.enable(engine, FilesystemFolder.create(new File(""), "UTF-8"));
        engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/lib/haml.js');");
//        engine.eval("function process(srcHaml){return Haml.compile(srcHaml);}");
//        function process(srcHaml) {result = Haml.compile(srcHaml); console.log(result);return Haml.compile(srcHaml);}
        engine.eval("var console = {log: print,};function process(srcHaml) {result = Haml.compile(srcHaml); console.log(result);return Haml.compile(srcHaml);}");

        Invocable invocable = (Invocable) engine;

        byte[] bytes = new byte[0];
        String string;

        Object object = invocable.invokeFunction("process", srcHaml4actual);
        string = String.valueOf(object);
//        object.
//        bytes = SerializationUtils.serialize((Serializable) object);

//        result = fromJavaToByteArray((Serializable) invocable.invokeFunction("process", srcHaml4actual));


        //        BufferedInputStream inputStream = null;
//        inputStream = new BufferedInputStream((InputStream) invocable.invokeFunction("process", srcHaml4actual));

//        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)
//        String result = CharStreams.toString(reader);

//        try {
//            Document document = Jsoup.parse((String) invocable.invokeFunction("process", srcHaml4actual));
//            InputStream inputStream = new Input;
//            inputStream.
//            byte[] arr = (byte[]) invocable.invokeFunction("process", srcHaml4actual);
//            Object result = invocable.invokeFunction("process", srcHaml4actual).readAllBytes();
//            String result = document.outerHtml();
//        string = result.toString();
//            assertEquals(expected, string);
        System.out.println("");
    }

}
