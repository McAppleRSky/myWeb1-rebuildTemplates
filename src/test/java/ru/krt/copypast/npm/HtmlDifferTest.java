package ru.krt.copypast.npm;
import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
//import jdk.nashorn.api.scripting.JSObject;
//import netscape.javascript.JSObject;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.junit.Test;

//import javax.script.*;
import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;


public class HtmlDifferTest implements Expected{

    String actual = "<!DOCTYPE html><html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title></head><body></body></html>"
//         = new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/rebuild/index.html")))
//         ,expected //= new String(Files.readAllBytes(Paths.get("/mnt/d/projects/myWeb1-rebuildTemplates/expected/index.htm")))
        ;

    @SuppressWarnings("removal")
    static NashornScriptEngine engine;
    static Module require;

    public HtmlDifferTest() throws IOException {
    }


    @SuppressWarnings("removal")
    @Test
    public void simpleTest() throws ScriptException, NoSuchMethodException, FileNotFoundException {
        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        require = Require.enable(engine, FilesystemFolder.create(new File(""), "UTF-8"));
        engine.eval(new FileReader("src/test/javascript/prepare.js"));

        Invocable invocable = (Invocable)engine;

        engine.eval(new FileReader("src/test/javascript/compare.js"));
        assertFalse((Boolean) invocable.invokeFunction("process", expected, actual));
    }

}
