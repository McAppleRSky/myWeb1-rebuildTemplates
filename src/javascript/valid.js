function doIt(srcHtml) {
  var validator = require('html-validator')
  var options = {
    data: srcHtml,
    format: 'text'
    }

    try {
      var result = validator(options)
      console.log(result)
    } catch (error) {
      console.error(error)
    }
}