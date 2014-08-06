package br.com.util.taglib;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Data extends SimpleTagSupport implements DynamicAttributes {

	private HashMap<String, Object> data = new HashMap<String, Object>();

	private String nome = null;
	private String valor = "";
	private String acao = "";
	private String formulario = "";
	private String imagem = "";

	@Override
	public void doTag() throws JspException, IOException {

		String _saida = String.format("<input maxlength='10' id='%s' name='%s' type='text' value='%s' onkeypress='formatarData(this)' ",
				this.getNome(), this.getNome(), valor);
		Object _scriptstring = ((PageContext) getJspContext()).getRequest()
				.getAttribute("SCRIPTSTRING");

		String lStringScript = "";

		lStringScript = "$(function() {$(\"#%s\").datepicker({ dateFormat: 'dd/mm/yy', showOn: 'button', buttonImage: '"
				+ this.getImagem() + "', buttonImageOnly: true });});";
		lStringScript = String.format(lStringScript, this.getNome());

		for (String attrName : data.keySet()) {
			String attrValue = (String) data.get(attrName);
			_saida += " " + attrName + "='" + attrValue.trim() + "'";
		}

		_saida += "/>";

		if (_scriptstring != null) {
			_scriptstring = _scriptstring.toString() + lStringScript;
		} else {
			_scriptstring = lStringScript;
		}

		((PageContext) getJspContext()).getRequest().setAttribute(
				"SCRIPTSTRING", _scriptstring);

		PageContext context = (PageContext) this.getJspContext();
		JspWriter out = context.getOut();

		out.print(_saida);

	}

	private String getAttrs() {
		String s = "";
		for (String attrName : data.keySet()) {
			String attrValue = (String) data.get(attrName);
			s += String.format("%s='%s' ", attrName, attrValue);
		}
		return s;
	}

	public void setDynamicAttribute(String uri, String key, Object value)
			throws JspException {
		data.put(key, value);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getFormulario() {
		return formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
