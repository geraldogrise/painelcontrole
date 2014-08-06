package br.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.dao.GenericDAO;
import br.com.util.Conexao;
import br.com.util.SVAException;

/**
 * Classe GenericBusiness
 * Essa classe cont�m as regras de neg�cio gen�ricas
 *
 * @author santos
 */

public class GenericBusiness<T> {	
	
	private static Logger logger = Logger.getLogger( GenericBusiness.class.getName());
	
    /**
    *  M�todo salvar
    *  <br>
    *  M�todo gen�rico para salvar um objeto no banco de dados.
    *
    * @param classe Object objeto a ser salvo
    */
	public void salvar(HttpServletRequest request, Object classe) throws Exception {
		Connection con = Conexao.getConnection();
		try {
			GenericDAO dao = new GenericDAO();
			dao.save(request, classe, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
	}
	
	/**
    * M�todo salvarXML
    *  <br>
    * M�todo gen�rico para salvar um objeto no banco de dados 
    * a partir de um nome de query recebido. 
    *
    * @param classe Object objeto a ser salvo
    * @param nomeSQL String nome da query
    */
	public void salvarXML(HttpServletRequest request, Object classe, String nomeSQL) throws Exception {
		Connection con = Conexao.getConnection();
		try {
			GenericDAO dao = new GenericDAO();
			dao.save(request, classe, con, nomeSQL);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
	}
	
	/**
    * M�todo editar
    *  <br>
    * M�todo gen�rico para alterar um objeto no banco de dados  
    *
    * @param classe Object objeto a ser alterado
    */
	public void editar(HttpServletRequest request, Object classe) throws Exception {
		Connection con = Conexao.getConnection();
		try {
			GenericDAO dao = new GenericDAO();
			dao.update(request, classe, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
	}

	/**
    * M�todo excluir
    *  <br>
    * M�todo gen�rico para excluir um objeto no banco de dados 
    *
    * @param classe Object objeto a ser exclu�do
    */
	public void excluir(HttpServletRequest request, Object classe) throws Exception {

		Connection con = Conexao.getConnection();
		try {
			GenericDAO dao = new GenericDAO();
			dao.delete(request, classe, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			if (e.getMessage().contains("ORA-02292")){
				throw new Exception("N�o � poss�vel excluir um registro j� vinculado.", e);
			}else if (e.getMessage().contains("ORA-20100")){
				throw new Exception(SVAException.retornaMsgTratada(e.getMessage()), e);
			}
			throw new Exception(e);
		}

	}
	
	/**
    * M�todo excluirXML
    *  <br>
    * M�todo gen�rico para excluir um objeto no banco de dados
    * a partir de um nome de classe recebida. 
    *
    * @param classe Object objeto a ser exclu�do
    * @param nome String nome da classe para buscar a query no xml
    */
	public void excluirXML(HttpServletRequest request, Object classe, String nome) throws Exception {

		Connection con = Conexao.getConnection();
		try {
			GenericDAO dao = new GenericDAO();
			
			dao.deleteXML(request, classe, con, nome);
			
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			if (e.getMessage().contains("ORA-02292")){
				throw new Exception("N�o � poss�vel excluir um registro j� vinculado.", e);
			}else if (e.getMessage().contains("ORA-20100")){
				throw new Exception(SVAException.retornaMsgTratada(e.getMessage()), e);
			}
			throw new Exception(e);
		}

	}
	
	/**
	* M�todo excluirXML
    *  <br>
    * M�todo gen�rico para excluir um objeto no banco de dados
    * a partir um nome de classe recebida e da classe dependente. 
    *
    * @param classe Object objeto a ser exclu�do
    * @param nome String nome da classe para buscar a query no xml
    * @param nomefilho String nome da classe dependente para buscar a query no xml
    */
	public void excluirXML(HttpServletRequest request, Object classe, String nome, String nomefilho) throws Exception {

		Connection con = Conexao.getConnection();
		try {
			GenericDAO dao = new GenericDAO();
			
			dao.deleteXML(request, classe, con, nomefilho);
			dao.deleteXML(request, classe, con, nome);
			
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
	}
	
	/**
    * M�todo consultarId
    *  <br>
    * M�todo gen�rico para consultar um objeto no banco de dados
    * 
    * @param classe Object objeto
    * @return um Object com seus dados preenchidos
    */
	public Object consultarId(HttpServletRequest request, Object classe) throws Exception {

		Connection con = Conexao.getConnection();
		Object objeto_retorno = null;
		try {
			GenericDAO dao = new GenericDAO();
			objeto_retorno = dao.select(request, classe, con).get(0);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
		return objeto_retorno;

	}

	/**
    * M�todo consultarGeral
    *  <br>
    * M�todo gen�rico para consultar objetos no banco de dados
    *
    * @param classe Object objeto para a consulta
    * @return uma lista de Object com seus dados preenchidos
    */
	public List<Object> consultarGeral(HttpServletRequest request, Object classe) throws Exception {

		Connection con = Conexao.getConnection();
		List<Object> obeto_retorno = null;
		try {
			GenericDAO dao = new GenericDAO();
			obeto_retorno = dao.selectGeral(request, classe, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
		return obeto_retorno;

	}
	
	/**
    * M�todo consultarXML
    *  <br>
    * M�todo gen�rico para consultar um objeto no banco de dados
    * a partir um nome de query
    *
    * @param classe Object objeto para fazer a consulta
    * @param nome   String nome da query a ser executada
    * @return uma  lista de Object com seus dados preenchidos
    */
	
	public List<Object> consultarXML(HttpServletRequest request, Object classe, String nome) throws Exception {
		//logger.debug("Consulta XML - Passo 1");
		Connection con = Conexao.getConnection();
		List<Object> obeto_retorno = null;
		try {
			GenericDAO dao = new GenericDAO();
			obeto_retorno = dao.selectXML(request, classe, con, nome);
			//logger.debug("Consulta XML - Passo 2");
			//con.commit();
			con.close();
		} catch (Exception e) {
			logger.error("Consulta XML - " + e.getMessage());
			//con.rollback();
			con.close();
			throw new Exception(e);
		}
		//logger.debug("Consulta XML - Passo 3");
		return obeto_retorno;
	}
	
	public List<T> consultarXML2(HttpServletRequest request, Object classe, String nome) throws Exception {
		//logger.debug("Consulta XML - Passo 1");
		Connection con = Conexao.getConnection();
		List<T> obeto_retorno = null;
		try {
			GenericDAO dao = new GenericDAO();
			obeto_retorno = dao.selectXML(request, classe, con, nome);
			//logger.debug("Consulta XML - Passo 2");
			//con.commit();
			con.close();
		} catch (Exception e) {
			logger.error("Consulta XML - " + e.getMessage());
			//con.rollback();
			con.close();
			throw new Exception(e);
		}
		//logger.debug("Consulta XML - Passo 3");
		return obeto_retorno;
	}
	
	
	/**
    ** M�todo consultarXMLResultSet
    *  <br>
    * M�todo gen�rico para consultar um objeto no banco de dados
    * a partir um nome de query
    *
    * @param classe Object objeto para fazer a consulta
    * @param nome   String nome da query a ser executada
    * @return um ResultSet da consulta
    */	
	public ResultSet consultarXMLResultSet(HttpServletRequest request,  Object classe, String nome) throws Exception {

		Connection con = Conexao.getConnection();
		ResultSet obeto_retorno = null;
		try {
			GenericDAO dao = new GenericDAO();
			 obeto_retorno = dao.selectXMLResultSet(request, classe, con, nome);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
		return obeto_retorno;

	}

	/**
    ** M�todo consultarQueryString
    *  <br>
    * M�todo gen�rico para realizar uma consulta 
    * no banco de dados a partir da query recebida
    *
    * @param sql String query para fazer a consulta
    * @return um ResultSet da consulta
    */	
	public ResultSet consultarQueryString(HttpServletRequest request,String sql) throws Exception {

		Connection con = Conexao.getConnection();
		ResultSet rs = null;
    	try {
			GenericDAO dao = new GenericDAO();
			rs = dao.executeQueryString(con, sql);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
		return rs;
	}
	
	/**
    ** M�todo executeQueryXml
    *  <br>
    * M�todo gen�rico para executar uma query 
    * no banco de dados a partir da query recebida
    * 
    * @param classe Object objeto 
    * @param nomeSQL String nome da query para executar
    */	
	public void executeQueryXml(HttpServletRequest request,Object classe, String nomeSQL) throws Exception {
		Connection con = Conexao.getConnection();
		try {
			GenericDAO dao = new GenericDAO();
			dao.queryXml(request, classe, con, nomeSQL);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new Exception(e);
		}
	}

}
