//console.log("process.platform : " + process.platform);
//require('dotenv').config();
var process = {
    env: {
        FORCE_COLOR: true
    }
}
var console = {
    log: print,
    warn: print,
    error: print
};

var //fs = require('fs'),
    HtmlDiffer = require('html-differ').HtmlDiffer
//    require('./node_modules/html-differ/lib/index.js')
    ,logger = require('html-differ/lib/logger')
    ;

var html1 = //fs.readFileSync('expected/index.htm', 'utf-8')
"<!DOCTYPE html><html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title></head><body></body></html>"
    ,html2 = //fs.readFileSync('rebuild/index.html', 'utf-8')
"<!DOCTYPE html><html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title></head><body><div></div></body></html>"
    ;

var options = {
        ignoreAttributes: [],
        compareAttributesAsJSON: [],
        ignoreWhitespaces: true,
        ignoreComments: true,
        ignoreEndTags: false,
        ignoreDuplicateAttributes: false
    };

var htmlDiffer = new HtmlDiffer(options);

var diff = htmlDiffer.diffHtml(html1, html2),
    isEqual = htmlDiffer.isEqual(html1, html2),
    res = logger.getDiffText(diff, { charsAroundDiff: 40 });

logger.logDiffText(diff, { charsAroundDiff: 40 });
