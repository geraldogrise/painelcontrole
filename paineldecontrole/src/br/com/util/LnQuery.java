/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

/**
 *
 * @author santos
 */
public class LnQuery {

	
	private String sql = "";
	private String sqlorig = "";
	private String parametro = "";
	private String excludenull = "N";
	private boolean isParametroBanco = false;
	

	public boolean isParametroBanco() {
		return isParametroBanco;
	}



	public void setParametroBanco(boolean isParametroBanco) {
		this.isParametroBanco = isParametroBanco;
	}



	public String getSql() {
		return sql;
	}
	

	
	public LnQuery( String sqlorig ) 
	{
	  
		this.sqlorig = sqlorig;
		int qtd    =  this.sqlorig.length();
		int posicaotag =  this.sqlorig.indexOf("<fwp");
		int fim    =  this.sqlorig.indexOf('>', qtd-1 );
		int inicio =  -1;
		
		if ( posicaotag >= 0 ) 
		{ 
			inicio =  this.sqlorig.indexOf(">", posicaotag );
			String textoparameter = this.sqlorig.substring(posicaotag+4, inicio).trim();
			
			this.setParametro(textoparameter.substring(textoparameter.indexOf("name=") +6  , textoparameter.indexOf("\"", 6)));
			
			this.setParametroBanco(( this.sqlorig.indexOf(":"+this.getParametro()) >= 0 ));
						
			int inicioExclude = textoparameter.indexOf("excludenull=");
			if ( inicioExclude  >= 0 ) {
				this.excludenull = textoparameter.substring(inicioExclude +13, textoparameter.indexOf("\"", inicioExclude+13) );
			}
		}
		
		if ( inicio >= 0  ) {
		   this.sql = this.sqlorig.substring(inicio+1, fim-5);
		} else {
			this.sql = this.sqlorig;
		}
		
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getSqlorig() {
		return sqlorig;
	}
	public void setSqlorig(String sqlorig) {
		this.sqlorig = sqlorig;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getExcludenull() {
		return excludenull;
	}
	public void setExcludenull(String excludenull) {
		this.excludenull = excludenull;
	}
	
	
	
}
