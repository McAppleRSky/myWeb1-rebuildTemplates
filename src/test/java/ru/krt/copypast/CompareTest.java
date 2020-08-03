package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.junit.Test;

import javax.script.ScriptEngineManager;
import java.io.*;

import static org.junit.Assert.assertNotNull;
import static ru.krt.copypast.ScripterProps.JvmNpmScript;

public class CompareTest implements ScripterProps{
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName(engineName);
    FilesystemFolder currentNpmprojectPath;
    Module require;

    @Test
    public void loadingFromFilesystem() throws Throwable{
        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName(engineName);
        engine.eval(JvmNpmScript);
        currentNpmprojectPath = FilesystemFolder.create(new File(pathName), encoding);
        require = Require.enable(engine, currentNpmprojectPath);
        engine.eval(new FileReader("src/main/javascript/compare.js"));
    }

}
