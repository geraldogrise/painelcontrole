package br.com.util;

public class JSonResult {

	private boolean isErro;
	private String msg_erro;
	private Object dados;

	public boolean isErro() {
		return isErro;
	}

	public void setErro(boolean isErro) {
		this.isErro = isErro;
	}

	public String getMsg_erro() {
		return msg_erro;
	}

	public void setMsg_erro(String msg_erro) {
		this.msg_erro = msg_erro;
	}

	public Object getDados() {
		return dados;
	}

	public void setDados(Object dados) {
		this.dados = dados;
	}

}
