package ru.krt.copypast.js;

import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.gradle.api.Project;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class NashornRunner {
    private Project mainProjectSources;
    @SuppressWarnings("removal")
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
    FilesystemFolder currentNpmprojectPath;
    Module require;

    public void  runNashorn() {
        try {
            engine.eval(new FileReader("src/main/javascript/prepare.js"));
            engine.eval(new FileReader("src/main/javascript/compare.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public NashornRunner(Project projectSources) {
        this.mainProjectSources = projectSources;
        currentNpmprojectPath = FilesystemFolder.create(mainProjectSources.getProject().getProjectDir(), "UTF-8");
        try {
            require = Require.enable(engine, currentNpmprojectPath);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        runNashorn();
    }

}
