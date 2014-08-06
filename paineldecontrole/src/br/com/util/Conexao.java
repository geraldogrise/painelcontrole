/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

/**
 *
 * @author santos
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class Conexao {

	private static Logger logger = Logger.getLogger(Conexao.class.getName());
	private static String ip;
	private static String porta;
	private static String sid;
	private static String user;
	private static String pass;

	private static Connection conn = null;
	private static Connection conn_cg = null;
	private static InitialContext initialContext = null;
	private static Context envContext = null;
	private static DataSource ds = null;

	public static Connection getConnection()
			throws Exception {
		return getConnection("ORACLE", "APP_PCTLOWN", "Oi2014");
	}

	public static Connection getConnectionConsultaGerencial() throws Exception {
		/*
		 * if (ip == null){ retornaDadosConexao(); }
		 */

		// return getConnection("ORACLE", "app_icfusr", "app_icfusr");
		return getConnectionConsultaGerencial("ORACLE", "app_icfusr",
				"zse4xdr5");
	}

	public static void retornaDadosConexao() {
		try {
			String path = Conexao.class.getResource("/../../").getPath();
			path = path.replace("webapps/icf/", "webapps/");
			File dir = new File(path + "WebConfig.xml");

			FileReader reader = new FileReader(dir);
			BufferedReader leitor = new BufferedReader(reader);
			String linha = "";
			while ((linha = leitor.readLine()) != null) {
				if (linha.endsWith("</ip>")) {
					ip = linha.substring(5, linha.length() - 5);
				} else if (linha.endsWith("</porta>")) {
					porta = linha.substring(8, linha.length() - 8);
				} else if (linha.endsWith("</sid>")) {
					sid = linha.substring(6, linha.length() - 6);
				} else if (linha.endsWith("</user>")) {
					user = linha.substring(7, linha.length() - 7);
				} else if (linha.endsWith("</pass>")) {
					pass = linha.substring(7, linha.length() - 7);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(String tpBanco, String usuario, String senha) throws Exception {

		conn = null;
		initialContext = null;
		envContext = null;
		ds = null;

		String base = "jdbc/painelcontrole";
		try {
			if (conn != null) {
				if (conn.isClosed()) {
					conn = null;
				} else {
					conn = null;
					logger.debug("Mantendo conexão");
				}

			}

			Locale.setDefault(new Locale("pt", "BR"));

			if (tpBanco.equals("ORACLE") && conn == null) {
				// logger.debug("Iniciando conexão");
				if (initialContext == null) {
					// System.out.println("CRIA initialContext");
					initialContext = new InitialContext();
				}
				if (envContext == null) {
					// System.out.println("CRIA envContext");
					envContext = (Context) initialContext
							.lookup("java:/comp/env");
				}
				if (ds == null) {
					// System.out.println("CRIA ds");
					ds = (DataSource) envContext.lookup(base);
				}

				conn = ds.getConnection();
				conn.setAutoCommit(false);

				/*
				 * Class.forName("oracle.jdbc.driver.OracleDriver"); conn =
				 * DriverManager.getConnection( getConnectionUrl(tpBanco,
				 * usuario, senha), usuario, senha); conn.setAutoCommit(false);
				 */
			}

			return conn;

		} catch (SQLException e) {
			e.getCause();
			logger.error("getConnection - " + e.getMessage());
			throw new Exception(e);
		}
		// return conn;
	}

	public static Connection getConnection(String tpBanco, String usuario,
			String senha, HttpServletRequest request) throws Exception {

		System.out.println(request.getAttribute("j_base"));

		try {
			if (conn != null) {
				if (conn.isClosed()) {
					conn = null;
				} else {
					conn = null;
					logger.debug("Mantendo conexão");
				}

			}

			Locale.setDefault(new Locale("pt", "BR"));

			if (tpBanco.equals("ORACLE") && conn == null) {
				logger.debug("Iniciando conexão");
				if (initialContext == null) {
					initialContext = new InitialContext();
				}
				if (envContext == null) {
					envContext = (Context) initialContext
							.lookup("java:/comp/env");
				}
				if (ds == null) {
					ds = (DataSource) envContext.lookup("jdbc/myoracle");
				}

				conn = ds.getConnection();
				conn.setAutoCommit(false);

				/*
				 * Class.forName("oracle.jdbc.driver.OracleDriver"); conn =
				 * DriverManager.getConnection( getConnectionUrl(tpBanco,
				 * usuario, senha), usuario, senha); conn.setAutoCommit(false);
				 */
			}

			return conn;

		} catch (SQLException e) {
			e.getCause();
			logger.error("getConnection - " + e.getMessage());
			throw new Exception(e);
		}
		// return conn;
	}

	public static Connection getConnectionConsultaGerencial(String tpBanco,
			String usuario, String senha) throws Exception {
		// java.sql.Connection conn = null;
		try {
			if (conn_cg != null) {
				if (conn_cg.isClosed()) {
					conn_cg = null;
				} else {
					logger.debug("Mantendo conexão");
				}

			}

			Locale.setDefault(new Locale("pt", "BR"));

			if (tpBanco.equals("ORACLE") && conn_cg == null) {
				logger.debug("Iniciando conexão");
				InitialContext initialContext = new InitialContext();
				Context envContext = (Context) initialContext
						.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
				conn_cg = ds.getConnection();
				conn_cg.setAutoCommit(false);

				/*
				 * Class.forName("oracle.jdbc.driver.OracleDriver"); conn =
				 * DriverManager.getConnection( getConnectionUrl(tpBanco,
				 * usuario, senha), usuario, senha); conn.setAutoCommit(false);
				 */
			}

			return conn_cg;

		} catch (SQLException e) {
			e.getCause();
			logger.error("getConnection - " + e.getMessage());
			throw new Exception(e);
		}
		// return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static String getConnectionUrl(String tpbanco, String usuario,
			String senha) {
		String _url = "";
		if (tpbanco.equals("ORACLE")) {
			// _url = "jdbc:oracle:thin:@vmadnsa03:1521:SVA";
			_url = "jdbc:oracle:thin:@(DESCRIPTION =    (ADDRESS = (PROTOCOL = TCP)(HOST = 10.145.1.172)(PORT = 1521))    (ADDRESS = (PROTOCOL = TCP)(HOST = isp-db-cluster.dev.infra)(PORT = 1521))    (ADDRESS = (PROTOCOL = TCP)(HOST = 10.145.1.173)(PORT = 1521))    (LOAD_BALANCE = yes)    (CONNECT_DATA =      (SERVER = DEDICATED)      (SERVICE_NAME = SVA)    )  )";

		}
		logger.debug("getConnectionUrl - " + _url);
		if (tpbanco.equals("MSSQL"))
			_url = "jdbc:sqlserver://localhost:1433;databaseName=igsap";

		if (tpbanco.equals("MYSQL"))
			_url = "jdbc:mysql://localhost/igmysql?user=" + usuario
					+ "&password=" + senha;

		return _url;
	}

}
