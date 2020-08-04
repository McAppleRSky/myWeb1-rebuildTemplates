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

function getFile(name) {
    var data = "";
    var chunk;
    var FileReader = Java.type("java.io.FileReader");
    var fileReader = new FileReader(name);
    chunk = fileReader.read();
    while (chunk != -1) {
        data += String.fromCharCode(chunk);
        chunk = fileReader.read();
    }
    fileReader.close();
    return data;
}
