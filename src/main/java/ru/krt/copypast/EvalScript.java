package ru.krt.copypast;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;

public class EvalScript implements EvalFile{
    private static ScriptEngineManager factory = new ScriptEngineManager();
    //ScriptEngine engine ;
    private static NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");;
    private static FilesystemFolder rootFolder = FilesystemFolder.create(new File(pathName), encoding);

    public static NashornScriptEngine getEngine() {
        return engine;
    }

    public EvalScript() {
        /*
        * https://github.com/provegard/nashorn-require
        * https://github.com/walterhiggins/commonjs-modules-javax-script
        * https://github.com/walterhiggins/commonjs-modules-javax-script/blob/master/README.md
        * */
        try {
            Require.enable(engine, rootFolder);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try {
            engine.eval(loadJvmNpmJs);
//            engine.eval("load('./jvm-npm.js')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try {
            engine.eval(requireRequireJs);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try {
            engine.eval(requireLibpath);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try {
            engine.eval(requireFs);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

//    public static void setUpNashorn(String[] args) throws Exception {
        // create a script engine manager
//        ScriptEngineManager factory = new ScriptEngineManager();
        // create a Nashorn script engine
//        ScriptEngine engine = factory.getEngineByName("nashorn");
        // evaluate JavaScript statement
//        try {engine.eval("print('Hello, World!');");} //catch (final ScriptException se) { se.printStackTrace();}
//    }
}
