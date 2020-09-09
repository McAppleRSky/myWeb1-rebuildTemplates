list=->
	document.write "<"+list.arguments[0]+"L>"
	document.write "<LI>" + i + "</LI>" for i in [].slice.call(list.arguments)[1...]
	document.write "</"+list.arguments[0]+"L>"
