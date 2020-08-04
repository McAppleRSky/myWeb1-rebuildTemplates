package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;

/*
 * https://gist.github.com/ignaciolg/5977b73e6f61bf33f68a9558403737cb
 * https://www.n-k.de/riding-the-nashorn/
 * https://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial
 * https://github.com/bem/html-differ/blob/master/README.ru.md
 * https://habr.com/ru/post/195870
 * https://github.com/malaporte/nashorn-commonjs-modules
 * https://github.com/provegard/nashorn-require
 * https://github.com/walterhiggins/commonjs-modules-javax-script
 * https://github.com/walterhiggins/commonjs-modules-javax-script/blob/master/README.md
 * */

public class NashornScripter implements ScripterProps {
    @SuppressWarnings("removal")
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName(engineName);
    FilesystemFolder currentNpmprojectPath = FilesystemFolder.create(new File(pathName), encoding);
//    Module require;
}
