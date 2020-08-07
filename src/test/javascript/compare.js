//console.log("process.platform : " + process.platform);
//require('dotenv').config();
//var process = {env: {FORCE_COLOR: true}}
//var console = {log: print, warn: print, error: print};

//var HtmlDiffer = require('html-differ').HtmlDiffer,logger = require('html-differ/lib/logger');

//var task = Java.type('ru.krt.copypast.jsrunner.JsRunner');
//print(task);

//var html1file = task.getToExpected()+"/"+task.getExpecteds()
//    ,html2file = task.getToActual()+"/"+task.getActuals();

//var html1 = "<!DOCTYPE html><html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title></head><body></body></html>",html2 = "<!DOCTYPE html><html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title></head><body><div></div></body></html>";

var script = function(param) {
  var options = {
    ignoreAttributes: [],
    compareAttributesAsJSON: [],
    ignoreWhitespaces: true,
    ignoreComments: true,
    ignoreEndTags: false,
    ignoreDuplicateAttributes: false
  };

  var htmlDiffer = new HtmlDiffer(options);

  var diff = htmlDiffer.diffHtml(param, param),
                                isEqual = htmlDiffer.isEqual(param, param),
                                res = logger.getDiffText(diff, {
                                    charsAroundDiff: 40
                                });

  logger.logDiffText(diff, {
    charsAroundDiff: 40
  });
  return isEqual;
}
