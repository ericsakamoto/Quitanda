package quitanda.controllers;

import quitanda.models.Compra;
import quitanda.models.CompraDao;
import quitanda.response.Response;
import quitanda.response.ResponseCompra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to interact with the MySQL database using the CompraDao class.
 *
 * @author Eric Sakamoto
 */
@CrossOrigin
@RestController
public class CompraController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /purchase/create --> Criar uma nova compra
	 * 
	 * @param produto
	 *            Nome do produto
	 * @param tipo
	 *            Tipo do produto (verdura, legume, fruta, outros)
	 * @param unidade
	 *            Unidade (caixa, pacote, kg, etc.)
	 * @param quantidade
	 *            Quantidade
	 * @param preco
	 *            Preco
	 * @param data
	 *            Data da Compra
	 * 
	 * @return Mensagem informando se compra foi criada com sucesso.
	 */
	@RequestMapping("/purchase/create")
	public Response create(String produto, String tipo, String unidade, String quantidade, String preco, String data) {
		Compra compra = null;
		try {
			compra = new Compra(produto, tipo, unidade, new Integer(quantidade), new Float(preco), parseDate(data));
			compraDao.save(compra);
		} catch (Exception ex) {
			return new Response("999","Erro ao criar a compra: " + ex.toString());
		}
		return new ResponseCompra("0", "Compra incluida com sucesso");
	}

	/**
	 * /purchase/delete --> Excluir a compra pelo ID.
	 * 
	 * @param id
	 *            id da compra a ser deletada
	 * @return Mensagem informando se a compra foi excluida com sucesso.
	 */
	@RequestMapping("/purchase/delete")
	public Response delete(long id) {
		try {
			Compra compra = new Compra(id);
			compraDao.delete(compra);
		} catch (Exception ex) {;
			return new Response("999","Erro ao excluir a compra: " + ex.toString());
		}
		return new ResponseCompra("0", "Compra " + id + " excluida com sucesso");
	}

	/**
	 * /purchase/get-by-date --> Retorna lista de compras a partir da data.
	 * 
	 * @param data
	 *            data da compra
	 * @return Lista de compras ou mensagem de erro.
	 */
	@RequestMapping("/purchase/get-by-date")
	public Response getComprasPorData(String data) {
		List<Compra> compras = null;
		Float soma = new Float(0.0);
		try {
			compras = compraDao.findByDataOrderByIdAsc(parseDate(data));
			soma = somarCompras(compras);
		} catch (Exception ex) {
			return new Response("999", ex.getMessage());
		}
		return new ResponseCompra("0", "Sucesso", compras, soma);
	}

	/**
	 * /product/update --> Atualizar o produto, tipo, unidade, quantidade e
	 * preco pelo ID.
	 * 
	 * @param id
	 *            ID da compra
	 * @param produto
	 *            Novo produto
	 * @param tipo
	 *            Novo tipo
	 * @param unidade
	 *            Nova unidade
	 * @param quantidade
	 *            Nova quantidade
	 * @param preco
	 *            Nova preco
	 * @param data
	 * 			  Nova data
	 * @return Mensagem informando se compra foi atualizada com sucesso.
	 */
	@RequestMapping("/purchase/update")
	public String update(long id, String produto, String tipo, String unidade, String quantidade, String preco, String data) {
		try {
			Compra compra = compraDao.findOne(id);
			compra.setProduto(produto);
			compra.setTipo(tipo);
			compra.setUnidade(unidade);
			compra.setQuantidade(new Integer(quantidade));
			compra.setPreco(new Float(preco));
			compra.setData(parseDate(data));
			compraDao.save(compra);
		} catch (Exception ex) {
			return "Erro ao atualizar a compra: " + ex.toString();
		}
		return "Compra atualizada com sucesso!";
	}

	private Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date.substring(0, 10));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Float somarCompras(List<Compra> compras) {
		
		Float partialSum = new Float(0.0);
		for (Iterator<Compra> iterator = compras.iterator(); iterator.hasNext();) {
			Compra compra = (Compra) iterator.next();
			partialSum += compra.getPreco();
		}
		System.out.println("Soma: " + partialSum);
		return partialSum;
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private CompraDao compraDao;

} // class UserController
