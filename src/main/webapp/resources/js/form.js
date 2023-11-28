const elementosForm = document.querySelector("#elementos-form");
var objJson;

function gerarForm(elementosJson) {
	let jsonJava = JSON.stringify(elementosJson);
	objJson = JSON.parse(jsonJava);


	const campos = objJson.campos;

	while (elementosForm.firstChild) {
		elementosForm.removeChild(elementosForm.firstChild);
	}

	for (let campo of campos) {
		const div = document.createElement("div");
		div.classList.add("field");

		const label = document.createElement("label");
		label.setAttribute("for", campo.idInput);
		label.innerHTML = campo.descricao + (campo.obrigatorio ? '*' : '');
		label.className = "ui-outputlabel ui-widget block text-white";

		div.appendChild(label);

		let ele;
		if (campo.tipo == 1 || campo.tipo == 2 || campo.tipo == 3 || campo.tipo == 5 || campo.tipo == 7 || campo.tipo == 8) {
			ele = createElement(campo);
			div.appendChild(ele);
		}

		let opcaoEle;
		if (campo.opcoes) {
			let divOpcoes = document.createElement("div");
			divOpcoes.style = "display: flex; flex-direction: column; gap: 5px; justify-content: space-between; flex-wrap: wrap;";
			for (let opcao of campo.opcoes) {
				switch (campo.tipo) {
					case 4:
						divOpcoes.setAttribute("id", campo.idInput)
						const divInput = document.createElement("div");
						divInput.classList.add("field2");

						const label = document.createElement("label");
						label.setAttribute("for", opcao.idInput);
						label.innerHTML = opcao.descricao;
						label.className = "ui-outputlabel ui-widget block";

						opcaoEle = document.createElement("input");
						opcaoEle.setAttribute("id", opcao.idInput);
						opcaoEle.setAttribute("type", "checkbox");

						divInput.appendChild(opcaoEle);
						divInput.appendChild(label);

						divOpcoes.appendChild(divInput);

						break;
					case 5:
						opcaoEle = document.createElement("option")
						opcaoEle.className = "ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all";
						opcaoEle.innerHTML = opcao.descricao;
						ele.appendChild(opcaoEle);
						break;
					case 6:
						divOpcoes.setAttribute("id", campo.idInput)
						const divInput2 = document.createElement("div");
						divInput2.classList.add("field2");

						const label2 = document.createElement("label");
						label2.setAttribute("for", opcao.idInput);
						label2.innerHTML = opcao.descricao;
						label2.className = "ui-outputlabel ui-widget block";


						opcaoEle = document.createElement("input");
						opcaoEle.setAttribute("id", opcao.idInput);
						opcaoEle.setAttribute("name", campo.idInput);
						opcaoEle.setAttribute("type", "radio");


						divInput2.appendChild(opcaoEle);
						divInput2.appendChild(label2);

						divOpcoes.appendChild(divInput2);
						break;
				}
			}

			div.appendChild(divOpcoes);
		}

		elementosForm.appendChild(div);
	}
}

function createElement(elemento) {
	let input;

	switch (elemento.tipo) {
		case 1:
			input = document.createElement("input");
			input.setAttribute("type", "text");
			input.className = "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all";
			break;
		case 2:
			input = document.createElement("textarea")
			input.className = "ui-inputfield ui-inputtextarea ui-widget ui-state-default ui-corner-all ui-inputtextarea-resizable";
			break;
		case 3:
			input = document.createElement("input");
			input.setAttribute("type", "number");
			input.className = "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all";
			break;
		case 5:
			input = document.createElement("select");
			input.className = "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all";
			break;
		case 7:
			input = document.createElement("input");
			input.setAttribute("type", "email");
			input.className = "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all";
			break;
		case 8:
			input = document.createElement("input");
			input.setAttribute("type", "date");
			input.className = "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all";
			break;
	}

	input.setAttribute("id", elemento.idInput);
	input.setAttribute("name", elemento.idInput);
	input.setAttribute("placeholder", elemento.sugestao);

	if (elemento.obrigatorio) {
		input.setAttribute("required", "true");
	}

	return input;
}


function salvarForm() {
	const campos = objJson.campos;

	let arrayRespostas = [];

	for (campo of campos) {
		let value;
		switch (campo.tipo) {
			case 1:
				value = document.querySelector(`#${campo.idInput}`).value;
				break;
			case 2:
				value = document.querySelector(`#${campo.idInput}`).value;
				break;
			case 3:
				value = document.querySelector(`#${campo.idInput}`).value;
				break;
			case 5:
				value = document.querySelector(`#${campo.idInput}`).value;
				break;
			case 7:
				value = document.querySelector(`#${campo.idInput}`).value;
				break;
			case 8:
				value = document.querySelector(`#${campo.idInput}`).value;
				break;
			case 4:
				const checkBox = document.querySelector(`#${campo.idInput}`);
				var inputs = checkBox.getElementsByClassName("field2");
				var checkeds = "";

				for (check of inputs) {
					let input = check.children[0];
					checkeds += input.checked ? check.children[1].innerText + ", " : "";
				}

				value = checkeds;
				break;
			case 6:
				const radio = document.querySelector(`#${campo.idInput}`);
				var inputs = radio.getElementsByClassName("field2");
				var checkeds = "";

				for (check of inputs) {
					let input = check.children[0];
					checkeds += input.checked ? check.children[1].innerText : "";
				}

				value = checkeds;
				break;

		}
		const resposta = {"resposta": value, "idCampo": campo.id };
		arrayRespostas.push(resposta);
	}

	salvarRespostas([{
		name: "listaRespostas",
		value: JSON.stringify(arrayRespostas)
	}])

	console.log(arrayRespostas)
}