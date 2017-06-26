package quitanda.controllers;

import quitanda.models.Produto;
import quitanda.models.ProdutoDao;
import quitanda.response.Response;
import quitanda.response.ResponseProduto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to interact with the MySQL database using the ProdutoDao class.
 *
 * @author Eric Sakamoto
 */
@CrossOrigin
@RestController
public class ProdutoController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /product/create --> Criar um novo produto
	 * 
	 * @param tipo
	 *            Tipo do produto (verdura, legume, fruta, outros)
	 * @param nome
	 *            Nome do produto
	 * @return Mensagem informando se produto foi criado com sucesso.
	 */
	@RequestMapping("/product/create")
	public String create(String tipo, String nome) {
		Produto produto = null;
		try {
			produto = new Produto(nome, tipo);
			produtoDao.save(produto);
		} catch (Exception ex) {
			return "Erro ao criar o produto: " + ex.toString();
		}
		return "Produto criado com sucesso! (id = " + produto.getId() + ")";
	}

	/**
	 * /product/delete --> Excluir o produto pelo ID.
	 * 
	 * @param id
	 *            id do produto a ser deletado
	 * @return Mensagem informando se produto foi excluido com sucesso.
	 */
	@RequestMapping("/product/delete")
	public String delete(long id) {
		try {
			Produto produto = new Produto(id);
			produtoDao.delete(produto);
		} catch (Exception ex) {
			return "Erro ao excluir o produto: " + ex.toString();
		}
		return "Produto excluido com sucesso!";
	}

	/**
	 * /product/get-by-type --> Return the id for the user having the passed email.
	 * 
	 * @param tipo
	 *            tipo de produto
	 * @return Lista de  or a message error if the user is not found.
	 */
	@RequestMapping("/product/get-by-type")
	public Response getProdutoPorTipo(String tipo) {
		List<Produto> produtos = null;
		try {
			produtos = produtoDao.findByTipoOrderByNomeAsc(tipo);			
		} catch (Exception ex) {
			return new Response("999",ex.getMessage());
		}
		return new ResponseProduto("0","Sucesso",produtos);
	}
	
	/**
	 * /product/get-by-id --> Return product by id.
	 * 
	 * @param id
	 *            id do produto
	 * @return Product or a message error if the user is not found.
	 */
	@RequestMapping("/product/get-by-id")
	public Response getProdutoPorID(String id) {
		Long l = new Long(id);
		System.out.println("ID: " + l);
		Produto produto = null;
		try {
			produto = produtoDao.findOne(l.longValue());
			System.out.println("Nome: " + produto.getNome());
		} catch (Exception ex) {
			ex.printStackTrace();
			return new Response("999",ex.getMessage());
		}
		return new ResponseProduto("0","Sucesso",produto);
	}	

	/**
	 * /product/update --> Atualizar o nome, tipo e unidade pelo ID.
	 * 
	 * @param id
	 *            ID do produto
	 * @param nome
	 *            Novo nome
	 * @param tipo
	 *            Novo tipo
	 * @param unidade
	 *            Nova unidade
	 * @return Mensagem informando se produto foi atualizado com sucesso.
	 */
	@RequestMapping("/product/update")
	public String update(long id, String nome, String tipo) {
		try {
			Produto produto = produtoDao.findOne(id);
			produto.setNome(nome);
			produto.setTipo(tipo);
			produtoDao.save(produto);
		} catch (Exception ex) {
			return "Erro ao atualizar produto: " + ex.toString();
		}
		return "Produto atualizado com sucesso!";
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private ProdutoDao produtoDao;

} // class UserController
