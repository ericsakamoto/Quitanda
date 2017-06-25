package quitanda.controllers;

import quitanda.models.Tipo;
import quitanda.models.TipoDao;
import quitanda.response.Response;
import quitanda.response.ResponseTipo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to interact with the MySQL database using the TipoDao class.
 *
 * @author Eric Sakamoto
 */
@CrossOrigin
@RestController
public class TipoController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /type/create --> Criar um novo tipo de produto
	 * 
	 * @param nome
	 *            Nome do Tipo do produto (verdura, legume, fruta, outros)
	 *            
	 * @return Mensagem informando se o tipo do produto foi criado com sucesso.
	 */
	@RequestMapping("/type/create")
	public String create(String nome) {
		Tipo tipo = null;
		try {
			tipo = new Tipo(nome);
			tipoDao.save(tipo);
		} catch (Exception ex) {
			return "Erro ao criar o tipo: " + ex.toString();
		}
		return "Tipo criado com sucesso! (id = " + tipo.getId() + ")";
	}

	/**
	 * /type/delete --> Excluir o tipo pelo ID.
	 * 
	 * @param id
	 *            id do tipo a ser deletado
	 * @return Mensagem informando se tipo foi excluido com sucesso.
	 */
	@RequestMapping("/type/delete")
	public String delete(long id) {
		try {
			Tipo tipo = new Tipo(id);
			tipoDao.delete(tipo);
		} catch (Exception ex) {
			return "Erro ao excluir o tipo: " + ex.toString();
		}
		return "Tipo excluido com sucesso!";
	}

	/**
	 * /type/update --> Atualizar o nome pelo ID.
	 * 
	 * @param id
	 *            ID do tipo
	 * @param nome
	 *            Novo nome
	 *            
	 * @return Mensagem informando se tipo foi atualizado com sucesso.
	 */
	@RequestMapping("/type/update")
	public String update(long id, String nome) {
		try {
			Tipo tipo = tipoDao.findOne(id);
			tipo.setNome(nome);
			tipoDao.save(tipo);
		} catch (Exception ex) {
			return "Erro ao atualizar tipo: " + ex.toString();
		}
		return "Tipo atualizado com sucesso!";
	}

	/**
	 * /listAll --> Listar todos tipos.
	 *            
	 * @return Lista de tipos.
	 */
	@RequestMapping("/type/listAll")
	public Response listAll() {
		Iterable<Tipo> tipos = null;
		try {
			tipos = tipoDao.findAllByOrderByNomeAsc();
		} catch (Exception ex) {
			return new Response("999",ex.getMessage());
		}
		return new ResponseTipo("0","Sucesso",tipos);
	}	

	/**
	 * /get-by-id --> Listar todos tipos.
	 *            
	 * @param ID           
	 *            
	 * @return Lista de tipos.
	 */
	@RequestMapping("/type/get-by-id")
	public Response getById(String id) {
		List<Tipo> tipos = null;
		try {
			Tipo tipo = tipoDao.findByIdOrderByNomeAsc(new Long(id));
			tipos = new ArrayList<Tipo>();
			tipos.add(tipo);
		} catch (Exception ex) {
			return new Response("999",ex.getMessage());
		}
		return new ResponseTipo("0","Sucesso",tipos);
	}		
	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private TipoDao tipoDao;

} // class TipoController
