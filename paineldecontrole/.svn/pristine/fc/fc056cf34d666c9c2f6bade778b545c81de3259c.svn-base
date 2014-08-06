package br.com.util.taglib;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Radio extends SimpleTagSupport implements DynamicAttributes {

	private HashMap<String, Object> data = new HashMap<String, Object>();

	private String nome = null;
	private String valor = "";
	private String valormarcado = "";
	private String titulo = "";
	private String acao = "";
	private String formulario = "";
	

	@Override
	public void doTag() throws JspException, IOException {

		String checked = "";
		
		if ( valormarcado.equals(valor) )
			checked = "CHECKED";
		
		String _saida = "";			
		if (acao.equals("salvar") || acao.equals("salvaredit") || acao.equals("salvareditlayoutcampo")){
			_saida = String.format("<input id='%s' name='%s' type='radio' value='%s' %s ",
					this.getNome(), this.getNome(), valormarcado, checked );
		}else{
			_saida = String.format("<input id='%s' name='%s' type='radio' value='%s' %s ",
					this.getNome(), this.getNome(), valor, checked );
			
		}
		Object _scriptstring = ((PageContext) getJspContext()).getRequest()
				.getAttribute("SCRIPTSTRING");

		for (String attrName : data.keySet()) {
			String attrValue = (String) data.get(attrName);
			_saida += " " + attrName + "='" + attrValue.trim() + "'";
		}

		_saida += "/>";
		
		_saida += titulo;
		
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

	
	public String getValormarcado() {
		return valormarcado;
	}

	public void setValormarcado(String valormarcado) {
		this.valormarcado = valormarcado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
