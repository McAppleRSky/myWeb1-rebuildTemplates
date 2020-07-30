package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Require;
import com.coveo.nashorn_modules.Module;
import jdk.nashorn.api.scripting.NashornScriptEngine;

//import com.coveo.nashorn_modules.Require;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

import static org.junit.Assert.assertNotNull;

public class CompareTest //extends EvalScript
                            {
//    EvalScript evalScript = new EvalScript();
    /*    ScriptEngineManager factory ;
    //ScriptEngine engine ;
    NashornScriptEngine engine;
    FilesystemFolder rootFolder;
*/
//    EvalScript evalScript;
//    NashornScriptEngine nashornEngine //= getEngine()
        ;

//    String
//        scriptFile = //"src/main/javascript/compare.js"
//                                "./compare"
//    ,script = String.format("require('%s')", scriptFile)
//    ;

//    @Test
//    @Ignore
//    public void testNode() throws FileNotFoundException {
//        try {nashornEngine.eval(script);} catch (ScriptException e) {e.printStackTrace();}
//    }

//    FilesystemFolder root = FilesystemFolder.create(new File("/mnt/d/projects/myWeb1-rebuildTemplates"), "UTF-8");
//    ResourceFolder root =
//        ResourceFolder.create(getClass().getClassLoader(), "ru/krt/copypast", "UTF-8");

    File file;
    FilesystemFolder root ;
    NashornScriptEngine engine;
    Module require;

    @Test
    @Before
    public void before() throws Throwable{
        file = new File("");
        root = FilesystemFolder.create(file, "UTF-8");
        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        require = Require.enable(engine, root);
    }

    @Test
    public void requireTest() throws ScriptException {
        assertNotNull(require);
        engine.eval("load('./jvm-npm.js');");
        engine.eval("require('');");
    }

    @Test
    public void htmlTest() throws ScriptException {
    }
}
