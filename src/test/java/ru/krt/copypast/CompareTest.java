package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import com.coveo.nashorn_modules.Require;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

public class CompareTest extends EvalScript{
//    EvalScript evalScript = new EvalScript();
    /*    ScriptEngineManager factory ;
    //ScriptEngine engine ;
    NashornScriptEngine engine;
    FilesystemFolder rootFolder;
*/
//    EvalScript evalScript;
    NashornScriptEngine nashornEngine = getEngine();

    String scriptFile = "src/main/javascript/compare.js"
    ,script = String.format("load('%s')", scriptFile)
    ;

    @Test
    public void testNode() throws FileNotFoundException {
        try {
            nashornEngine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

}
