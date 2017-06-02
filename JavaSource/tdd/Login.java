package tdd;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Test;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidade.Usuario;

public class Login {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Test
	public void testarVerificaUsuario() {
		Usuario usuario =  new Usuario();
		
		usuario.setUsername("jeans");
		usuario.setSenha("jeans");
	}
	
	public boolean verificaUsuario(Usuario usuario){
		try {
			Query query = this.entityManager.createQuery(
					"SELECT u from Usuario u where u.username = :username and u.senha = :senha");
			query.setParameter("name" , usuario.getUsername());
			query.setParameter("senha", usuario.getSenha()).getSingleResult();

	        return true;
	     } catch (NoResultException e) {
	            return false;
	     }
		
	}
}
