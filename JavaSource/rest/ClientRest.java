package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entidade.Usuario;
import servico.ServicoUsuario;

@Path("/restCliente")
public class ClientRest{

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Path("/post")
	public void cadastrarUsuario(Usuario usuario){
		try{
			new ServicoUsuario().cadastrarUsuario(usuario);
		}catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarUsuario(){
		try{
			return new ServicoUsuario().listarTodos();
		}catch (Exception e) {
			e.printStackTrace();
			//e.getMessage();
		}
		return null;
	}
}
