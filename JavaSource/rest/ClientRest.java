package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import entidade.Usuario;
import servico.ServicoUsuario;

@Path("/clientrest")
public class ClientRest {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarUsuario(Usuario usuario){
		try{
			new ServicoUsuario().cadastrarUsuario(usuario);
		}catch (Exception e) {
			e.printStackTrace();
			//e.getMessage();
		}
	}
}
