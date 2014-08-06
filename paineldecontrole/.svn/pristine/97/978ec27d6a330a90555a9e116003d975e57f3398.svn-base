/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 
 * @author santos
 */
public class MontaQuery<T> {

	private static Logger logger = Logger.getLogger(MontaQuery.class.getName());

	private Connection conexao = null;
	private boolean isPadrao = false;

	private List<LnQuery> listaQuery = new ArrayList<LnQuery>();
	private HashMap<String, Object> listaParametros = new HashMap<String, Object>();
	private NamedParameterStatement pstmt = null;

	public String getQuery(T objeto) throws Exception {

		
		logger.debug("getQuery(T objeto)");
		HashMap<String, Coluna> colunasmodel = Auxiliar.getColunasModel(objeto
				.getClass());

		String sql = "";
		listaParametros = new HashMap<String, Object>();
		Method m = null;
		Object valor = null;
		String excludenull = "N";
		String nmparametro = "";
		for (int i = 0; i < listaQuery.size(); i++) {
			LnQuery ltemp = listaQuery.get(i);
			nmparametro = ltemp.getParametro();
			excludenull = ltemp.getExcludenull();
			if (excludenull.equals("S")) {
				try {

					if (colunasmodel.containsKey(nmparametro)) {
						m = objeto.getClass().getMethod(
								colunasmodel.get(nmparametro).MetodoGet());
						valor = m.invoke(objeto, null);
					} else {
						valor = ((EntityBase) objeto).colunaJoin(nmparametro);
					}
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					logger.debug("getQuery(T objeto) - SecurityException"+ e.getMessage());
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					logger.debug("getQuery(T objeto) - NoSuchMethodException"+ e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					logger.debug("getQuery(T objeto) - IllegalArgumentException"+ e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					logger.debug("getQuery(T objeto) - IllegalAccessException"+ e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					logger.debug("getQuery(T objeto) - InvocationTargetException "+ e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e){
					logger.debug("getQuery(T objeto) - Exception "+ e.getMessage());
					e.printStackTrace();
				}

			}

			if ((excludenull.equals("N"))
					|| (excludenull.equals("S") && (valor != null))) {
				String Linha = ltemp.getSql();
				if (ltemp.isParametroBanco()) {
					listaParametros.put(ltemp.getParametro(), null);
				}
				sql += " " + Linha;
			}
		}
		logger.debug(sql);
		System.out.println(sql);
		return sql;
	}

	public String getQuery() {
		logger.debug("getQuery()");
		String sql = "";
		listaParametros = new HashMap<String, Object>();

		for (int i = 0; i < listaQuery.size(); i++) {
			LnQuery ltemp = listaQuery.get(i);

			String Linha = ltemp.getSql();
			if (ltemp.isParametroBanco()) {
				listaParametros.put(ltemp.getParametro(), null);
			}
			sql += " " + Linha;
		}
		logger.debug(sql);
		return sql;
	}

	public List<LnQuery> getListaQuery() {
		return listaQuery;
	}

	public void setListaQuery(List<LnQuery> listaQuery) {
		this.listaQuery = listaQuery;
	}

	public void setParametrosModel(T objeto) throws Exception,
			InvocationTargetException {

		HashMap<String, Coluna> colunasmodel = Auxiliar.getColunasModel(objeto
				.getClass());
		logger.debug("setParametrosModel - Passo 1");
		for (Map.Entry<String, Object> entry : listaParametros.entrySet()) {
			String key = entry.getKey();

			Method m = null;

			if (colunasmodel.containsKey(key)) {
				m = objeto.getClass().getMethod(
						colunasmodel.get(key).MetodoGet());
				listaParametros.put(key, m.invoke(objeto, null));
			} else {
				listaParametros.put(key, ((EntityBase) objeto).colunaJoin(key));
			}
		}
	}

	public void getPreparedStatement() throws Exception {

		pstmt = new NamedParameterStatement(this.getConexao(), this.getQuery());

	}
	
	public void getPreparedStatement(T objeto) throws Exception {

		pstmt = new NamedParameterStatement(this.getConexao(), this.getQuery(objeto));

	}


	public BigDecimal ExecuteInsert(T objeto) throws Exception {

		BigDecimal retorno = null;
		this.setParametrosModel(objeto);

		for (Map.Entry<String, Object> entry : listaParametros.entrySet()) {
			String key = entry.getKey();
			Object valor = "";
			if (entry.getValue() != null){
				valor = entry.getValue();
			}
			pstmt.setObject(key, valor);

		}

		pstmt.executeUpdate();

		String pk = Auxiliar.getPrimarySQLPadrao(objeto.getClass());
		if (!pk.equals("")) {
			NamedParameterStatement pstmtidentity = new NamedParameterStatement(
					this.getConexao(), pk);
			ResultSet dados = pstmtidentity.executeQuery();
			while (dados.next()) {
				retorno = (BigDecimal) dados.getObject(1);
			}
		}
		return retorno;
	}

	public void ExecuteNonQuery(T objeto) throws Exception {

		if (pstmt == null) {
			pstmt = new NamedParameterStatement(this.getConexao(),
					this.getQuery(objeto));

		}

		this.setParametrosModel(objeto);

		for (Map.Entry<String, Object> entry : listaParametros.entrySet()) {
			String key = entry.getKey();
			Object valor = "";
			if (entry.getValue() != null){
				valor = entry.getValue();
			}
			pstmt.setObject(key, valor);

		}

		pstmt.executeUpdate();
	}

	public Object ExecuteScalar(T objeto) {

		Object retorno = null;
		try {

			this.setParametrosModel(objeto);

			for (Map.Entry<String, Object> entry : listaParametros.entrySet()) {
				String key = entry.getKey();
				Object valor = entry.getValue();
				pstmt.setObject(key, valor);

			}

			ResultSet dados = pstmt.executeQuery();
			while (dados.next()) {
				retorno = dados.getObject(1);

			}

		} catch (Exception e) {
			logger.debug("ExecuteScalar " + e.getMessage());
			e.printStackTrace();

		}

		return retorno;
	}

	public List<T> ExecuteStatement(T objeto) throws Exception {
		logger.debug("ExecuteStatement - Passo 1");
		List<T> lista = new ArrayList<T>();
		
		try {
			if (pstmt == null) {
				pstmt = new NamedParameterStatement(this.getConexao(),
						this.getQuery(objeto));
		
			}
		
		} catch (Exception e) {
			logger.error("NamedParameterStatement - "+ e.getMessage());
			throw new Exception(e);
		}
			
		this.setParametrosModel(objeto);
		logger.debug("ExecuteStatement - Passo 2");
		for (Map.Entry<String, Object> entry : listaParametros.entrySet()) {
			String key = entry.getKey();
			Object valor = entry.getValue();
			pstmt.setObject(key, valor); // AQUI

		}

		ResultSet rs = pstmt.executeQuery();
		lista = new ConvertFW<T>().ConvertResultSetToList(
				(Class<T>) objeto.getClass(), rs);
		return lista;
	}

	public ResultSet ExecuteStatementResultSet(T objeto) throws Exception {

		List<T> lista = new ArrayList<T>();

		if (pstmt == null) {
			pstmt = new NamedParameterStatement(this.getConexao(),
					this.getQuery(objeto));

		}

		this.setParametrosModel(objeto);

		for (Map.Entry<String, Object> entry : listaParametros.entrySet()) {
			String key = entry.getKey();
			Object valor = entry.getValue();
			pstmt.setObject(key, valor); // AQUI

		}

		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	public MontaQuery(HttpServletRequest request, Class<T> c, String query, boolean ispadrao,
			Connection _conec) throws Exception {
		this.isPadrao = ispadrao;
		setConexao(_conec);
		Monta(request, c, query, ispadrao);
	}

	public MontaQuery(HttpServletRequest request, Class<T> c, String query, Connection _conec)
			throws Exception {
		this.isPadrao = false;
		Monta(request, c, query, false);
	}

	public void Monta(HttpServletRequest request, Class<T> c, String query, boolean ispadrao)
			throws Exception {

		listaQuery = new ArrayList<LnQuery>();
		listaParametros = new HashMap<String, Object>();

		pstmt = null;

		T objetoTeste = (T) c.newInstance();
		String lquery = "";
		if (!ispadrao) {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder
							.parse(this.getClass().getResource("/../../SQLXML/" + objetoTeste.getClass().getSimpleName() + ".xml").getPath());
						
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile("//query[@name='" + query
					+ "']");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;

			lquery = nodes.item(0).getTextContent().trim();
		} else {

			if (query.toLowerCase().equals("insert"))
				lquery = Auxiliar.getInsertSQLPadraoXML(c);
			else if (query.toLowerCase().equals("update"))
				lquery = Auxiliar.getUpdateSQLPadraoXML(c);
			else if (query.toLowerCase().equals("delete"))
				lquery = Auxiliar.getDeleteSQLPadraoXML(c);
			else if (query.toLowerCase().equals("select"))
				lquery = Auxiliar.getSelectSQLPadraoXML(c);
			else if (query.toLowerCase().equals("selectgeral"))
				lquery = Auxiliar.getSelectGeralSQLPadraoXML(c);
			else if (query.toLowerCase().equals("primary"))
				lquery = Auxiliar.getPrimarySQLPadraoXML(c);

		}

		lquery = lquery.replaceAll("\\<fwp", "#I@<fwp");
		lquery = lquery.replaceAll("\\</fwp>", "</fwp>#F@");

		String[] conjunto = lquery.split("\\#I@");

		for (int i = 0; i < conjunto.length; i++) {

			if (!conjunto[i].trim().equals("")) {
				String[] linhatratada = conjunto[i].trim().split("\\#F@");
				listaQuery.add(new LnQuery(linhatratada[0]));
				if (linhatratada.length > 1)
					listaQuery.add(new LnQuery(linhatratada[1]));
			}

		}
	}

	/**
	 * @return the conexao
	 */
	public Connection getConexao() {
		return conexao;
	}

	/**
	 * @param conexao
	 *            the conexao to set
	 */
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
}
