package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.poi.ss.formula.functions.T;

import br.com.dao.RelatorioDAO;
import br.com.util.Conexao;

public class RelatorioBusiness extends GenericBusiness<T> {

	public ResultSet executaRelatorio(String _xml,	String _query) throws Exception {
		Connection con = Conexao.getConnection();
		ResultSet rs = null;
		try {
			RelatorioDAO dao = new RelatorioDAO();
			rs = dao.executaRelatorio(_xml, _query, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}

		return rs;
	}

}
