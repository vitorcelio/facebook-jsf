package com.facebook.jsf.service;

import static com.facebook.jsf.utils.FacebookUtils.getInputStreamToBytes;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.Part;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.facebook.jsf.form.ProdutoForm;
import com.facebook.jsf.modelo.Categoria;
import com.facebook.jsf.modelo.Produto;
import com.facebook.jsf.modelo.Usuario;
import com.facebook.jsf.repository.CategoriaRepository;
import com.facebook.jsf.repository.ProdutoRepository;
import com.facebook.jsf.repository.UsuarioRepository;
import com.facebook.jsf.utils.FacebookUtils;

@Stateless
public class ProdutoService {

	@Inject
	private ProdutoRepository dao;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Inject
	private UsuarioRepository usuarioRepository;

	public void salvar(ProdutoForm form) {
		AWSCredentials credentials = new BasicAWSCredentials(
				  "AKIAZ75UGUJ7UTDLSGXN", 
				  "o4mk9sfrk50VSYSDfk0+//4UBBLsKbqBvB4HzqRs"
				);
		
		String bucketName = "teste-facebook";
		
		try {
			Categoria categoria = categoriaRepository.findById(form.getIdCategoria());
			Usuario usuario = usuarioRepository.findById(form.getIdUsuario());
			Produto produto = new Produto();
			Date dataProduto = new Date();

			String imagem64 = null;

			try {
				imagem64 = FacebookUtils.getImagemBase64(form.getImagem());
			} catch (Exception e) {
				e.printStackTrace();
			}

			produto.setCategoria(categoria);
			produto.setCondicao(form.getCondicao());
			produto.setLatitude(form.getLatitude());
			produto.setLongitude(form.getLongitude());
			produto.setDescricao(form.getDescricao());
			produto.setData(dataProduto);
			produto.setCidade(form.getCidade());
			produto.setCidadeQuery(form.getCidadeQuery());
			produto.setImagem64(imagem64);
			produto.setUsuario(usuario);
			produto.setNome(form.getTitulo());
			produto.setPreco(form.getPreco());
			produto.setToken(FacebookUtils.gerarToken(form.getTitulo(), form.getCidade(), imagem64, usuario));
			
			
			dao.salvar(produto);
			
			AmazonS3 s3client = AmazonS3ClientBuilder
					.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion(Regions.US_EAST_1)
					.build();
			
			s3client.putObject(bucketName, "Imagem/" + produto.getId() + ".png", arquivoAwsS3(form.getImagem()));
			
			produto.setUrlImagemAmazons3("https://teste-facebook.s3.amazonaws.com/Imagem/" + produto.getId() + ".png");
			dao.atualizar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File arquivoAwsS3(Part imagem) throws IOException {
		byte[] streamToBytes = getInputStreamToBytes(imagem.getInputStream());
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(streamToBytes));
		
		File imagemPng = new File("image.png");
		
		ImageIO.write(bi, "png", imagemPng);
		
		return imagemPng;
	}
	
	public void atualizar(ProdutoForm form) {
		try {
			Categoria categoria = categoriaRepository.findById(form.getIdCategoria());
			Usuario usuario = usuarioRepository.findById(form.getIdUsuario());
			Produto produto = dao.findById(form.getId());
			Date dataProduto = new Date();

			String imagem64 = null;

			try {
				imagem64 = FacebookUtils.getImagemBase64(form.getImagem());
			} catch (Exception e) {
			}

			produto.setCategoria(categoria);
			produto.setCondicao(form.getCondicao());
			produto.setLatitude(form.getLatitude());
			produto.setLongitude(form.getLongitude());
			produto.setDescricao(form.getDescricao());
			produto.setData(dataProduto);
			produto.setCidade(form.getCidade());
			produto.setCidadeQuery(form.getCidadeQuery());
			produto.setImagem64(imagem64);
			produto.setUsuario(usuario);
			produto.setNome(form.getTitulo());
			produto.setPreco(form.getPreco());
			dao.atualizar(produto);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(Long id) {
		Produto produto = dao.findById(id);
		dao.deletar(produto);
	}

	public List<Produto> getAll() {
		return dao.getAll();
	}

	public List<Produto> getAll(Integer inicio, Integer fim) {
		return dao.getAll(inicio, fim);
	}

	public List<Produto> getAllByUsuarioId(Long id) {
		return dao.getAllByUsuarioId(id);
	}

	public List<Produto> getAllByUsuarioId(Long id, Integer inicio, Integer fim) {
		return dao.getAllByUsuarioId(id, inicio, fim);
	}

	public List<Produto> getAllByCategoriaId(Long id) {
		return dao.getAllByCategoriaId(id);
	}

	public List<Produto> getAllByCategoriaId(Long id, Integer inicio, Integer fim) {
		return dao.getAllByCategoriaId(id, inicio, fim);
	}
	
	public List<Produto> getAllByCategoriaNome(String nome, Integer inicio, Integer fim) {
		return dao.getAllByCategoriaNome(nome, inicio, fim);
	}
	
	public List<Produto> getAllByCidadeAndCategoriaNome(String cidade, String categoria, Integer inicio, Integer fim) {
		return dao.getAllByCidadeAndCategoriaNome(cidade, categoria, inicio, fim);
	}

	public Produto findById(Long id) {
		return dao.findById(id);
	}

	public Produto findByToken(String token) {
		return dao.findByToken(token);
	}

}
