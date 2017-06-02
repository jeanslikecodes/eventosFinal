package servico;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidade.EventoEnt;

@Stateless
public class ServicoEvento implements Serializable {

	
	private static final long serialVersionUID = -3886021972844385036L;
	
	@PersistenceContext
	private EntityManager entityManager;
		
	public EventoEnt cadastrar(EventoEnt evento){
		this.entityManager.persist(evento);
		return evento;
	}
	
	public void remover(EventoEnt evento){
		this.entityManager.remove(this.entityManager.merge(evento));
	}
	
	@SuppressWarnings("unchecked")
	public List<EventoEnt> listarTodos(){
		Query query = this.entityManager.createQuery("FROM EventoEnt event");
		return query.getResultList();
	}
	
	public EventoEnt buscarPorNome(String nomeEvent){
		try{
			return (EventoEnt) this.entityManager
					.createQuery("FROM EventoEnt e WHERE e.nomeEvento = :p1")
					.setParameter("p1", nomeEvent)
					.getSingleResult();
		}catch (NoResultException nre) {
			return null;
		}
		
	}
}
