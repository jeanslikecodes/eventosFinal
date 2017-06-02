package servico;

import javax.ejb.Stateless;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import entidade.Usuario;


@Named
@Stateless
public class ServicoUsuario {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Usuario cadastrarUsuario(Usuario usuario){
		this.entityManager.persist(usuario);
		return usuario;
	}
	
	
	public Usuario VerificarCadastro(String username, String senha){
		Query query = this.entityManager.createQuery(
                "SELECT u from Usuario u where u.username = :username and u.senha = :senha");
	query.setParameter("name" , username);
	query.setParameter("senha", senha).getSingleResult();
		
		try {
	    	

            return (Usuario)query.getSingleResult();
      } catch (NoResultException e) {
            return null;
      }
	}
	    
	
	public void remover(Usuario usuario){
		this.entityManager.remove(this.entityManager.merge(usuario));
	}

	
	//Alterar dados do usuario	
	

}
