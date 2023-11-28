const mensagemGeral = document.getElementById('mensagemGeral');

if (!mensagemGeral.classList.contains('sumir')) {
	setTimeout(() => {
		mensagemGeral.classList.add('sumir');
	}, 6000);
}