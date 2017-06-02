package servico;

import java.util.List;

import entidade.EventoEnt;

public interface InterfaceServico<T> {
	
	public EventoEnt cadastrar(T t);
	
	public void remover(T t);
	
	public List<T> listarTodos();
	
	public T buscarPorNome(String nome);

}
