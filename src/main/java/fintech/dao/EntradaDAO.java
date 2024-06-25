package fintech.dao;

import java.util.List;
import fintech.bean.EntradaModel;
import fintech.exception.DBException;

public interface EntradaDAO {
	
	void cadastrar(EntradaModel entrada) throws DBException;
	void atualizar(EntradaModel entrada) throws DBException;
	void remover(int codigo) throws DBException;
	EntradaModel buscar(int id);
	List<EntradaModel> listar();
}