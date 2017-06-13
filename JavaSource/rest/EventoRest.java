package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entidade.EventoEnt;
import servico.ServicoEvento;

@Path("/eventorest")
public class EventoRest {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvarEvento(EventoEnt evento){
		try{
			new ServicoEvento().cadastrar(evento);
		}catch (Exception e) {
			e.printStackTrace();
			//e.getMessage();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventoEnt> listar(){
		List<EventoEnt> listaEventos = new ServicoEvento().listarTodos();
		return listaEventos;
	}

}
