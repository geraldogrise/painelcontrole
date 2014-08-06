package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.Monitoramento_lote_erroDAO;
import br.com.model.Monitoramento_lote_erro;
import br.com.util.Conexao;

public class Monitoramento_lote_erroBusiness extends GenericBusiness<Monitoramento_lote_erro> {

	
	public List<Monitoramento_lote_erro> consultar(String _nomelote, String _nomearquivo, String _query) throws Exception {
		Connection con = Conexao.getConnection();
		ResultSet rs = null;
		List<Monitoramento_lote_erro> list = new ArrayList<Monitoramento_lote_erro>();
		try {
			Monitoramento_lote_erroDAO dao = new Monitoramento_lote_erroDAO();
			rs = dao.consultar(_nomelote, _nomearquivo, _query, con);
			con.commit();

			Monitoramento_lote_erro monitoramento_lote_erro = null;
			while (rs.next()) {
				monitoramento_lote_erro = new Monitoramento_lote_erro();
				monitoramento_lote_erro.setDescricaoerro(rs.getString("DESC_ERRO"));
				monitoramento_lote_erro.setQuantidadeimpactado(rs.getBigDecimal("QTDE_ERROS"));
				monitoramento_lote_erro.setValorimpactado(rs.getBigDecimal("VALOR_ERRO"));
				list.add(monitoramento_lote_erro);
			}
			
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}

		return list;
	}

}
