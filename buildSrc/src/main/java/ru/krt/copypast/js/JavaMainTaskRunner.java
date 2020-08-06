package ru.krt.copypast.js;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class JavaMainTaskRunner extends DefaultTask {
    NashornRunner nashornRunner;
    @TaskAction
    private void mainTask() {
        nashornRunner = new NashornRunner(getProject());
    }

}
