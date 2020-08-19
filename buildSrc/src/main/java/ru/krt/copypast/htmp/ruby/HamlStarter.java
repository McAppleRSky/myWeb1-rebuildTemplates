package ru.krt.copypast.htmp.ruby;

import org.gradle.api.Project;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/*
* https://mkyong.com/java/how-to-execute-shell-command-from-java/
*
* */

public class HamlStarter {

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

    public HamlStarter(Project projectSources) {
        this.mainProjectSources = projectSources;
        this.mainProjectPath = mainProjectSources.getProject().getProjectDir().getPath();
//        currentNpmprojectPath = FilesystemFolder.create(mainProjectSources.getProject().getProjectDir(), "UTF-8");
        //require = Require.enable(engine, currentNpmprojectPath);

//        runNashorn();
//        TaskQueue taskQueue = new TaskQueue(mainProjectSources.getProject().getProjectDir().getPath());

//        try {
//            if(ToolFunc.notNull(new ProcessBuilder("bash", "-c", "").start())){
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
            hamlQueue(
//                    Paths.get(mainProjectPath, hamlInputDir).toString()
//                    ,Paths.get(mainProjectPath, HtmlOutputDir).toString()
                    Paths.get(mainProjectPath, hamlInputDir).toString()
                    ,Paths.get("..", HtmlOutputDir).toString()
                    ,Paths.get(mainProjectPath, filelist)
            );
//            }
//        } catch (IOException e) {e.printStackTrace();}

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
                System.out.println("Compile : "+hamlFilenameKey);
                //                    srcHaml = new String(Files.readAllBytes(Paths.get(hamlSourcesDir+hamlFilenameKey)));
                htmlFilenameValue = jsonObj.get(hamlFilenameKey).toString();
//        engine.eval("load('/mnt/d/projects/myWeb1-rebuildTemplates/node_modules/hamlInputDirArg/lib/haml.js');");
//                srcHtml = (String)((Invocable)engine).invokeFunction("process", srcHaml);
//                srcHtml1 = srcHtml.getBytes(StandardCharsets.UTF_8);
//                Files.write(Paths.get(HtmlOutputDir+htmlFilenameValue), srcHtml1);
                ExternalProgramLauncher(Paths.get(inputDir).toString(),"haml"
//                        ,Paths.get(mainProjectPath, "node_modules","haml").toString() //"node_modulse/haml"
//                        ,"/usr/lib/ruby/vendor_ruby/haml.rb"//Paths.get("..", "node_modules","haml").toString()
//                        ,"/usr/lib/ruby/vendor_ruby/haml.rb"
//                        ,""
//                        ,Paths.get(inputDir,hamlFilenameKey).toString()
                        ,Paths.get(hamlFilenameKey).toString()
                        ,Paths.get(outputDir,htmlFilenameValue).toString()
                );
            }
        }
    }

    /*
    * https://nts.strzibny.name/using-ruby-gems-in-javagradle-projects-jruby/
    * */
    void ExternalProgramLauncher(
            String workdir
            ,String program
//            ,String optionArg
            ,String hamlInputDirArg
            ,String htmlOutputDirArg){
        // указываем в конструкторе ProcessBuilder,
        // что нужно запустить программу ls с параметрами -l /dev
//        ProcessBuilder procBuilder = new ProcessBuilder("ls","-l","/dev");
        ProcessBuilder processBuilder = new ProcessBuilder(program
//                ,optionArg
                ,hamlInputDirArg
                ,htmlOutputDirArg);
        processBuilder.directory(new File(workdir));

        // перенаправляем стандартный поток ошибок на
        // стандартный вывод
        processBuilder.redirectErrorStream(true);

        // запуск программы
        Process process = null;
        try {
            process = processBuilder.start();
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

        System.out.println("");
    }

}
