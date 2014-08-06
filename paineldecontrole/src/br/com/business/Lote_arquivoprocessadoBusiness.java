package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.Lote_arquivoprocessadoDAO;
import br.com.model.Lote_arquivoprocessado;
import br.com.util.Conexao;

public class Lote_arquivoprocessadoBusiness extends GenericBusiness<Lote_arquivoprocessado> {

	public List<Lote_arquivoprocessado> consultar(String _xml, String _query) throws Exception {
		Connection con = Conexao.getConnection();
		ResultSet rs = null;
		List<Lote_arquivoprocessado> list = new ArrayList<Lote_arquivoprocessado>();
		List<ResultSet> list_result = new ArrayList<ResultSet>();
		Lote_arquivoprocessado lote_arquivoprocessado = null;
		try {
			Lote_arquivoprocessadoDAO dao = new Lote_arquivoprocessadoDAO();
			list_result = dao.consultar(_xml, _query, con);
			con.commit();

			for (int i = 0; i < list_result.size(); i++) {
				rs = list_result.get(i);
				while (rs.next()) {
					lote_arquivoprocessado = new Lote_arquivoprocessado();
					lote_arquivoprocessado.setNomelote(rs.getString("NOME_LOTE"));
					lote_arquivoprocessado.setQuantidadearquivo(rs.getBigDecimal("QTDE_ARQUIVOS"));
					lote_arquivoprocessado.setTotalregistro(rs.getBigDecimal("TOTAL_REGISTROS"));
					lote_arquivoprocessado.setValor(rs.getBigDecimal("VALOR_TOTAL"));
					lote_arquivoprocessado.setDataprocessamento(rs.getString("DATA_PROCESSAMENTO"));
	
					list.add(lote_arquivoprocessado);
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
