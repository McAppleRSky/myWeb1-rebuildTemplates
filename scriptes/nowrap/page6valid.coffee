$ ->
	$("body")
		.append '<div class="win" name="modal"></div>'
	$("[name=modal]")
		.css {
			"background-color": "#81ecec",
			"top": "25%",
			"left": "25%",
			"border": "solid black"
		}
		.append "<section><p>Вы действительно хотите это сделать?</p></section>"
		.append '<section><input type="button" name="yesBut" value="Да"><input type="button" name="noBut"value="Нет"></section>'
		.children("section").eq(0)
		.css {
			"height": "80%",
			"display": "table"
		}
		.children("p")
		.css {
			"display": "table-cell",
			"vertical-align": "middle",
			"text-align": "center"
		}
	$("[name=modal]")
		.children("section").eq(1)
		.children "input"
		.css "width", "49%"
		.eq(0);
	$("#mainForm")
		.children "input[type=submit]"
		.attr "type", "button"
		.click( ->

			$(".blured")
				.addClass"blur"
			$("body")
				.addClass "lock"
			$("[name=modal]")
				.removeClass "win"
				.addClass "win-modal"
			return
			)

	$("#mainForm")
		.attr "onsubmit",""
	$("[name=noBut]").click( ->

			$(".blured")
				.removeClass "blur"
			$("body")
				.removeClass "lock"
			$("[name=modal]")
				.addClass "win"
				.removeClass "win-modal"
			return
			)

	$("[name=yesBut]").click( ->
			test = validMainForm()
			if test
				$('#mainForm').submit()
				$('#mainForm')
					.css "display", "none"
				$("#MainFormSubmitedMessage")
					.css "display", "inline"
			$(".blured")
				.removeClass "blur"
			$("body")
				.removeClass "lock"
			$("[name=modal]")
				.addClass "win"
				.removeClass "win-modal"
			return
			)

	$("#mainForm li").eq(0)
		.attr "data-toggle", "popover"
		.attr "title", "Tips (popover):"
		.attr "data-content", "все поля должны быть заполнены словами не менее из 3 букв, не должны быть заполнены словами Nicname, Family, Name, Surname. Nicname заполняется латиницей для соместимости без utf-8"
	$("#mainForm li").eq(2)
		.attr "data-toggle", "popover"
		.attr "title", "Tips (popover):"
		.attr "data-content", "поле должно быть отмечено"
	$("#mainForm li").eq(3)
		.attr "data-toggle", "popover"
		.attr "title", "Tips (popover):"
		.attr "data-content", "варианты должны быть выбраны"
	$("#mainForm li").eq(4)
		.attr "data-toggle", "popover"
		.attr "title", "Tips (popover):"
		.attr "data-content", "ответ должен быть длинной не менее 30 символов"
	$("#mainForm li").eq(5)
		.attr "data-toggle", "popover"
		.attr "title", "Tips (popover):"
		.attr "data-content", "должны быть отмечены несколько варриантов"
	$("#mainForm li").eq(6)
		.attr "data-toggle", "popover"
		.attr "title", "Tips (popover):"
		.attr "data-content", "ответ нужно выбрать"
	$("#mainForm li").eq(7)
		.attr "data-toggle", "popover"
		.attr "title", "Tips (popover):"
		.attr "data-content", "должны быть отмечены несколько варриантов"
	$('[data-toggle="popover"]').popover
		trigger: 'hover'
		delay:
			'show': 100
			'hide': 500
		# ---
		# generated by js2coffee 2.2.0
	return

howMany = (selectObject) ->
	numberSelected = 0
	for option in selectObject.options
		if option.selected == true
			numberSelected++
	numberSelected

howManyNotnone = (selectObject) ->
	numberSelected = 0
	i = 1
	while i < selectObject.options.length
		if selectObject.options[i].selected == true
			numberSelected++
		i++
	numberSelected

howManyChecked = (checkObject) ->
	numberChecked = 0;
	for obj in checkObject
		if obj.checked
			numberChecked++
	numberChecked

validMainForm = ->
	validStatus = true
	form = document.querySelector "#mainForm"
	if howMany($("li#ask6").children("select[name=ask6]").get(0)) == 0
		$("#ask6")
			.css "border", "2px dashed red"
		$("#valid61")
			.css "display", "inline"
		validStatus = false
		$("li#ask6")
			.children "select[name=ask6]"
			.focus()
	else
		$("#ask6")
			.css "border", "1px solid gray"
		$("#valid61")
			.css "display", "none"
	if howManyNotnone($("li#ask5").children("select[name=ask5]").get(0)) == 0
		$("#ask5")
			.css "border", "2px dashed red"
		$("#valid51")
			.css "display", "inline"
		validStatus = false
		$("li#ask5")
			.children "select[name=ask5]"
			.focus()
	else
		$("#ask5")
			.css "border", "1px solid gray"
		$("#valid51")
			.css "display", "none"
	if howMany($("li#ask4").children("select[name=ask4]").get(0)) == 0
		$("#ask4")
			.css "border", "2px dashed red"
		$("#valid41")
			.css "display", "inline"
		validStatus = false
		$("li#ask4")
			.children "select[name=ask4]"
			.focus()
	else
		$("#ask4")
			.css "border", "1px solid gray"
		$("#valid41")
			.css "display", "none"
	if $("li#ask3").children("textarea[name=ask3]").val() == ""
		$("#ask3")
			.css "border", "2px dashed red"
		$("#valid31")
			.css "display", "inline"
		validStatus = false
		$("li#ask3")
			.children "textarea[name=ask3]"
			.focus()
	else
		$("#ask3")
			.css "border", "1px solid gray"
		$("#valid31")
			.css "display", "none"
		if $("li#ask3").children("textarea[name=ask3]").val().split(" ").length < 30
			$("#ask3")
				.css "border", "2px dashed red"
			$("#valid311")
				.css "display", "inline"
			validStatus = false
			$("li#ask3")
				.children "textarea[name=ask3]"
				.focus()
		else
			$("#ask3")
				.css "border", "1px solid gray"
			$("#valid311")
				.css "display", "none"
	if howManyChecked($("li#ask2").children("input[name=ask2]")) == 0
		$("#ask2")
			.css "border", "2px dashed red"
		$("#valid21")
			.css "display", "inline"
		validStatus = false
		$("li#ask2")
			.children "input[name=ask2]"
			.get(0)
			.focus()
	else
		$("#ask2")
			.css "border", "1px solid gray"
		$("#valid21")
			.css "display", "none"
#	if document.querySelector("#mainForm").ask1.value == ""
	if form.ask1.value == ""
		$("#ask1")
			.css "border", "2px dashed red"
		$("#valid11")
			.css "display", "inline"
		validStatus = false
		$("li#ask1")
			.children "input[name=ask1]"
			.get(0)
			.focus()
	else
		$("#ask1")
			.css "border", "1px solid gray"
		$("#valid11")
			.css "display", "none"
	if $("input[name=nicname]").val() == ""
		$("input[name=nicname]")
			.css "border", "2px dashed red"
		$("#valid01")
			.css "display", "inline"
		validStatus = false
		$("input[name=nicname]")
			.focus()
	else
		$("input[name=nicname]")
			.css "border", "1px solid gray"
		$("#valid01")
			.css "display", "none"
		if $("input[name=nicname]").val().length < 3
			$("input[name=nicname]")
				.css "border", "2px dashed red"
			$("#valid02")
				.css "display", "inline"
			validStatus = false
			$("input[name=nicname]")
				.focus()
		else
			$("input[name=nicname]")
				.css "border", "1px solid gray"
			$("#valid02")
				.css "display", "none"
			if $("input[name=nicname]").val() == "nicname"
				$("input[name=nicname]")
					.css "border", "2px dashed red"
				$("#valid03")
					.css "display", "inline"
				validStatus = false
				$("input[name=nicname]")
					.focus()
			else
				$("input[name=nicname]")
					.css "border", "1px solid gray"
				$("#valid03")
					.css "display", "none"
				if $("input[name=family]").val() == ""
					$("input[name=family]")
						.css "border", "2px dashed red"
					$("#valid011")
						.css "display", "inline"
					validStatus = false
					$("input[name=family]")
						.focus()
				else
					$("input[name=family]")
						.css "border", "1px solid gray"
					$("#valid011")
						.css "display", "none"
					if $("input[name=family]").val().length < 3
						$("input[name=family]")
							.css "border", "2px dashed red"
						$("#valid012")
							.css "display", "inline"
						validStatus = false
						$("input[name=family]")
							.focus()
					else
						$("input[name=family]")
							.css "border", "1px solid gray"
						$("#valid012")
							.css "display", "none"
						if $("input[name=name]").val() == ""
							$("input[name=name]")
								.css "border", "2px dashed red"
							$("#valid021")
								.css "display", "inline"
							validStatus = false
							$("input[name=name]")
								.focus()
						else
							$("input[name=name]")
								.css "border", "1px solid gray"
							$("#valid021")
								.css "display", "none"
							if $("input[name=name]").val().length < 3
								$("input[name=name]")
									.css "border", "2px dashed red"
								$("#valid022")
									.css "display", "inline"
								validStatus = false
								$("input[name=name]")
									.focus()
							else
								$("input[name=name]")
									.css "border", "1px solid gray"
								$("#valid022")
									.css "display", "none"
								if $("input[name=surname]").val() == ""
									$("input[name=surname]")
										.css "border", "2px dashed red"
									$("#valid031")
										.css "display", "inline"
									validStatus = false
									$("input[name=surname]")
										.focus()
								else
									$("input[name=surname]")
										.css "border", "1px solid gray"
									$("#valid031")
										.css "display", "none"
									if $("input[name=surname]").val().length < 3
										$("input[name=surname]")
											.css "border", "2px dashed red"
										$("#valid032")
											.css "display", "inline"
										validStatus = false
										$("input[name=surname]")
											.focus()
									else
										$("input[name=surname]")
											.css "border", "1px solid gray"
										$("#valid032")
											.css "display", "none"
	validStatus