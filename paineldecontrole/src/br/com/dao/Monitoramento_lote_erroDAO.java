package br.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;
import br.com.model.Monitoramento_lote_erro;

public class Monitoramento_lote_erroDAO extends
		GenericDAO<Monitoramento_lote_erro> {

	public ResultSet consultar(String _nomelote, String _nomearquivo, String _query, Connection con)
			throws Exception {
		ResultSet rs = null;
		try {
			CallableStatement cs = con.prepareCall(_query);
			cs.setString(1, _nomelote);
			cs.setString(2, _nomearquivo);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.execute();

			rs = (ResultSet) cs.getObject(3);

		} catch (Exception e) {
			throw new Exception(e);
		}
		return rs;
	}
}
