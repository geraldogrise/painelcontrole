package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.FilabfmicfbfDAO;
import br.com.model.Filabfmicfbf;
import br.com.model.Monitoramento_lote_erro;
import br.com.util.Conexao;

public class FilabfmicfbfBusiness extends GenericBusiness<Monitoramento_lote_erro> {

	public List<Filabfmicfbf>  consultar(String _xml, String _query) throws Exception {
		Connection con = Conexao.getConnection();
		ResultSet rs = null;
		List<Filabfmicfbf> list = new ArrayList<Filabfmicfbf>();
		try {
			FilabfmicfbfDAO dao = new FilabfmicfbfDAO();
			rs = dao.consultar(_xml, _query, con);
			con.commit();

			Filabfmicfbf filabfmicfbf = null;
			while (rs.next()) {
				filabfmicfbf = new Filabfmicfbf();
				filabfmicfbf.setNomelote(rs.getString("NOME_LOTE"));
				filabfmicfbf.setTotalregistro(rs.getBigDecimal("QTDE_REGISTROS"));
				filabfmicfbf.setValorlote(rs.getBigDecimal("VALOR_LOTE"));

				filabfmicfbf.setRegistroprocessado_bfm(rs.getBigDecimal("REGS_PROCESSADOS"));
				filabfmicfbf.setValorprocessado_bfm(rs.getBigDecimal("VALOR_PROCESSADO"));
				filabfmicfbf.setRegistroprocessadoerro_bfm(rs.getBigDecimal("REGS_PROC_ERROS"));
				filabfmicfbf.setValorprocessadoerro_bfm(rs.getBigDecimal("VALOR_PROC_ERROS"));
				filabfmicfbf.setUltimadata_bfm(rs.getString("DATA_IMPORTACAO"));

				filabfmicfbf.setRegistroprocessado_icfbf(rs.getBigDecimal("ICFBF_QTDE_REGS_OK"));
				filabfmicfbf.setValorprocessado_icfbf(rs.getBigDecimal("ICFBF_VALOR_PROCESSADO_OK"));
				filabfmicfbf.setRegistroprocessadoerro_icfbf(rs.getBigDecimal("ICFBF_QTDE_REGS_ERRO"));
				filabfmicfbf.setValorprocessadoerro_icfbf(rs.getBigDecimal("ICFBF_VALOR_PROCESSADO_ERRO"));
				filabfmicfbf.setUltimadata_icfbf(rs.getString("ICFBF_DATA_IMPORTACAO"));

				list.add(filabfmicfbf);
			}
			
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}

		return list;
	}


}
