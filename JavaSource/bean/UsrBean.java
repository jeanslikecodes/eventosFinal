package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.omnifaces.util.Messages;

import entidade.Usuario;
import servico.ServicoUsuario;

@Named
@ViewScoped
public class UsrBean implements Serializable{

	private static final long serialVersionUID = -80609744645696869L;
	
	private Usuario usuario;
	
	private List<Usuario> listUsuario;
	
	
	@EJB
	ServicoUsuario servicousr;
	
	public UsrBean(){
		this.usuario = new Usuario();
		this.listUsuario = new ArrayList<Usuario>();
	}
	
	
	
	public void cadastrarUsr(){
		try{
			this.servicousr.cadastrarUsuario(this.usuario);
			this.usuario = new Usuario();			
			FacesMessage msg = new FacesMessage("Seja bem vindo!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			}catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	

	
	public void removerUsr(Usuario usuario){
		this.servicousr.remover(usuario);
		Messages.create("Usuario Removido com sucesso");
	}
	

	public Usuario getUsr() {
		return usuario;
	}

	public void setEvento(Usuario usuario) {
		this.usuario = usuario;
	}

}
