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
	
	
	public boolean verificaUsuario(Usuario usuario){
		try {
			Query query = this.entityManager.createQuery(
					"SELECT u from PUBLIC.USUARIO u where u.username = :username and u.senha = :senha");
			query.setParameter("name" , usuario.getUsername());
			query.setParameter("senha", usuario.getSenha()).getSingleResult();

	        return true;
	     } catch (NoResultException e) {
	            return false;
	     }
		
	}
	    
	
	public void remover(Usuario usuario){
		this.entityManager.remove(this.entityManager.merge(usuario));
	}

	
	//Alterar dados do usuario	
	

}
