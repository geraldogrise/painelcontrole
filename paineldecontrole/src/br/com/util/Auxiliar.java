/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import br.com.business.GenericBusiness;

/**
 * 
 * @author santos
 */
public class Auxiliar {
	private static Logger logger = Logger.getLogger(Auxiliar.class.getName());

	

	public static void main(String[] args) {
		try {
			// xmlToMovimento("<?xml version='1.0' encoding='UTF-8'?><linha><TIPO_REGISTRO>D</TIPO_REGISTRO><CODIGO_INTEGRADOR>31329</CODIGO_INTEGRADOR><TIPO_USO>1541</TIPO_USO><PRODUTO>01</PRODUTO><STATUS>01</STATUS><MOTIVO_STATUS>00001</MOTIVO_STATUS><DATA_STATUS>20130123</DATA_STATUS><HORA_STATUS>100000</HORA_STATUS><ASSINANTE_A_OPERADORA>553188811357</ASSINANTE_A_OPERADORA><TIPO_ASSINANTE>0</TIPO_ASSINANTE><CONTEUDO_ASSINANTE_B>CONTEUDO SERVICO</CONTEUDO_ASSINANTE_B><DATA_EVENTO>20130116</DATA_EVENTO><HORA_EVENTO>120000</HORA_EVENTO><CATEGORIA>CATEGORIA</CATEGORIA><VALOR_LIQUIDO>72973</VALOR_LIQUIDO><VALOR_BRUTO>72973</VALOR_BRUTO><UNIDADE_TARIFACAO>K</UNIDADE_TARIFACAO><DURACAO_REAL>100</DURACAO_REAL><DURACAO_TARIFADA>100</DURACAO_TARIFADA><UF_NF_FATURADA>BA</UF_NF_FATURADA><NUMERO_NOTA_FISCAL>7867856</NUMERO_NOTA_FISCAL><DATA_VENCIMENTO_FATURA>20130123</DATA_VENCIMENTO_FATURA><NUMERO_CONTRATO_PARCELAMENTO>873462875</NUMERO_CONTRATO_PARCELAMENTO><NUMERO_FATURA>15069872973</NUMERO_FATURA></linha>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static String retornaBase(HttpServletRequest request){
		if (request == null){
			return "SVA";
		}
		Cookie acheiCookie = null;  
		Cookie[]  meusCookies = request.getCookies();  
		for (int i = 0; i < meusCookies.length; i++)  
		    if (meusCookies[i].getName().equals("j_base"))  
		       acheiCookie = meusCookies[i];  
		  if (acheiCookie != null)  
		    return  acheiCookie.getValue();  
		  
		  
		 return null;

	}

	public static List<ModelSelect> MontaListaSelect(ResultSet dados,
			String[] camposcodigos, String descricao) throws Exception {

		List<ModelSelect> listaRetorno = new ArrayList<ModelSelect>();
		int qtdCampos = camposcodigos.length;
		while (dados.next()) {

			ModelSelect _model = new ModelSelect();

			_model.setDescricao(dados.getString(descricao));
			if (qtdCampos == 1) {
				try {
					_model.setCodigo(dados.getString(camposcodigos[0]));
				} catch (Exception e) {
					_model.setCodigo(dados.getBigDecimal(camposcodigos[0])
							.toString());
				}
			} else {
				String campo = "";
				String separador = "";
				for (String campovalor : camposcodigos) {
					campo += (separador + "{\"" + campovalor + "\":\""
							+ dados.getString(campovalor) + "\"}");
					separador = ",";
				}
				campo = "[" + campo + "]";
				_model.setCodigo(campo);
			}

			listaRetorno.add(_model);
		}
		return listaRetorno;
	}

	public static Tabela getTabelaModel(Class<?> clazz) {

		Tabela _tabela = clazz.getAnnotation(Tabela.class);
		return _tabela;
	}

	public static String retornaValor(List<Campos_Join> lista, String chave) {

		for (Campos_Join object : lista) {
			if (object.getChave().equalsIgnoreCase(chave)) {
				return object.getValor().toString();
			}
		}

		return "";
	}

	public static HashMap<String, Coluna> getColunasModel(Class<?> clazz) {

		HashMap<String, Coluna> listaAnnotation = new HashMap<String, Coluna>();

		Field[] campos = clazz.getDeclaredFields();
		for (Field campo : campos) {
			if (campo.isAnnotationPresent(Coluna.class)) {
				Coluna _col = campo.getAnnotation(Coluna.class);
				listaAnnotation.put(_col.NomeColuna(), _col);
			}

		}
		return listaAnnotation;
	}

	public static String getInsertSQLPadraoXML(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = "";
		StringBuilder _insert = new StringBuilder("insert into "
				+ tabela.NomeTabela() + " ( ");

		StringBuilder _parametros = new StringBuilder(" values ( ");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (_col.TipoPK() != TipoPKColuna.IDENTITY) {
				_insert.append(_separador + "<fwp name=\"" + _col.NomeColuna()
						+ "\">" + _col.NomeColuna() + "</fwp>");

				if (_col.TipoPK() != TipoPKColuna.SEQUENCE)
					_parametros.append(_separador + "<fwp name=\""
							+ _col.NomeColuna() + "\">:" + _col.NomeColuna()
							+ "</fwp>");
				else
					_parametros.append(_separador + "<fwp name=\""
							+ _col.NomeColuna() + "\">" + _col.NomeColuna()
							+ ".NEXTVAL </fwp>");

				_separador = ",";
			}

		}

		return _insert.toString() + ")" + _parametros.toString() + ")";
	}

	public static String getInsertSQLPadrao(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = "";
		StringBuilder _insert = new StringBuilder("insert into "
				+ tabela.NomeTabela() + " ( ");
		StringBuilder _parametros = new StringBuilder(" values ( ");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (_col.TipoPK() != TipoPKColuna.IDENTITY) {
				_insert.append(_separador + _col.NomeColuna());
				if (_col.TipoPK() != TipoPKColuna.SEQUENCE)
					_parametros.append(_separador + ":" + _col.NomeColuna());
				else
					_parametros.append(_separador + _col.Sequence()
							+ ".NEXTVAL ");

				_separador = ",";
			}

		}

		return _insert.toString() + ")" + _parametros.toString() + ")";
	}

	public static String getPrimarySQLPadraoXML(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		StringBuilder _sqls = new StringBuilder();

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (_col.TipoPK() == TipoPKColuna.IDENTITY)
				_sqls.append("select @@IDENTITY  as " + _col.NomeColuna());

			if (_col.TipoPK() == TipoPKColuna.SEQUENCE)
				_sqls.append("select " + _col.Sequence() + ".CURVAL as "
						+ _col.NomeColuna() + " FROM DUAL ");

		}

		return _sqls.toString();
	}

	public static String getPrimarySQLPadrao(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		StringBuilder _sqls = new StringBuilder();

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (_col.TipoPK() == TipoPKColuna.IDENTITY)
				_sqls.append("select @@IDENTITY as " + _col.NomeColuna());

			if (_col.TipoPK() == TipoPKColuna.SEQUENCE)
				_sqls.append("select " + _col.Sequence() + ".CURVAL as "
						+ _col.NomeColuna() + " FROM DUAL ");

		}

		return _sqls.toString();
	}

	public static String getDeleteSQLPadraoXML(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = "where ";
		StringBuilder _insert = new StringBuilder("delete from "
				+ tabela.NomeTabela() + "  ");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (_col.IsPK()) {
				_insert.append(_separador + "<fwp name=\"" + _col.NomeColuna()
						+ "\">" + _col.NomeColuna() + " = " + ":"
						+ _col.NomeColuna() + "</fwp>  ");
				_separador = " and ";
			}

		}

		return _insert.toString();
	}

	public static String getDeleteSQLPadrao(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = "where ";
		StringBuilder _insert = new StringBuilder("Delete from "
				+ tabela.NomeTabela() + "  ");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (_col.IsPK()) {
				_insert.append(_separador + _col.NomeColuna() + " = " + ":"
						+ _col.NomeColuna());
				_separador = " and ";
			}

		}

		return _insert.toString();
	}

	public static String getUpdateSQLPadraoXML(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = "set ";
		String _separadorWhere = "where ";
		StringBuilder _insert = new StringBuilder("update   "
				+ tabela.NomeTabela() + "  ");
		StringBuilder _where = new StringBuilder("");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (!_col.IsPK()) {
				_insert.append(_separador + "<fwp name=\"" + _col.NomeColuna()
						+ "\">" + _col.NomeColuna() + " = :"
						+ _col.NomeColuna() + "</fwp>");
				_separador = ", ";
			} else {
				_where.append(_separadorWhere + "<fwp name=\""
						+ _col.NomeColuna() + "\">" + _col.NomeColuna()
						+ " = :" + _col.NomeColuna() + "</fwp>");
				_separadorWhere = " and ";
			}

		}

		return _insert.toString() + " " + _where.toString() + " ";
	}

	public static String getUpdateSQLPadrao(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = "set ";
		String _separadorWhere = "where ";
		StringBuilder _insert = new StringBuilder("update from  "
				+ tabela.NomeTabela() + "  ");
		StringBuilder _where = new StringBuilder("");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			if (!_col.IsPK()) {
				_insert.append(_separador + _col.NomeColuna() + ":"
						+ _col.NomeColuna());
				_separador = ", ";
			} else {
				_where.append(_separadorWhere + _col.NomeColuna() + "= :"
						+ _col.NomeColuna());
				_separadorWhere = " and ";
			}

		}

		return _insert.toString() + " " + _where.toString() + " ";
	}

	public static String getSelectSQLPadraoXML(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = " ";
		String _separadorWhere = "where ";
		StringBuilder _insert = new StringBuilder("SELECT  ");
		StringBuilder _where = new StringBuilder("");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			_insert.append(_separador + _col.NomeColuna());
			_separador = ", ";

			if (_col.IsPK()) {
				_where.append(_separadorWhere + "<fwp name=\""
						+ _col.NomeColuna() + "\">" + _col.NomeColuna()
						+ " = :" + _col.NomeColuna() + "</fwp>");
				_separadorWhere = " and ";
			}

		}

		return _insert.toString() + " FROM " + tabela.NomeTabela() + " "
				+ _where.toString() + " ";
	}

	public static String getSelectGeralSQLPadraoXML(Class<?> clazz) {
		Tabela tabela = getTabelaModel(clazz);
		return "SELECT * FROM " + tabela.NomeTabela() + " ";
	}

	public static String getSelectSQLPadrao(Class<?> clazz) {
		HashMap<String, Coluna> _colunas = getColunasModel(clazz);
		Tabela tabela = getTabelaModel(clazz);

		String _separador = " ";
		String _separadorWhere = "where ";
		StringBuilder _insert = new StringBuilder("SELECT  ");
		StringBuilder _where = new StringBuilder("");

		for (Coluna _col : new HashSet<Coluna>(_colunas.values())) {

			_insert.append(_separador + _col.NomeColuna());
			_separador = ", ";

			if (_col.IsPK()) {
				_where.append(_separadorWhere + _col.NomeColuna() + "= :"
						+ _col.NomeColuna());
				_separadorWhere = " and ";
			}

		}

		return _insert.toString() + " FROM " + tabela.NomeTabela() + " "
				+ _where.toString() + " ";
	}

	/*
	 * XML Relatório
	 */

	public static Integer retornaTamanho(String valor) {
		Integer tamanho_campo = 0;
		while (!valor.substring(tamanho_campo, (tamanho_campo + 1)).equals(";")) {

			tamanho_campo++;

		}
		return tamanho_campo;
	}

	public static String retornaXMLConsulta(String tabela, String chave,
			String valor) {
		Integer _reg_qtd = 0;
		String Body = "";
		String Footer = "";
		String Retorno = "";
		String Head = "";

		if (valor.length() == 0) {

			Head += "<" + tabela + "><reg_qtd>0</reg_qtd> ";
			Footer += "</" + tabela + ">";
			Retorno = Head + Body + Footer;
			return Retorno;

		}

		Integer tamanho = retornaTamanho(valor);

		for (int i = 0; i < valor.length(); i = i + (tamanho + 1)) {
			Body += "<" + chave + ">" + valor.substring(i, i + tamanho) + "</"
					+ chave + ">";
			_reg_qtd++;
		}
		Head = "<" + tabela + "><reg_qtd>" + _reg_qtd + "</reg_qtd> ";

		Footer += "</" + tabela + ">";

		Retorno = Head + Body + Footer;
		return Retorno;
	}

	public static String retornaXMLConsultaVariado(String tabela, String chave,
			String valor) {
		Integer _reg_qtd = 0;
		String Body = "";
		String Footer = "";
		String Retorno = "";
		String Head = "";

		if (valor.length() == 0) {

			Head += "<" + tabela + "><reg_qtd>0</reg_qtd> ";
			Footer += "</" + tabela + ">";
			Retorno = Head + Body + Footer;
			return Retorno;

		}

		Integer tamanho = retornaTamanho(valor);

		while (valor.length() > 0) {
			Body += "<" + chave + ">" + valor.substring(0, tamanho) + "</"
					+ chave + ">";
			_reg_qtd++;
			valor = valor.substring(tamanho + 1, valor.length());
			if (valor.length() > 0) {
				tamanho = retornaTamanho(valor);
			}
		}
		Head = "<" + tabela + "><reg_qtd>" + _reg_qtd + "</reg_qtd> ";

		Footer += "</" + tabela + ">";

		Retorno = Head + Body + Footer;
		return Retorno;
	}

	public static String addPadrao(String _xml) {
			return "<?xml version='1.0' encoding='UTF-8'?><registro>" + _xml
					+ "</registro>";
	}

	public static String retornaDescricao(HttpServletRequest request, String tabela, String chave,
			String valor, String descricao) throws SQLException, Exception {
		if (valor.length() == 0) {
			return "Todos(as)";
		}
		String _sql = "";
		String _valor = "";
		String _descricao = "";
		ResultSet rs = null;

		_valor = valor.replace(";", "','");
		_valor = _valor.substring(0, (_valor.length() - 3));

		_sql = "select " + descricao + " from " + tabela + " where " + chave
				+ " in ( '" + _valor + "')";

		GenericBusiness<Object> business = new GenericBusiness<Object>();
		rs = business.consultarQueryString(request, _sql);

		while (rs.next()) {
			_descricao += rs.getString(descricao) + " ";
		}
		return _descricao;
	}

	public static String retornaSequence(HttpServletRequest request, String _sequence) throws Exception {
		String _sequenceValor = "";
		String _sql = "";

		ResultSet rs = null;

		GenericBusiness<Object> business = new GenericBusiness<Object>();
		_sql = "Select " + _sequence + " as chave from dual";

		rs = business.consultarQueryString(request, _sql);

		while (rs.next()) {

			_sequenceValor = rs.getString("chave");
		}

		return _sequenceValor;

	}

	/*
	 * public static String retornaParametro(String id) throws Exception {
	 * String _sql = "";
	 * 
	 * ResultSet rs = null;
	 * 
	 * GenericBusiness<Object> business = new GenericBusiness<Object>(); _sql =
	 * "select pm_valor from ger_pm where pm_id = 'PM_BD_CNT'";
	 * 
	 * rs = business.consultarQueryString(_sql);
	 * 
	 * while (rs.next()) {
	 * 
	 * _sequenceValor = rs.getString("chave"); }
	 * 
	 * return _sequenceValor;
	 * 
	 * }
	 */

	public static String retornaData(HttpServletRequest request) throws Exception {
		GenericBusiness<Object> business = new GenericBusiness<Object>();
		ResultSet rs = null;
		String data = null;
		rs = business
				.consultarQueryString(request, "select to_char (sysdate,'dd/mm/yyyy') data, to_char(sysdate,'hh:mi') hora from dual");
		while (rs.next()) {
			data = rs.getString("data");
		}
		return data;
	}

	public static String retornaHora(HttpServletRequest request) throws Exception {
		GenericBusiness<Object> business = new GenericBusiness<Object>();
		ResultSet rs = null;
		String hora = null;
		rs = business
				.consultarQueryString(request,"select to_char (sysdate,'dd/mm/yyyy') data, to_char(sysdate,'hh:mi') hora from dual");
		while (rs.next()) {
			hora = rs.getString("hora");
		}
		return hora;
	}

	public static List<ModelSelect> ListToModelSelect(List<Object> lista) {

		List<ModelSelect> listaretorno = new ArrayList<ModelSelect>();

		for (int i = 0; i < lista.size(); i++) {
			listaretorno.add((ModelSelect) lista.get(i));
		}

		return listaretorno;

	}

	public static String trataErroSql(String msg) {
		int x = 0, y = 0;

		for (int i = 0; i < msg.length(); i++) {
			if (msg.substring(i, i + 10).equals("ORA-20001:")) {
				x = i + 10;
				for (int j = x; j < msg.length(); j++) {
					if (msg.substring(j, j + 4).equals("ORA-")) {
						y = j;
						j = msg.length();
						i = msg.length();
					}
				}
			}
		}
		return msg.substring(x, y);
	}

	public static List<String> split(String valor, String separador) {
		int i = 0;
		List<String> list = new ArrayList<String>();

		while (valor.length() > 0) {
			if (valor.substring(i, i + 1).equals(separador)) {
				list.add(valor.substring(0, i));
				valor = valor.substring(i + 1, valor.length());
			}
			i++;
		}
		return list;
	}

	public static String fomataData(Date data) {
		if (data != null) {
			SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
			return formatData.format(data);
		}
		return "";
	}

	public static void execute(HttpServletRequest request,
			HttpServletResponse response, ResultSet rs,
			Map<String, Object> parametro, String path) {
		JRDataSource jrds = new JRResultSetDataSource(rs);
		File reportFile = new File(path);
		try {

			jrds = new JRResultSetDataSource(rs);
			reportFile = new File(path);
			byte[] bytes = null;
			try {
				bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),
						parametro, jrds);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			if (bytes != null && bytes.length > 0) {

				OutputStream out = response.getOutputStream();
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);

				out.write(bytes, 0, bytes.length);
				out.flush();
				out.close();
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jrds != null) {
				jrds = null;
			}
			if (reportFile != null) {
				reportFile = null;
			}
		}
	}

	public static String MarcaraCpfCnpj_Cep(String valor) {
		if (valor.length() == 11) {
			valor = valor.substring(0, 3) + "." + valor.substring(3, 6) + "."
					+ valor.substring(6, 9) + "-" + valor.substring(9, 11);
		} else if (valor.length() == 14) {
			valor = valor.substring(0, 2) + "." + valor.substring(2, 5) + "."
					+ valor.substring(5, 8) + "/" + valor.substring(8, 10)
					+ valor.substring(10, 12) + "-" + valor.substring(12, 14);
		} else if (valor.length() == 9) {
			valor = valor.substring(0, 2) + "." + valor.substring(2, 5) + "-"
					+ valor.substring(5, 8);
		} else {
			return valor;
		}
		return valor;

	}
	
	
	public static int geraxlsADN(ResultSet rs, String PlanilhaSaida, HttpServletRequest request, HttpServletResponse response)
			throws InvalidFormatException, FileNotFoundException, IOException {

		//FileOutputStream fop = null;
		//File file;
		int qtd_linhas = 0;
		int numColumns = 0;

		ResultSetMetaData rsmd;
		StringBuilder lescrita = new StringBuilder();
		Object campo = null;
		

		try {
			rsmd = rs.getMetaData();
			numColumns = rsmd.getColumnCount();
			//file = new File(this.getClass().getResource("/../../geraarquivo/").getPath() + PlanilhaSaida);// );
			/*fop = new FileOutputStream(file);*/

			// if file doesnt exists, then create it
			/*if (!file.exists()) {
				file.createNewFile();
			}*/

			// get the content in bytes
			//byte[] contentInBytes = "content".getBytes();

			/****/
			lescrita = new StringBuilder();
			lescrita.append("<?xml version='1.0' encoding='ISO-8859-1'?><registros>");
			/*fop.write(lescrita.toString().getBytes());*/
			while (rs.next()) {
				qtd_linhas++;
				
				
				lescrita.append("<registro>");
				for (int coluna = 1; coluna <= numColumns; coluna++) {
					try {
						lescrita.append("<");
						lescrita.append(rsmd.getColumnName(coluna));
						lescrita.append(">");

						campo = rs.getObject(coluna);


						if (campo != null) {
							if (campo.toString().contains("<") || campo.toString().contains("<")){
								lescrita.append("<![CDATA[" + campo + "]]>");
							}else if (campo  instanceof java.sql.Timestamp){
								SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
								lescrita.append(format.format(campo));
							}else{
								lescrita.append(campo);
							}
							
						} else {
							lescrita.append("");
						}
						lescrita.append("</");
						lescrita.append(rsmd.getColumnName(coluna));
						lescrita.append(">");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				lescrita.append("</registro>");
				/*fop.write(lescrita.toString().getBytes());
				fop.flush();*/


			}
			lescrita.append("</registros>");
			/*fop.write(lescrita.toString().getBytes());
			fop.flush();
			fop.close();*/
			byte[] bytes = null;
			try {
				bytes = lescrita.toString().getBytes();
				lescrita = new StringBuilder();
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
			if (bytes != null && bytes.length > 0) {

				OutputStream out = response.getOutputStream();
				//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setContentType("application/vnd.ms-excel");
				response.setContentLength(bytes.length);

				out.write(bytes, 0, bytes.length);
				out.flush();
				out.close();
			}
			//fop.write(contentInBytes);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			
			rsmd = null;
			//file = null;
			//fop = null;
			campo = null;
			lescrita = null;
			if (rs != null) {
			      try {
			          rs.close();
			      } catch (SQLException sqe) {
			          logger.debug(sqe.getMessage());
			      } catch (RuntimeException re) {
			          logger.debug(re.getMessage());
			      }
			   }
		}
		
		return qtd_linhas;
	}
	
}
