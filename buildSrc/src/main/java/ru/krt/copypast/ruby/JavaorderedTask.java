package ru.krt.copypast.ruby;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class JavaorderedTask extends DefaultTask {
    HamlStarter hamlRunner;
//    MainScript mainScript;

    @TaskAction
    private void mainTask() {
        hamlRunner = new HamlStarter(getProject());
//        nashornRunner = new NashornRunner();
//        mainScript = new MainScript();
    }

}
