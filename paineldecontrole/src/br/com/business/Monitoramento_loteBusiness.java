package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.Monitoramento_loteDAO;
import br.com.model.Monitoramento_lote;
import br.com.util.Conexao;

public class Monitoramento_loteBusiness extends
		GenericBusiness<Monitoramento_lote> {

	public List<Monitoramento_lote> consultar(String _nomelote, String _query) throws Exception {
		Connection con = Conexao.getConnection();
		ResultSet rs = null;
		List<Monitoramento_lote> list = new ArrayList<Monitoramento_lote>();
		try {
			Monitoramento_loteDAO dao = new Monitoramento_loteDAO();
			rs = dao.consultar(_nomelote, _query, con);
			con.commit();

			Monitoramento_lote monitoramento_lote = null;
			while (rs.next()) {
				monitoramento_lote = new Monitoramento_lote();
				monitoramento_lote.setNomearquivo(rs.getString("NOME_ARQUIVO"));
				monitoramento_lote.setStatus(rs.getString("STATUS"));
				monitoramento_lote.setTotallinhas(rs.getBigDecimal("TOTAL_LINHAS"));
				monitoramento_lote.setValortotalarquivo(rs.getBigDecimal("VALOR_ARQUIVO"));
				monitoramento_lote.setLinhasprocessadas(rs.getBigDecimal("LINHAS_PROCESSADAS"));
				monitoramento_lote.setValorprocessado(rs.getBigDecimal("VALOR_PROCESSADO"));
				monitoramento_lote.setLinhaprocessadaserro(rs.getBigDecimal("LINHAS_ERRO"));
				monitoramento_lote.setValorprocessadoerro(rs.getBigDecimal("VALOR_LINHA_ERRO"));
				monitoramento_lote.setDataimportacao(rs.getString("DATA_IMPORTACAO"));
				list.add(monitoramento_lote);
			}
			
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
		return list;
	}

}
