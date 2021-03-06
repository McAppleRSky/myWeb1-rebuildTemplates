monthNames	= ("январь,февраль,март,апрель,май,июнь,июль,август,сентябрь,октябрь,ноябрь,декабрь").split(',')
westDays	= "Sun,Mon,Tue,Wed,Thu,Fri,Sat".split(',')
euroeastDays = "Пн,Вт,Ср,Чт,Пт,Сб,Вс".split(',')


birthdate	= null
birthmonth	= null
birthyear	= null
daysTSpace	= null
appendTableFlag = false

fillBirthdate = (date, month, year) ->
	monthNumbers = ("01,02,03,04,05,06,07,08,09,10,11,12").split(',')
	if date < 10
		date = '0' + date
	else
		date = '' + date
	birthdate.value = date + '.' + monthNumbers[month] + '.' + year
	return

initDateElements = ->
	year	= null
	month	= null
	date	= null

	birthdate	= document.querySelector "#birthdate"
	birthmonth	= document.querySelector "#birthmonth"
	birthyear	= document.querySelector "#birthyear"
	daysTSpace	= document.querySelector "#days-space"

	srcDate 	= new Date()
	date 		= srcDate.getDate()
	month 		= srcDate.getMonth()
	year 		= srcDate.getFullYear()
	fillBirthdate(date, month, year)

	monthNames.forEach((monthName, i_1) =>
		optionMonth = document.createElement "option"
		optionMonth.textContent = monthName
		optionMonth.value = i_1;
		birthmonth.appendChild optionMonth
		return
	)

	birthmonth.value = month

	optionYear = null
	i_0 = 0
	while i_0<12
		optionYear = document.createElement "option"
		birthyear.appendChild optionYear
		i_0++
	optionYear.textContent = year
	birthyear.value = year
	fillYear(birthyear)

	daysTSpace.value = date
	return

fillYear = (yearSelector) ->
	year =	Number(yearSelector.value)
	options = yearSelector.getElementsByTagName "option"
	i = 6 + year
	j = 0
	while i > year - 6
		options[j].textContent = i
		options[j].setAttribute("value", i)
		i--
		j++
	yearSelector.value = year
	return

incYear = (inc, runnerElement) ->
	year = Number(birthyear.value)
	birthyear.value = year + inc
	fillYear(birthyear)
	fillBirthdate(daysTSpace.value, birthmonth.value, birthyear.value)
	fillCalender(runnerElement)
	return

fillCalender = (runnerElement_0) ->
	if runnerElement_0.id == "birthmonth"
		fillBirthdate daysTSpace.value, birthmonth.value, birthyear.value
	if runnerElement_0.id == "birthyear"
		fillYear(runnerElement_0)
		fillBirthdate daysTSpace.value, birthmonth.value, birthyear.value

	daysTable = null
	daysTBody = null
	daysTr = null
	if appendTableFlag == true
		deletingNode = document.querySelector "#calenderTableUniq"
		daysTSpace.removeChild(deletingNode)
		appendTableFlag = false

	daysTable = document.createElement "table"
	daysTBody = document.createElement "tbody"
	daysTr = document.createElement "tr"
	daysTh = new Array(7)
	daysTh.forEach((dayTh, i_2) =>
		dayTh = document.createElement("th")
		dayTh.textContent = euroeastDays[i_2]
		daysTr.appendChild(dayTh)
		return
	)

	daysTable.id = "calenderTableUniq"
	daysTBody.appendChild(daysTr)
	daysTable.appendChild(daysTBody)
	daysTSpace.appendChild(daysTable)
	appendTableFlag = true

	dateComponent = null
	currentYear = null
	currentMonth = null
	lastDateCurrentMonth = null
	firstDateCurrenMonth = null
	firstDayCurrenMonth = null
	tokenDate = 1

	dateComponent = birthdate.value.split('.')
	currentYear = Number(dateComponent[2])
	currentMonth = Number(dateComponent[1]) - 1

	firstDateCurrenMonth = new Date(currentYear, currentMonth, 1)
	firstDayCurrenMonth = firstDateCurrenMonth.getDay()
	if firstDayCurrenMonth == 0
		firstDayCurrenMonth = 6
	else
		firstDayCurrenMonth--

	while currentMonth == new Date(currentYear, currentMonth, ++tokenDate).getMonth()
		lastDateCurrentMonth = tokenDate

	daysTds = new Array()
	daysTrs = new Array()

	tokenRow = 0
	appendChildTrFlag = null

	daysTrs[tokenRow] = document.createElement("tr")
	appendChildTrFlag = false
	daysTds[tokenRow] = new Array(7)

	tokenDay = 0
	while tokenDay < daysTds[tokenRow].length
		daysTds[tokenRow][tokenDay] = document.createElement("td")
		daysTrs[tokenRow].appendChild(daysTds[tokenRow][tokenDay])
		tokenDay++
	tokenDate = 1
	tokenDay = firstDayCurrenMonth
	while tokenDate <= lastDateCurrentMonth
		if tokenDay == 7
			daysTBody.appendChild(daysTrs[tokenRow++])
			appendChildTrFlag = true
			daysTrs[tokenRow] = document.createElement("tr")
			appendChildTrFlag = false
			daysTds[tokenRow] = new Array(7)
			tokenDay = 0
			while tokenDay < daysTds[tokenRow].length
				daysTds[tokenRow][tokenDay] = document.createElement("td")
				daysTrs[tokenRow].appendChild(daysTds[tokenRow][tokenDay])
				tokenDay++
			tokenDay = 0
		aHrefElement = document.createElement("a")
		aHrefElement.setAttribute "href", "##"
		aHrefElement.textContent = tokenDate;
		daysTds[tokenRow][tokenDay].appendChild(aHrefElement)
		daysTds[tokenRow][tokenDay].onclick = ->

			daysTSpace.value = Number(this.textContent)
			fillBirthdate(daysTSpace.value, birthmonth.value, birthyear.value)
			return

		tokenDay++
		tokenDate++
	if appendChildTrFlag == false
		daysTBody.appendChild(daysTrs[tokenRow])
		appendChildTrFlag = true
	return
