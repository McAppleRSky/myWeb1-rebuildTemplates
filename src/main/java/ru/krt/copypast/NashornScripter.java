package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;

/*
 * https://github.com/malaporte/nashorn-commonjs-modules
 * https://github.com/provegard/nashorn-require
 * https://github.com/walterhiggins/commonjs-modules-javax-script
 * https://github.com/walterhiggins/commonjs-modules-javax-script/blob/master/README.md
 * */

public class NashornScripter implements ScripterProps {
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName(engineName);
    FilesystemFolder currentNpmprojectPath = FilesystemFolder.create(new File(pathName), encoding);
//    Module require;
}
