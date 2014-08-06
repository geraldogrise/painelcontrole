/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

public class Campos_Join {

	public String chave;
	public Object valor;
//	public DBType tipoDado;

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

/*	public DBType getTipoDado() {
		return tipoDado;
	}

	public void setTipoDado(DBType tipoDado) {
		this.tipoDado = tipoDado;
	}*/

	public Campos_Join(String pChave, Object pValor) {
		setChave(pChave);
		setValor(pValor);
	}

	public Campos_Join() {
	}

}
