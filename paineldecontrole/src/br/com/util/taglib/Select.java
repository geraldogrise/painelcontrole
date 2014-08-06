package br.com.util.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.util.ModelSelect;

public class Select extends SimpleTagSupport implements DynamicAttributes {

	private HashMap<String, Object> data = new HashMap<String, Object>();
	private List<Object> lista = null;
	private String nmlista = null;
	private String nome = null;
	private String valor = "";
	private String primeirocodigo = null;
	private String primeiradescricao = null;
	private String desabilita = null;
	
	@Override
	public void doTag() throws JspException, IOException {

		String Hiddencampos = "";
		String javascriptnomesmv = "";

		Object _scriptstring = ((PageContext) getJspContext()).getRequest()
				.getAttribute("SCRIPTSTRING");
			
		List<ModelSelect> listamodel = (List<ModelSelect>) ((PageContext) getJspContext()).getRequest().getAttribute(nmlista);
		List<Object> listadesabilita = (List<Object>) ((PageContext) getJspContext()).getRequest().getAttribute(desabilita);
		
		
		// Rotina para Verificar se é multivalorado a rotina pega o json do
		// value e da lista
		if (listamodel.size() > 0) {

			String _valordalista = ((ModelSelect) listamodel.get(0))
					.getCodigo().trim();
			String prefixojson = "";
			String finaljson = "";
			int tamanhovalor = _valordalista.length();

			if (tamanhovalor > 3)
				prefixojson = _valordalista.substring(0, 3);

			if (prefixojson.equals("[{\""))
				finaljson = _valordalista.substring(tamanhovalor - 3,
						tamanhovalor);

			if ((prefixojson.equals("[{\"")) && (finaljson.equals("\"}]"))) {
				ObjectMapper mappervalor = new ObjectMapper();
				JsonFactory factoryvalor = mappervalor.getJsonFactory();

				JsonParser jpvalor = factoryvalor.createJsonParser(this
						.getValor());

				JsonNode actualObjValor = null;
				if (jpvalor.nextToken() == JsonToken.START_ARRAY)
					actualObjValor = mappervalor.readTree(jpvalor);

				// [{"emp_cd":"99"},{"int_cd":"SCR "}]

				ObjectMapper mapper = new ObjectMapper();
				JsonFactory factory = mapper.getJsonFactory();

				JsonParser jp = factory.createJsonParser(_valordalista);
				JsonNode actualObj = mapper.readTree(jp);

				for (int lposicao = 0; lposicao < actualObj.size(); lposicao++) {
					String _nomecampo = actualObj.get(lposicao).getFieldNames()
							.next();

					String valor = "";
					if (actualObjValor != null) {
						valor = actualObjValor.get(lposicao).getElements()
								.next().toString();

					}

					
						Hiddencampos += String
								.format("<input name='%s' id='%s' value='%s' type='hidden' /> ",
										_nomecampo, _nomecampo, valor);
					javascriptnomesmv +=  (String.format("$('#%s').val(objeto[0].%s); ", _nomecampo, _nomecampo));

				}

			}
		}

		PageContext context = (PageContext) this.getJspContext();
		JspWriter out = context.getOut();
		String nomedocombo = this.nome;
		String lStringScript = "";

		if (!Hiddencampos.equals("")) {
			out.print(Hiddencampos);
			nomedocombo += "_mv";
			lStringScript = String.format("$('#%s').change(function() ",
					nomedocombo);
			lStringScript += ("{ var varURL = $('option:selected', this).val();");
			lStringScript += ("var objeto = eval(varURL);");
			lStringScript += javascriptnomesmv;
			lStringScript += "});";
			
			
			 if ( _scriptstring != null ) { 
				 _scriptstring = _scriptstring.toString() + lStringScript; 
			 } 
			 else { 
				 _scriptstring = lStringScript; 
			 }
			 
				((PageContext) getJspContext()).getRequest().setAttribute(
						"SCRIPTSTRING", _scriptstring);

			
		}
		if (!(listadesabilita == null)){
			if (listadesabilita.contains(nomedocombo)){
				out.print(String.format("<input id='%s' name='%s' value='%s' type='hidden'><select id='%s' name='%s' ", nomedocombo, nomedocombo, valor, nomedocombo + "_",
						nomedocombo + "_"));
			}
			
		}
		else
		{
			out.print(String.format("<select id='%s' name='%s' ", nomedocombo,
					nomedocombo));
		}
		
		for (String attrName : data.keySet()) {
			String attrValue = (String) data.get(attrName);
			out.print(" " + attrName + "='" + attrValue.trim() + "'");
		}
		
		if (!(listadesabilita == null)){
			if (listadesabilita.contains(nomedocombo)){
				out.print("disabled = " + '"' + "disabled" + '"');
			}
		}
		out.print("> ");
		String stroption = "<option value='%s' %s >%s</option>";

		if (primeirocodigo != null) {
			if (valor.equals(primeirocodigo))
				out.print(String.format(stroption, this.primeirocodigo,
						" SELECTED ", this.primeiradescricao));
			else
				out.print(String.format(stroption, this.primeirocodigo, " ",
						this.primeiradescricao));

		}

		if (listamodel != null)
			for (ModelSelect object : listamodel) {

				if (valor.trim().equals(object.getCodigo().trim()))
					out.print(String.format(stroption, object.getCodigo(),
							" SELECTED ", object.getDescricao()));
				else
					out.print(String.format(stroption, object.getCodigo(), "",
							object.getDescricao()));
			}
		out.println("</select>");
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

	public final void setLista(List<Object> valor) {
		lista = valor;
	}

	public final void setNome(String valor) {
		nome = valor;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNmlista(String valor) {
		nmlista = valor;
	}

	public final String getNmlista() {
		return nmlista;
	}

	public final void setValor(String _valor) {
		valor = _valor;
	}

	public final String getValor() {
		return valor;
	}

	public final void setPrimeirocodigo(String _valor) {
		primeirocodigo = _valor;
	}

	public final String getPrimeirocodigo() {
		return primeirocodigo;
	}

	public final void setPrimeiradescricao(String _valor) {
		primeiradescricao = _valor;
	}

	public final String getPrimeiradescricao() {
		return primeiradescricao;
	}

	public final void setDesabilita(String _valor) {
		desabilita = _valor;
	}

	public final String getDesabilita() {
		return desabilita;
	}

}
