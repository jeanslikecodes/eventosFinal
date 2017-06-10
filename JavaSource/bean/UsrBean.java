package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;

import org.omnifaces.util.Messages;

import entidade.Usuario;
import servico.ServicoUsuario;

@Named
@SessionScoped
public class UsrBean extends HttpServlet implements Serializable {

	private static final long serialVersionUID = -80609744645696869L;

	private Usuario usuario;

	private Usuario usuarioLogado;

	private List<Usuario> listUsuario;

	@EJB
	ServicoUsuario servicousr;

	public UsrBean() {
		this.usuario = new Usuario();
		this.listUsuario = new ArrayList<Usuario>();
	}

	public void cadastrarUsr() {
		try {
			this.servicousr.cadastrarUsuario(this.usuario);
			this.usuario = new Usuario();
			// FacesMessage msg = new FacesMessage("Seja bem vindo!");
			// FacesContext.getCurrentInstance().addMessage(null, msg);
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void removerUsr(Usuario usuario) {
		this.servicousr.remover(usuario);
		Messages.create("Usuario Removido com sucesso");
	}

	public String verificaLogin() {
		this.usuarioLogado = this.servicousr.verificaUsuario(this.usuario);
		if (this.usuarioLogado != null) {
			return "cadEventos?faces-redirect=true";
		} else {
			/**
			 * Mantem o cara na mesma tela
			 */
			FacesMessage message = new FacesMessage();
			message.setSummary("Login inválido");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			/**
			 * Colocar a mensagem na lista pra mostrar na tela
			 */
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}

	}

	public Usuario getUsr() {
		return usuario;
	}

	public void setEvento(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
