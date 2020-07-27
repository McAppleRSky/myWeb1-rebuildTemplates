package ru.krt.copypast;

public interface EvalFile {
    String
    pathName = ""
    ,encoding = "UTF-8"
    ,initScriptFile = "node_modules/jvm-npm/src/main/javascript/jvm-npm.js"
    ,initScript = String.format("load('%s')", initScriptFile)
    ,initScript1 = String.format("var Require = load('%s');", "src/main/javascript/require.js")
    ,initScript2 = String.format("var require = Require('%s', ['libpath1', 'libpath2']);"
            , "./")
    ;
}
