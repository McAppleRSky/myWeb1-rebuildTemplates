package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.junit.Test;

import javax.script.ScriptEngineManager;
import java.io.*;

import static org.junit.Assert.assertNotNull;
//import static ru.krt.copypast.ScripterProps.JvmNpmScript;

public class CompareTest {
    @SuppressWarnings("removal")
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
    FilesystemFolder currentNpmprojectPath;
    Module require;

    @Test
    public void loadingFromFilesystem() throws Throwable{
//        engine.eval(JvmNpmScript);
        currentNpmprojectPath = FilesystemFolder.create(new File(""), "UTF-8");
        require = Require.enable(engine, currentNpmprojectPath);
        engine.eval(new FileReader("src/main/javascript/prepare.js"));
        engine.eval(new FileReader("src/main/javascript/compare.js"));
    }

}
