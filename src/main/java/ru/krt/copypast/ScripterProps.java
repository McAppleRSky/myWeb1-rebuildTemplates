package ru.krt.copypast;

public interface ScripterProps {
    String engineName = "nashorn"
//    ,jvmNpmJs = "./node_modules/jvm-npm/src/main/javascript/jvm-npm.js"
    ,pathName = "", encoding = "UTF-8"
//    ,JvmNpmScript = String.format("load('%s');", jvmNpmJs)



//    ,requireLibpath = String.format("var require = Require( '%s' , ['node_modules/html-differ', 'libpath2']);" , "./")
//    ,initScript2 = String.format("var require = Require( new java.io.File( '%s' ), ['libpath1', 'libpath2']);"
//    ,requireFs = String.format("var fs = require('%s');", "file-system")
//              var fs = require('file-system');
//    ,nodeschnaps="./node_modules/nodeschnaps/loader.js"
//    ,loadNodeschnaps = String.format("load('%s')", nodeschnaps)
    ;
}
