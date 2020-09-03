imgListPath		= ["img/", "album/"]
listFilename 	= "list.txt"
listfFilename	= "listf.txt"
file 			= 0
title 			= 1
width 			= 2
height 			= 3
listCount		= 2

lists 			= []
photos 			= []
reader 			= []
readerIndex 	= -1
mainArticle = null
imgList = null
fileElem = null
full 			= false
formated 		= false
OnesReadystatechangeCount = 0
curPhotoViewWidth = 0
currentPhotoView = null
curPhotoViewHeight = null
myConsole = window.console

Photo = (file, title, width, height) ->
	this.file = file
	this.title = title
	this.width = width
	this.height = height
	return

$(".mainArticle")
	.css {
		"background-image": 'url("img/cfa82088a277960755ecb3ca54c728be.png")',
		"background-repeat": "repeat-y",
		"background-position": "left",
		"background-size": "100%"
		}
$(".commonTable")
	.width 	"78%"
$("figcapTitle")
	.css "font-size", "75%" 
$("table.commonTable tr th")
	.width "15%"

document.onreadystatechange =->
	fillListAndPhotosAndAlbumHandler() if OnesReadystatechangeCount++ == 0 if document.readyState == "complete"
	return

fillListAndPhotosAndAlbumHandler=->
	if window.location.protocol == "http:"
		fillListFromHttp()
		$(".mainArticle").children(".imgList").remove()
		fillPhotos()
		albumHandler()
	else
		if window.location.protocol == "file:"
			myConsole.log("protocol is file")
			document.querySelector("#fileElem").style.display = "block"
		else
			myConsole.log("invalid protocol")
	return

fillListFromFileAndfillPhotosAndAlbumHandler=(event)->
	for item in event.target.files
		if item.name == listFilename and full == false
			reader[++readerIndex] = new FileReader()

			reader[readerIndex].onload = ->
				str = @result.split("\n")
				full = null
				if str[0].split(';').length == 1
					lists.push str
					full = lists.length - 1
					document.querySelector('object[data="' + imgListPath[0] + listFilename + '"]')
						.style.display = "none"
				if lists.length == listCount
					mainArticle.removeChild imgList
					fillPhotos()
					albumHandler()
				return

			reader[readerIndex].readAsText(item)
		else
			if item.name == listfFilename and formated == false
				reader[++readerIndex] = new FileReader()

				reader[readerIndex].onload =->
					str = @result.split("\n")
					if str[0].split(';').length == 4
						lists.push str
						formated = lists.length - 1
						document.querySelector('object[data="'+ imgListPath[0] + listfFilename + '"]')
							.css "display", "none"
					if lists.length == listCount
						$(".mainArticle").children(".imgList").remove()
						fillPhotos()
						albumHandler()
					return

				reader[readerIndex].readAsText(item)
	return 

fillListFromHttp = ->
	i = 0
	while i < listCount
		curListValueCount = null
		try lists[i] = window.frames[i].document.querySelector("pre").textContent.split "\n"
		catch e
			myConsole.log "list${i} with exception| ${e}"
		finally
			curListValueCount = lists[i][0].split(';').length
			if curListValueCount == 4
				full = i
			else myConsole.log "incorrect files list.txt listf.txt"
		i++
	return

fillPhotos = ->
	curPhotoParam = null
	fileInList = null
	for item1 in	lists[formated]
		curPhotoParam = item1.split(';')
		fileInList = lists[full].indexOf(curPhotoParam[file])
		if lists[full].includes(curPhotoParam[file])
			if curPhotoParam.length == 4
				photos.push new Photo(lists[full].splice(fileInList, 1)[0],
					curPhotoParam[title],
					curPhotoParam[width],
					curPhotoParam[height])
	for item2 in	lists[full]
		if item2 != ""
			photos.push new Photo item2, item2, "100", "100"
	return

albumHandler = ->
	photoTable = null
	photoWin = null
	winImg = null
	cellCount = 4
	tableWidthPercent = "100"
	newRow = null
	newCell = null
	fileName = null
	anchEl = null
	figEl = null
	imgEl = null
	figcapEl = null
	i_0 = 0
	j_0 = 0
	k_0 = 0
	photoTable = document.createElement "table"
	photoTable.id = "photoTable"
	photoTable.setAttribute "width", "" + tableWidthPercent + "%"
	while k_0 < photos.length
		newRow = photoTable.insertRow i_0
		while j_0 < cellCount
			newCell = newRow.insertCell -1
			if k_0 < photos.length
				newCell.width = "" + tableWidthPercent / cellCount
				newCell.style.textAlign = "center"
				fileName = imgListPath[0] + imgListPath[1] + photos[k_0].file

				imgEl = document.createElement "img"
				imgEl.setAttribute "value", photos[k_0].file
				imgEl.setAttribute "src", fileName
				imgEl.setAttribute "width", photos[k_0].width
				imgEl.setAttribute "height", photos[k_0].height

				imgEl.onclick = (event) -> 
					currentSrc = event.path[0].src
					componentSrc = currentSrc.split '/'
					photos.forEach((item_0, i_1) =>
						if item_0.file == componentSrc[componentSrc.length - 1]
							currentPhotoView = i_1
						return
					)
					$("#viewLayer").addClass("win-modal").removeClass "win"
					setPhotoViewLayer(currentSrc)
					$(".blured").addClass "blur"
					$("body").addClass "lock"
					return

				figcapEl = document.createElement "figcaption"
				figcapEl.textContent = photos[k_0].title
				figcapEl.classList.add "figcapTitle"
				figEl = document.createElement "figure"
				figEl.appendChild imgEl
				figEl.appendChild figcapEl
				anchEl = document.createElement "a"
				anchEl.appendChild figEl
				newCell.appendChild anchEl
				k_0++
			j_0++
		i_0++

	photoTable.classList.add "blured"

	$(".mainArticle").append photoTable
	$(".mainArticle")
		.css "padding-top", "5px"
	$(".mainArticle")
		.append '<div class="win" id="viewLayer"></div>'
	$("#viewLayer")
		.css {
			"background-color": "#81ecec",
			"top": "5%",
			"left": "5%"
			}
		.append '<figure><img src="" alt="" ><figcaption><table><tr><td></td><td>-----------------------------------</td><td></td></tr></table></figcaption></figure>'
		.find("img").click = ->
			$(".win-modal")
				.addClass "win"
				.removeClass "win-modal"
			$(".blured")
				.removeClass "blur"
			$("body")
				.removeClass "lock"
			return

	$("#viewLayer")
		.find	"table"
		.width	"100%"
		.find	"tr"
		.css "background-color", "#81ecec"
		.find("td").eq(1)
		.css {
			"color": "gray",
			"width": "50%",
			"text-align": "center"
			}
	$("#viewLayer")
		.find "td:first"
		.css "text-align", "right"
		.append "<label><strong>&lt;&lt;</strong></label>"

		.children().click = ->
			if currentPhotoView == 0
				currentPhotoView = photos.length - 1
			else currentPhotoView--
			setPhotoViewLayer imgListPath[0] + imgListPath[1] + photos[currentPhotoView].file
			return

	$("#viewLayer")
		.find "td:last"
		.append "<label><strong>&gt;&gt;</strong></label>"

		.children().click = ->
			if currentPhotoView == photos.length - 1
				currentPhotoView = 0
			else currentPhotoView++
			setPhotoViewLayer imgListPath[0] + imgListPath[1] + photos[currentPhotoView].file
			return
	return

setPhotoViewLayer = (src) ->
	prevPhotoViewWidth = null
	continueIf = false
	if curPhotoViewWidth != 0
		continueIf = true
		prevPhotoViewWidth = curPhotoViewWidth
		$("#viewLayer")
			.find "img" 
			.css {
				"opacity": "0.0",
				"height": "",
				"width": ""
				}
	$("#viewLayer")
		.find "img" 
		.attr "src", src 
		.attr "title", photos[currentPhotoView].title
		.attr "alt", photos[currentPhotoView].alt
		.attr "height", $(window).height() / 2
	curPhotoViewHeight = $("#viewLayer").find("img").height()
	curPhotoViewWidth = $("#viewLayer").find("img").width()
	if continueIf
		$("#viewLayer")
			.find "img"
			.removeAttr "height"
			.css "height", curPhotoViewHeight
			.css "width", prevPhotoViewWidth
		$("#viewLayer")
			.find "img"
			.animate {
				opacity: 1.0,
				height: curPhotoViewHeight,
				width: curPhotoViewWidth
				}, 300
	$("#viewLayer")
		.find("td").eq(1).empty()
		.append "фото "+currentPhotoView+1+" из "+photos.length
	return