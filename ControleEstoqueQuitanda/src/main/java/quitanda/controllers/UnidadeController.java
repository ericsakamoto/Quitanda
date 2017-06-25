package quitanda.controllers;

import quitanda.models.Unidade;
import quitanda.models.UnidadeDao;
import quitanda.response.Response;
import quitanda.response.ResponseUnidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to interact with the MySQL database using the UnidadeDao class.
 *
 * @author Eric Sakamoto
 */
@CrossOrigin
@RestController
public class UnidadeController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /unit/create --> Criar uma nova unidade de medida
	 * 
	 * @param nome
	 *            Nome da Unidade de medida (caixa, pacote, kg, etc.)
	 *            
	 * @return Mensagem informando se a unidade foi criada com sucesso.
	 */
	@RequestMapping("/unit/create")
	public String create(String nome) {
		Unidade unidade = null;
		try {
			unidade = new Unidade(nome);
			unidadeDao.save(unidade);
		} catch (Exception ex) {
			return "Erro ao criar a unidade: " + ex.toString();
		}
		return "Unidade criada com sucesso! (id = " + unidade.getId() + ")";
	}

	/**
	 * /unit/delete --> Excluir a unidade pelo ID.
	 * 
	 * @param id
	 *            id da unidade a ser deletada
	 * @return Mensagem informando se unidade foi excluida com sucesso.
	 */
	@RequestMapping("/unit/delete")
	public String delete(long id) {
		try {
			Unidade unidade = new Unidade(id);
			unidadeDao.delete(unidade);
		} catch (Exception ex) {
			return "Erro ao excluir a unidade: " + ex.toString();
		}
		return "Unidade excluida com sucesso!";
	}

	/**
	 * /unit/update --> Atualizar o nome pelo ID.
	 * 
	 * @param id
	 *            ID da unidade
	 * @param nome
	 *            Novo nome
	 *            
	 * @return Mensagem informando se unidade foi atualizada com sucesso.
	 */
	@RequestMapping("/unit/update")
	public String update(long id, String nome) {
		try {
			Unidade unidade = unidadeDao.findOne(id);
			unidade.setNome(nome);
			unidadeDao.save(unidade);
		} catch (Exception ex) {
			return "Erro ao atualizar unidade: " + ex.toString();
		}
		return "unidade atualizado com sucesso!";
	}
	
	/**
	 * /listAll --> Listar todas unidades.
	 *            
	 * @return Lista de unidades.
	 */
	@RequestMapping("/unit/listAll")
	public Response listAll() {
		Iterable<Unidade> unidades = null;
		try {
			unidades = unidadeDao.findAllByOrderByNomeAsc();
		} catch (Exception ex) {
			return new Response("999",ex.getMessage());
		}
		return new ResponseUnidade("0","Sucesso",unidades);
	}	

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private UnidadeDao unidadeDao;

} // class UserController
