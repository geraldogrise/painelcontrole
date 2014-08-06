package br.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.util.MontaQuery;

/**
 * Classe GenericDAO
 * Essa classe cont�m  os m�todos gen�ricos para separar 
 * as regras de neg�cio das regras de acesso a banco de dados
 *
 * @author santos
 */

public class GenericDAO<T> {

	private static Logger logger = Logger.getLogger( GenericDAO.class.getName());
	/**
    *  M�todo save
    *  <br>
    *  M�todo gen�rico para executar a query insert.
    *
    * @param classe Object objeto a ser salvo
    * @param con Connection conex�o do banco de dados
    * @return um BigDecimal
    */
	public BigDecimal save(HttpServletRequest request,Object classe, Connection con) throws Exception {

		
		MontaQuery mq = null;
		BigDecimal lpxxx = null;
		try {
			mq = new MontaQuery<Class<T>>(request, (Class<Class<T>>) classe.getClass(), "insert", true, con);
			mq.getPreparedStatement(classe);
			lpxxx = mq.ExecuteInsert(classe);
		} catch (Exception e) {
			logger.debug("Save "+ e.getMessage());
			throw new Exception(e);
		}
		return lpxxx;
		
		
	}
	
	/**
    *  M�todo save
    *  <br>
    *  M�todo gen�rico para executar a query insert.
    *
    * @param classe Object objeto a ser salvo
    * @param con Connection conex�o do banco de dados
    * @param nmsql String nome da query
    * @return um BigDecimal
    */
	public BigDecimal save(HttpServletRequest request,Object classe, Connection con, String nmsql) throws Exception {
		MontaQuery mq = null;
		BigDecimal lpxxx = null;
		try {
			mq = new MontaQuery<Class<T>>(request, (Class<Class<T>>) classe.getClass(), nmsql, false, con);
			mq.getPreparedStatement(classe);
			lpxxx = mq.ExecuteInsert(classe);
		} catch (Exception e) {
			logger.debug("Save "+ e.getMessage());
			throw new Exception(e);
		}
		return lpxxx;
	}
	
	/**
    *  M�todo update
    *  <br>
    *  M�todo gen�rico para executar um update
    *
    * @param classe Object objeto a ser alterado
    * @param con Connection conex�o do banco de dados
    * @return um Object 
    */
	public Object update(HttpServletRequest request,Object classe, Connection con) throws Exception {
		MontaQuery mq = null;
		mq = new MontaQuery<Class<T>>(request,(Class<Class<T>>) classe.getClass(), "update", true, con);
		mq.getPreparedStatement(classe);
		mq.ExecuteNonQuery(classe);
		return classe;

	}

	/**
    *  M�todo select
    *  <br>
    *  M�todo gen�rico para executar um select
    *
    * @param classe Object objeto a ser consultado
    * @param con Connection conex�o do banco de dados
    * @return uma List do tipo de objeto consultado 
    */
	public List<Class<T>> select(HttpServletRequest request,Object classe, Connection con) throws Exception {
		logger.debug("select 1 ");
		MontaQuery mq = null;
		mq = new MontaQuery<Class<T>>(request, (Class<Class<T>>) classe.getClass(), "select", true, con);
		logger.debug("select 1.1 ");
		mq.getPreparedStatement(classe);
		logger.debug("select 1.2 ");
		List<Class<T>> lista = mq.ExecuteStatement(classe);
		logger.debug("select 1.3 ");

		return lista;

	}

	/**
    *  M�todo selectGeral
    *  <br>
    *  M�todo gen�rico para executar um select geral
    *
    * @param classe Object objeto a ser consultado
    * @param con Connection conex�o do banco de dados
    * @return uma List do tipo de objeto consultado 
    */
	public List<Class<T>> selectGeral(HttpServletRequest request,Object classe, Connection con) throws Exception {
		MontaQuery mq = null;
		mq = new MontaQuery<Class<T>>(request, (Class<Class<T>>) classe.getClass(), "selectgeral", true, con);
		mq.getPreparedStatement(classe);
		List<Class<T>> lista = mq.ExecuteStatement(classe);

		return lista;

	}
	
	/**
    *  M�todo selectXML
    *  <br>
    *  M�todo gen�rico para executar um select xml
    *
    * @param classe Object objeto a ser consultado
    * @param con Connection conex�o do banco de dados
    * @param nome String nome da query
    * @return uma List do tipo de objeto consultado 
    */
	public List<Class<T>> selectXML(HttpServletRequest request, Object classe, Connection con, String nome) throws Exception {
		MontaQuery mq = null;
		mq = new MontaQuery<Class<T>>(request, (Class<Class<T>>) classe.getClass(), nome, false, con);
		//mq.getPreparedStatement();
		List<Class<T>> lista = mq.ExecuteStatement(classe);
		
		return lista;

	}

	/**
    *  M�todo selectXMLResultSet
    *  <br>
    *  M�todo gen�rico para executar um select xml com um resultset de retorno
    *
    * @param classe Object objeto a ser consultado
    * @param con Connection conex�o do banco de dados
    * @param nome String nome da query
    * @return um ResultSet da consulta
    */
	public ResultSet selectXMLResultSet(HttpServletRequest request, Object classe, Connection con, String nome) throws Exception {
		MontaQuery mq = null;
		mq = new MontaQuery<Class<T>>(request,(Class<Class<T>>) classe.getClass(), nome, false, con);
		//mq.getPreparedStatement();
		ResultSet lista = mq.ExecuteStatementResultSet(classe);

		return lista;	

	}	
	
	/**
    *  M�todo delete
    *  <br>
    *  M�todo gen�rico para executar um delete
    *
    * @param classe Object objeto a ser excluido
    * @param con Connection conex�o do banco de dados
    */
	public void delete(HttpServletRequest request,Object classe, Connection con) throws Exception {
		MontaQuery mq = null;
		mq = new MontaQuery<Class<T>>(request,(Class<Class<T>>) classe.getClass(), "delete", true, con);
		mq.getPreparedStatement(classe);
		mq.ExecuteNonQuery(classe);
	}	
	
	/**
    *  M�todo deleteXML
    *  <br>
    *  M�todo gen�rico para executar um delete a partir do xml
    *
    * @param classe Object objeto a ser excluido
    * @param con Connection conex�o do banco de dados
    * @param nome String nome da query
    */
	public void deleteXML(HttpServletRequest request,Object classe, Connection con, String nome) throws Exception {
		MontaQuery mq = null;
		mq = new MontaQuery<Class<T>>(request, (Class<Class<T>>) classe.getClass(), nome, false, con);
		//mq.getPreparedStatement();
		mq.ExecuteNonQuery(classe);
	}	
	
	/**
    *  M�todo executeQueryString
    *  <br>
    *  M�todo gen�rico para executar uma query
    * 
    * @param con Connection conex�o do banco de dados
    * @param sql String nome da query
    * @return um ResultSet da query
    */
	public ResultSet executeQueryString(Connection con, String sql) throws Exception {
    	Statement  stm = con.createStatement(); 
		ResultSet rs = null;
		rs = stm.executeQuery(sql);
		return rs;
	}	
	
	/**
    *  M�todo queryXml
    *  <br>
    *  M�todo gen�rico para executar uma query
    *
    * @param classe Object objeto
    * @param con Connection conex�o do banco de dados
    * @param nmsql String nome da query
    */
	public void queryXml(HttpServletRequest request,Object classe, Connection con, String nmsql) throws Exception {
		MontaQuery mq = null;
		try {
			mq = new MontaQuery<Class<T>>(request, (Class<Class<T>>) classe.getClass(), nmsql, false, con);
			mq.getPreparedStatement(classe);
			mq.ExecuteNonQuery(classe);
		} catch (Exception e) {
			logger.debug("queryXml "+ e.getMessage());
			throw new Exception(e);
		}
	}
	
}
