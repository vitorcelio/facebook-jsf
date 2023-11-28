const botoesPublicacao = document.getElementById('botoes-perfil:publicacaoBotao');
const botoesAmigos = document.getElementById('botoes-perfil:amigosBotao');
const botoesFotos = document.getElementById('botoes-perfil:fotosBotao');

const publicacao = document.getElementById('publicacoes');
const amigos = document.getElementById('amigos');
const fotos = document.getElementById('fotos');
const body = document.getElementsByTagName('body')[0];

const perfilEditarBody = document.getElementById('perfilEditarBody');
const perfilEditarFoto = document.getElementById('perfilEditarFoto');
const perfilEditarCapa = document.getElementById('perfilEditarCapa');
const biografiaBody = document.getElementById('biografiaBody');
const biografiaTexto = document.getElementById('main-editar-perfil:biografiaTexto');
const biografiaBotao = document.getElementById('biografiaBotao');
const perfilEditarInformacoes = document.getElementById('perfilEditarInformacoes');


botoesPublicacao.addEventListener('click', () => {
	botoesPublicacao.classList.add('menu-perfil-ativo');
	botoesAmigos.classList.remove('menu-perfil-ativo');
	botoesFotos.classList.remove('menu-perfil-ativo');
	publicacao.classList.remove('sumir');
	amigos.classList.add('sumir');
	fotos.classList.add('sumir');
});

botoesAmigos.addEventListener('click', () => {
	botoesPublicacao.classList.remove('menu-perfil-ativo');
	botoesAmigos.classList.add('menu-perfil-ativo');
	botoesFotos.classList.remove('menu-perfil-ativo');
	publicacao.classList.add('sumir');
	amigos.classList.remove('sumir');
	fotos.classList.add('sumir');
});

botoesFotos.addEventListener('click', () => {
	botoesPublicacao.classList.remove('menu-perfil-ativo');
	botoesAmigos.classList.remove('menu-perfil-ativo');
	botoesFotos.classList.add('menu-perfil-ativo');
	publicacao.classList.add('sumir');
	amigos.classList.add('sumir');
	fotos.classList.remove('sumir');
});

function sumirSalvo(id) {
	const botaoSalvar = document.getElementById(id + ':salvar-post');
	botaoSalvar.classList.toggle('sumir');
}

function mostrarComentario(id) {
	const abaComentario = document.getElementById(id + ':comentarios-respostas');
	const postFooter = document.getElementById(id + ':footer');
	abaComentario.classList.toggle('sumir');
	postFooter.classList.toggle('semBordaBottom');
}

function mostrarRespotas(idPost, id) {
	const abaRespostas = document.getElementById(idPost + ':' + id + ':' + 'respostas');
	abaRespostas.classList.toggle('sumir');
}

function abrirEditar() {
	perfilEditarBody.classList.toggle('sumir');
	body.classList.toggle('sem-scroll');
}

function abrirEditarFoto() {
	perfilEditarFoto.classList.toggle('sumir');
}

function abrirEditarCapa() {
	perfilEditarCapa.classList.toggle('sumir');
}

function abrirEditarBio() {
	biografiaBody.classList.toggle('sumir');
	biografiaTexto.classList.toggle('sumir');
	if (biografiaBotao.textContent === "Adicionar") {
		biografiaBotao.textContent = "Cancelar";
	} else {
		biografiaBotao.textContent = "Adicionar";
	}
}

function abrirEditarInformacao() {
	perfilEditarInformacoes.classList.toggle('sumir');
}