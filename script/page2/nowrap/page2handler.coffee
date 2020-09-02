list=(type)->
	arr = []
	`for (var i = 1; i < list.arguments.length; i++) arr.push("<LI>"+list.arguments[i]+"</LI>")`
	document.write("<#{type}L>")
	document.write(item for item in arr)
	document.write("</#{type}L>")
