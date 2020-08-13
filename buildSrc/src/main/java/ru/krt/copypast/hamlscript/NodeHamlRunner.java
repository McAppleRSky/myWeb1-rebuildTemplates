package ru.krt.copypast.hamlscript;

import org.gradle.api.Project;
import org.json.JSONObject;
import ru.krt.copypadt.util.ToolFunc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/*
* https://mkyong.com/java/how-to-execute-shell-command-from-java/
*
* */

public class NodeHamlRunner{

    private final String mainProjectPath;
    private Project mainProjectSources;

    ProcessBuilder processBuilder;
//    Map<String, String> env;

    //final
//    Process
//            execw = null
//            ,execl = null
//            ;

    @SuppressWarnings("removal")
//    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
//    FilesystemFolder currentNpmprojectPath;
//    Module require;

    private static
    String
//            filelist = "filelist"
            filelist = "hamlsQueue.json"
            ,hamlInputDir = "templates"
            ,HtmlOutputDir = "rebuild"
            ;

    public NodeHamlRunner(Project projectSources) {
        this.mainProjectSources = projectSources;
        this.mainProjectPath = mainProjectSources.getProject().getProjectDir().getPath();
//        currentNpmprojectPath = FilesystemFolder.create(mainProjectSources.getProject().getProjectDir(), "UTF-8");
        //require = Require.enable(engine, currentNpmprojectPath);

//        runNashorn();
//        TaskQueue taskQueue = new TaskQueue(mainProjectSources.getProject().getProjectDir().getPath());

        try {
            if(ToolFunc.notNull(new ProcessBuilder("bash", "-c", "").start())){
/*                hamlQueue(mainProjectSources
                                .getProject().getProjectDir()
                                .listFiles(new FilterChild(hamlInputDir))[0]
                        ,mainProjectSources
                                .getProject().getProjectDir()
                                .listFiles(new FilterChild(HtmlOutputDir))[0]
                        ,mainProjectSources
                                .getProject().getProjectDir()
                                .listFiles(new FilterFile(filelist, ".json"))[0]
                        );
    OR
try (Stream<Path> stream = Files.find(Paths.get("Folder 1"), 5,
            (path, attr) -> path.getFileName().toString().equals("Myfile.txt") )) {
        System.out.println(stream.findAny().isPresent());
} catch (IOException e) {
        e.printStackTrace();
}
https://fooobar.com/questions/28326/how-to-combine-paths-in-java
                hamlQueue(Paths.get(mainProjectPath).resolve(hamlInputDir).toString(),
                        Paths.get(mainProjectPath).resolve(HtmlOutputDir).toString(),
                        Paths.get(mainProjectPath, filelist));
*/
                hamlQueue(Paths.get(mainProjectPath, hamlInputDir).toString(),
                        Paths.get(mainProjectPath, HtmlOutputDir).toString(),
                        Paths.get(mainProjectPath, filelist));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("");
    }

    public void hamlQueue(String inputDir, String outputDir, Path filelist) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(new String(Files.readAllBytes(filelist)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator iterator = jsonObj.keys();
        while(iterator.hasNext()){
            String hamlFilenameKey = iterator.next().toString()
                    ,htmlFilenameValue
//                    ,srcHaml = null
//                    ,srcHtml = null
                    ;
//            byte[] srcHtml1 = null;
            if(!hamlFilenameKey.trim().isEmpty()){
//                    srcHaml = new String(Files.readAllBytes(Paths.get(hamlSourcesDir+hamlFilenameKey)));
                htmlFilenameValue = jsonObj.get(hamlFilenameKey).toString();
//        engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/haml/lib/haml.js');");
//                srcHtml = (String)((Invocable)engine).invokeFunction("process", srcHaml);
//                srcHtml1 = srcHtml.getBytes(StandardCharsets.UTF_8);
//                Files.write(Paths.get(HtmlOutputDir+htmlFilenameValue), srcHtml1);
                ExternalProgramLauncher("haml",
                        Paths.get(inputDir,hamlFilenameKey).toString(),
                        Paths.get(outputDir,htmlFilenameValue).toString());
            }
        }
    }

    void ExternalProgramLauncher(String program,String haml,String html){
        // указываем в конструкторе ProcessBuilder,
        // что нужно запустить программу ls с параметрами -l /dev
//        ProcessBuilder procBuilder = new ProcessBuilder("ls","-l","/dev");
        ProcessBuilder procBuilder = new ProcessBuilder(program, haml, html);

        // перенаправляем стандартный поток ошибок на
        // стандартный вывод
        procBuilder.redirectErrorStream(true);

        // запуск программы
        Process process = null;
        try {
            process = procBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // читаем стандартный поток вывода
        // и выводим на экран
        InputStream stdout = process.getInputStream();
        InputStreamReader isrStdout = new InputStreamReader(stdout);
        BufferedReader brStdout = new BufferedReader(isrStdout);

        String line = null;
        while(true) {
            try {
                if (!((line = brStdout.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(line);
        }

        // ждем пока завершится вызванная программа
        // и сохраняем код, с которым она завершилась в
        // в переменную exitVal
        try {
            int exitVal = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
