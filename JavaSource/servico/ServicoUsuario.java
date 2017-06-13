package servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidade.Usuario;

@Stateless
public class ServicoUsuario {

	@PersistenceContext
	private EntityManager entityManager;

	public Usuario cadastrarUsuario(Usuario usuario) {
		this.entityManager.persist(usuario);
		return usuario;
	}

	public Usuario verificaUsuario(Usuario usuario) {
		try {
			Query query = this.entityManager
					.createQuery("SELECT u FROM Usuario u WHERE u.username = :p1 and u.senha = :p2");

			query.setParameter("p1", usuario.getUsername());
			query.setParameter("p2", usuario.getSenha());

			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Usuario> listarUsuario() {
		Query query = this.entityManager.createQuery("from Usuario");
		return query.getResultList();
	}

	public void remover(Usuario usuario) {
		this.entityManager.remove(this.entityManager.merge(usuario));
	}

	// Alterar dados do usuario

}
