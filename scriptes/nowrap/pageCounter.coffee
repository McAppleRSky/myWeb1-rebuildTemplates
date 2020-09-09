
prepCalcThisPage = ->
	if getCookie(htmPage) == undefined
		setCookie(htmPage, '', 1)
	if getCookie(htmPage) == ''
		setCookie(htmPage, '0', 1)
	return

sessionStoragePrepCalcThisPage = ->
	if sessionStorage.getItem(htmPage) == null
		sessionStorage.setItem(htmPage, '', 1)
	if sessionStorage.getItem(htmPage) == ''
		sessionStorage.setItem(htmPage, '0', 1)
	return

calcThisPage = ->
	pageCount = Number(getCookie(htmPage))
	if pageCount >= 0
		if pageCount == 0
			date = new Date(Date.now())
			setCookie(htmPage + "TimeStamp", date.toUTCString(), 1)
		setCookie(htmPage, pageCount + 1, 1)
	return

sessionStorageCalcThisPage = ->
	pageCount = Number(sessionStorage.getItem(htmPage))
	if pageCount >= 0
		if pageCount == 0
			date = new Date(Date.now())
			sessionStorage.setItem(htmPage + "TimeStamp", date.toUTCString())
		sessionStorage.setItem(htmPage, pageCount + 1)
	return

prepCalcThisPage()
sessionStoragePrepCalcThisPage()
calcThisPage()
sessionStorageCalcThisPage()
