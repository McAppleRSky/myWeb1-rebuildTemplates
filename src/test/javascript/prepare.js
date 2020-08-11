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

var HtmlDiffer = require('html-differ');

var HtmlDiffer = require('html-differ').HtmlDiffer
    ,logger = require('html-differ/lib/logger')
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


//var htmlDiffer = new HtmlDiffer(options);

//var isEqualFunHtmlDiffer = htmlDiffer.isEqual;
