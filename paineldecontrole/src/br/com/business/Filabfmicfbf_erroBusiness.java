package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.Filabfmicfbf_erroDAO;
import br.com.model.Filabfmicfbf_erro;
import br.com.model.Monitoramento;
import br.com.util.Conexao;

public class Filabfmicfbf_erroBusiness extends
		GenericBusiness<Filabfmicfbf_erro> {

	public List<Filabfmicfbf_erro>  consultar(String _nomearquivo, String _query) throws Exception {
		Connection con = Conexao.getConnection();
		ResultSet rs = null;
		List<Filabfmicfbf_erro> list 		= new ArrayList<Filabfmicfbf_erro>();
		List<ResultSet> 		list_result = new ArrayList<ResultSet>();

		try {
			Filabfmicfbf_erroDAO dao = new Filabfmicfbf_erroDAO();
			list_result = dao.consultar(_nomearquivo, _query, con);
			con.commit();

			Filabfmicfbf_erro filabfmicfbf_erro = null;
			for (int i = 0; i < list_result.size(); i++) {
				rs = list_result.get(i);
				while (rs.next()) {
					filabfmicfbf_erro = new Filabfmicfbf_erro();

					filabfmicfbf_erro.setDescricaoerro(rs.getString("DESC_ERRO"));
					filabfmicfbf_erro.setQuantidadeimpactado(rs.getBigDecimal("QTDE_ERROS"));
					filabfmicfbf_erro.setValorimpactado(rs.getBigDecimal("VALOR_ERRO"));
					
					list.add(filabfmicfbf_erro);
				}
			}
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}

		return list;
	}

}
