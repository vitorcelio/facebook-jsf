const botaoPublicar = document.getElementById('botaoPublicar');
const botaoPublicar2 = document.getElementById('botaoPublicar2');
const publicarBody = document.getElementById('publicarBody');
const fecharPublicacao = document.getElementById('fecharPublicacao');
const menuSalvar = document.getElementById('menu-salvar');
const botaoSalvar = document.getElementById('post:botao-salvar');

botaoPublicar.addEventListener('click', ()=> {
	publicarBody.classList.toggle('sumir');
});

botaoPublicar2.addEventListener('click', ()=> {
	publicarBody.classList.toggle('sumir');
});

fecharPublicacao.addEventListener('click', ()=> {
	publicarBody.classList.toggle('sumir');
});

function abrirPublicar(){
	publicarBody.classList.toggle('sumir');
}

function sumirSalvo(id) {
	const botaoSalvar = document.getElementById('publicacoesList:'+id+':publicacao:salvar-post');
	botaoSalvar.classList.toggle('sumir');
}

function mostrarComentario(id) {
	const abaComentario = document.getElementById('publicacoesList:'+ id + ':publicacao:comentarios-respostas');
	const postFooter = document.getElementById('publicacoesList:'+ id +':publicacao:footer');
	abaComentario.classList.toggle('sumir');
	postFooter.classList.toggle('semBordaBottom');
}

function mostrarRespotas(id){
	const abaRespostas = document.getElementById('respostas' + id);
	abaRespostas.classList.toggle('sumir');
}


