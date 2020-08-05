package ru.krt.copypast.jsrunner;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

public class JsRunner extends DefaultTask {
    @SuppressWarnings("removal")
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
    FilesystemFolder currentNpmprojectPath;
    Module require;
    @Input
    private String expecteds, actuals, toActual, toExpected, toNodemodules;

    @TaskAction
    private void runJs() throws ScriptException, FileNotFoundException {
        currentNpmprojectPath = FilesystemFolder.create(new File(toNodemodules), "UTF-8");
        require = Require.enable(engine, currentNpmprojectPath);
        engine.eval(new FileReader("src/main/javascript/prepare.js"));
        engine.eval(new FileReader("src/main/javascript/compare.js"));
    }

    public String getExpecteds() {
        return expecteds;
    }

    public void setExpecteds(String expecteds) {
        this.expecteds = expecteds;
    }

    public String getActuals() {
        return actuals;
    }

    public void setActuals(String actuals) {
        this.actuals = actuals;
    }

    public String getToActual() {
        return toActual;
    }

    public void setToActual(String toActual) {
        this.toActual = toActual;
    }

    public String getToExpected() {
        return toExpected;
    }

    public void setToExpected(String toExpected) {
        this.toExpected = toExpected;
    }

    public String getToNodemodules() {
        return toNodemodules;
    }

    public void setToNodemodules(String toNodeModules) {
        this.toNodemodules = toNodeModules;
    }

}
