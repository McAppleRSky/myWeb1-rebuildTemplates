package ru.krt.mcapple.script;
import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
//import jdk.nashorn.api.scripting.JSObject;
//import netscape.javascript.JSObject;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

//import javax.script.*;
import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class HtmlTest {
    @SuppressWarnings("removal")
    static
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
//    Bindings scope = engine.getBindings(ScriptContext.ENGINE_SCOPE);
    static FilesystemFolder currentNpmprojectPath;
    static Module require;

//    @SuppressWarnings("removal")
//    JSObject func;

//    ScriptContext context;

    @BeforeClass
    public static void beforeClass(){
        currentNpmprojectPath = FilesystemFolder.create(new File(""), "UTF-8");
        try {
            require = Require.enable(engine, currentNpmprojectPath);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void beforeTest(){
        try {
            engine.eval(new FileReader("javascript/prepare.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("removal")
    @Test
    public void simpleTest() throws ScriptException, NoSuchMethodException {
        String tst = "<!DOCTYPE html><html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title></head><body></body></html>";
        Invocable invocable = (Invocable) engine;

//        context = new SimpleScriptContext();
//        context.setBindings(engine.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);

            invocable.invokeFunction("script", tst);
//        func = (JSObject)scope.get("script");
//        func.call(null, tst);
//            invocable.invokeFunction("script", tst);
//            assertTrue((boolean)true);

        System.out.println("");
    }

}
