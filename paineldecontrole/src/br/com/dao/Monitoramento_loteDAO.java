package br.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;
import br.com.model.Monitoramento_lote;

public class Monitoramento_loteDAO extends GenericDAO<Monitoramento_lote> {

	public ResultSet consultar(String _nomelote, String _query, Connection con)
			throws Exception {
		ResultSet rs = null;
		try {
			CallableStatement cs = con.prepareCall(_query);
			cs.setString(1, _nomelote);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.execute();

			rs = (ResultSet) cs.getObject(2);

		} catch (Exception e) {
			throw new Exception(e);
		}
		return rs;
	}

}
