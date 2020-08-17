function process(expected, actual) {
  var diff = htmlDiffer.diffHtml(expected, actual),
    isEqual = htmlDiffer.isEqual(expected, actual),
    res = logger.getDiffText(diff, {
      charsAroundDiff: 40
    });

  logger.logDiffText(diff, {
    charsAroundDiff: 40
  });

  return isEqual;
}