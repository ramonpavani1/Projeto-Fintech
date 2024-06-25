package fintech.dao;

import java.util.List;
import fintech.bean.GastoModel;
import fintech.exception.DBException;

public interface GastoDAO {
	
	void cadastrar(GastoModel gasto) throws DBException;
	void atualizar(GastoModel gasto) throws DBException;
	void remover(int codigo) throws DBException;
	GastoModel buscar(int id);
	List<GastoModel> listar();
}