package br.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

import org.apache.poi.ss.formula.functions.T;

public class RelatorioDAO extends GenericDAO<T> {

	public ResultSet executaRelatorio(String _xml, String _query, Connection con)
			throws Exception {
		ResultSet rs = null;
		try {
			CallableStatement cs = con.prepareCall(_query);
			cs.setString(1, _xml);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(2);

		} catch (Exception e) {
			throw new Exception(e);
		}
		return rs;
	}

}
