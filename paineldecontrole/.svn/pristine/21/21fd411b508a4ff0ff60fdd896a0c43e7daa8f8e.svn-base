package br.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import br.com.model.Filabfmicfbf_erro;

public class Filabfmicfbf_erroDAO extends GenericDAO<Filabfmicfbf_erro> {

	public List<ResultSet> consultar(String _xml, String _query, Connection con)
			throws Exception {
		
		List<ResultSet> list_result = new ArrayList<ResultSet>();
		ResultSet rs_bfm = null;
		ResultSet rs_bff = null;

		try {
			CallableStatement cs = con.prepareCall(_query);
			cs.setString(1, _xml);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.execute();

			
			rs_bfm = (ResultSet) cs.getObject(2);
			rs_bff = (ResultSet) cs.getObject(3);
			
			if (rs_bfm != null){
				list_result.add(rs_bfm);
			}else if (rs_bff != null){
				list_result.add(rs_bff);
			}
			
			

		} catch (Exception e) {
			throw new Exception(e);
		}
		return list_result;
	}

}
