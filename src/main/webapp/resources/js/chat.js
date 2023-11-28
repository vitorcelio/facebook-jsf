document.body.style.position = "relative";
var notificacao = document.getElementById("notificacao");
var link;

notificacao.addEventListener("click", () => {
	window.location.assign(link);
})

function mostrarNotificacao(json) {
	link = `http://localhost:8080/messages/t/${json.username}` 
	if (usernameBack !== json.username) {
		notificacao.style.transform = "translateX(0)";
		document.getElementById("notificacaoNome").innerText = json.nome;
		document.getElementById("notificacaoImagem").src = json.imagem;
		document.getElementById("notificacaoMsg").innerText = json.mensagem;

		setTimeout(() => {
			notificacao.style.transform = "translateX(500px)";
		}, 5000);
	}
}


var websocket = new WebSocket(uriMeuUsuario);

websocket.onopen = function(event) {
	console.log("Abrindo ws meuUsuario");
};

websocket.onerror = function(event) {
	console.log("Fechando ws meuUsuario");
};

websocket.onmessage = function(event) {
	var json = JSON.parse(event.data);
	mostrarNotificacao(json);
};
