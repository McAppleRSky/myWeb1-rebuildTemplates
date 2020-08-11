package ru.krt.copypast.hamlscript;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class JavaMainTaskRunner extends DefaultTask {
    NashornRunner nashornRunner;
//    MainScript mainScript;

    @TaskAction
    private void mainTask() {
        nashornRunner = new NashornRunner(getProject());
//        nashornRunner = new NashornRunner();
//        mainScript = new MainScript();
    }

}
