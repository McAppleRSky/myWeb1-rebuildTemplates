package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Require;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.ResourceFolder;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

import static org.junit.Assert.assertNotNull;

public class CompareTest {

    FilesystemFolder rootPath;
    NashornScriptEngine engine;
    Module require;
    ResourceFolder resourceFolder;

    @Test
    @Before
    public void loadingFromFilesystem() throws Throwable{
        engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("load('./node_modules/jvm-npm/src/main/javascript/jvm-npm.js');");
        rootPath = FilesystemFolder.create(new File(""), "UTF-8");
        require = Require.enable(engine, rootPath);
        engine.eval(new FileReader("src/main/javascript/compare.js"));
    }

}
