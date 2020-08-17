function process(expected, actual, filename) {
  var diff = htmlDiffer.diffHtml(expected, actual),
    isEqual = htmlDiffer.isEqual(expected, actual),
    res = logger.getDiffText(diff, {
      charsAroundDiff: 40
    });
  console.log("FILE: "+filename);
  logger.logDiffText(diff, {
    charsAroundDiff: 40
  });

  return isEqual;
}