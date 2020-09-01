list=(type, list...)->
	document.write("<" + type + "L>")
	document.write("<LI>" + i for i in list + "</LI>")
	document.write("</" + type + "L>")
