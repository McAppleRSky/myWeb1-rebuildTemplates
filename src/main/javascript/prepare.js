var process = {
    env: {
        FORCE_COLOR: true
    }
}

var console = {
    log: print,
    warn: print,
    error: print
}

var //fs = require('fs'),
    HtmlDiffer = require('html-differ').HtmlDiffer
    //HtmlDiffer = require('./index').HtmlDiffer,

//    require('./node_modules/html-differ/lib/index.js')
    ,logger = require('html-differ/lib/logger')
    ;

function getFile(name) {
    var data = "";
    var chunk;
    var FileReader = Java.type("java.io.FileReader");
    var fileReader = new FileReader(name);
    do {
        chunk = fileReader.read();
        data += String.fromCharCode(chunk);
    } while (chunk != -1)
//    chunk = fileReader.read();
//    while (chunk != -1) {
//        data += String.fromCharCode(chunk);
//        chunk = fileReader.read();
//    }
    fileReader.close();
    return data;
}
