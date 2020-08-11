package ru.krt.copypast.hamlscript;

import org.gradle.api.Project;

public class NashornRunner //implements Sources
                                                {

    private Project mainProjectSources;

    @SuppressWarnings("removal")
//    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
//    FilesystemFolder currentNpmprojectPath;
//    Module require;

    public void  runNashorn() {
        //engine.eval(new FileReader("src/main/javascript/prepare.js"));
        //engine.eval(new FileReader("src/main/javascript/compare.js"));

//        System.out.println("");
    }

    public NashornRunner(Project projectSources) {
        this.mainProjectSources = projectSources;
//        currentNpmprojectPath = FilesystemFolder.create(mainProjectSources.getProject().getProjectDir(), "UTF-8");
        //require = Require.enable(engine, currentNpmprojectPath);

//        runNashorn();
//        TaskQueue taskQueue = new TaskQueue(mainProjectSources.getProject().getProjectDir().getPath());
        System.out.println("");
    }

}
