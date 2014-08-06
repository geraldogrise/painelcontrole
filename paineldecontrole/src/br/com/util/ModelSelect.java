package br.com.util;


import br.com.util.Coluna;
import br.com.util.EntityBase;
import br.com.util.Tabela;


@Tabela(NomeTabela = "")
public class ModelSelect extends EntityBase {

	@Coluna(NomeColuna = "codigo", MetodoGet = "getCodigo", MetodoSet = "setCodigo")
	private String codigo;

	@Coluna(NomeColuna = "descricao", MetodoGet = "getDescricao", MetodoSet = "setDescricao")
	private String descricao;

	@Coluna(NomeColuna = "adicional", MetodoGet = "getAdicional", MetodoSet = "setAdicional")
	private String adicional;

	
	public ModelSelect() {
		
	}
	public ModelSelect( String _codigo, String _descricao, String _adicional ) {
	   this.setCodigo(_codigo);
	   this.setDescricao(_descricao);
	   this.setAdicional(_adicional);
	
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String value) {
		this.codigo = value;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String value) {
		this.descricao = value;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String value) {
		this.adicional = value;
	}
}
