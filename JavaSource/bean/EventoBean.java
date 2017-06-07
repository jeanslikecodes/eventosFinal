package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


import org.omnifaces.util.Messages;

import entidade.EventoEnt;

import servico.ServicoEvento;

@Named
@ViewScoped
public class EventoBean implements Serializable {

	
	private static final long serialVersionUID = -5372082140816019661L;
	
	private EventoEnt evento;
	
	private List<EventoEnt> listaEventos;
	
	@EJB
	private ServicoEvento servicoEvento;
	
	public EventoBean(){
		this.evento = new EventoEnt();
		this.listaEventos = new ArrayList<EventoEnt>();
	}
	
	

	
	
	
	public void cadastrarEvento(){
		try{
			this.servicoEvento.cadastrar(this.evento);
			this.evento = new EventoEnt();			
			FacesMessage msg = new FacesMessage("Evento Cadastrado Com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			}
		catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void removerEvento(EventoEnt evento){
		this.servicoEvento.remover(evento);
		Messages.create("Evento Removido com sucesso");
	}
	
	public List<EventoEnt> getlistaEvento(){
		this.listaEventos = servicoEvento.listarTodos();
		return listaEventos;
	}

	public EventoEnt getEvento() {
		return evento;
	}

	public void setEvento(EventoEnt evento) {
		this.evento = evento;
	}
	
	
	//Alterar dados do evento
	//Comentar eventos
	
}
