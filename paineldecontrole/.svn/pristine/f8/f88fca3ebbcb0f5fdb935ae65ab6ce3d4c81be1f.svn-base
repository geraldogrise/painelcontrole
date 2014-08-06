package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.MonitoramentoDAO;
import br.com.model.Monitoramento;
import br.com.util.Conexao;

public class MonitoramentoBusiness extends GenericBusiness<Monitoramento> {

	public List<Monitoramento>  consultar(String _xml, String _query) throws Exception {
		Connection con = Conexao.getConnection();
		ResultSet rs = null;
		List<Monitoramento> list = new ArrayList<Monitoramento>();
		List<ResultSet> list_result = new ArrayList<ResultSet>();
		try {
			MonitoramentoDAO dao = new MonitoramentoDAO();
			list_result = dao.consultar(_xml, _query, con);
			con.commit();

			Monitoramento monitoramento = null;
			for (int i = 0; i < list_result.size(); i++) {
				rs = list_result.get(i);
				while (rs.next()) {
					monitoramento = new Monitoramento();
					monitoramento.setNomelote(rs.getString("NOME_LOTE"));
					monitoramento.setStatuslote(rs.getString("STATUS_LOTE"));
					monitoramento.setTotalarquivo(rs.getBigDecimal("TOTAL_ARQUIVOS"));
					monitoramento.setTotalregistro(rs.getBigDecimal("QTDE_REGISTROS"));
					monitoramento.setValorlote(rs.getBigDecimal("VALOR"));
					monitoramento.setArquivonaoprocessado(rs.getBigDecimal("QTDE_ARQS_OK"));
					monitoramento.setArquivoprocessadoerro(rs.getBigDecimal("QTDE_ARQS_ERROS"));
					monitoramento.setDataimportacao(rs.getString("DATA_IMPORTACAO"));
					list.add(monitoramento);
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
