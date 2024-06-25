package fintech.factory;

import fintech.dao.EntradaDAO;
import fintech.dao.GastoDAO;
import fintech.dao.UsuarioDAO;
import fintech.dao.impl.OracleEntradaDAO;
import fintech.dao.impl.OracleGastoDAO;
import fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

	public static EntradaDAO getEntradaDAO() {
		return new OracleEntradaDAO();
	}
	

public static GastoDAO getGastoDAO() {
	return new OracleGastoDAO();
}


public static UsuarioDAO getUsuarioDAO() {
	return new OracleUsuarioDAO();
}

}