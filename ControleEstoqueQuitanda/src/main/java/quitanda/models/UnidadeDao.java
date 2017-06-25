package quitanda.models;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author Eric Sakamoto
 */
@Transactional
public interface UnidadeDao extends CrudRepository<Unidade, Long> {

	  /**
	   * Retorna Unidades
	   * 
	   */
	  public List<Unidade> findAllByOrderByNomeAsc();
	
} // class UnidadeDao
