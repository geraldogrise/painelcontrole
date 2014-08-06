package br.com.model;

import java.math.BigDecimal;

import br.com.util.Coluna;
import br.com.util.EntityBase;
import br.com.util.Tabela;

@Tabela(NomeTabela = "Monitoramento_lote")
public class Monitoramento_lote extends EntityBase {
	
	@Coluna(NomeColuna = "nomearquivo", MetodoGet = "getNomearquivo", MetodoSet = "setNomearquivo")
	private String nomearquivo;

	public String getNomearquivo() {
		return nomearquivo;
	}

	public void setNomearquivo(String nomearquivo) {
		this.nomearquivo = nomearquivo;
	}

	@Coluna(NomeColuna = "status", MetodoGet = "getStatus", MetodoSet = "setStatus")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Coluna(NomeColuna = "totallinhas", MetodoGet = "getTotallinhas", MetodoSet = "setTotallinhas")
	private BigDecimal totallinhas;

	public BigDecimal getTotallinhas() {
		return totallinhas;
	}

	public void setTotallinhas(BigDecimal totallinhas) {
		this.totallinhas = totallinhas;
	}

	@Coluna(NomeColuna = "valortotalarquivo", MetodoGet = "getValortotalarquivo", MetodoSet = "setValortotalarquivo")
	private BigDecimal valortotalarquivo;

	public BigDecimal getValortotalarquivo() {
		return valortotalarquivo;
	}

	public void setValortotalarquivo(BigDecimal valortotalarquivo) {
		this.valortotalarquivo = valortotalarquivo;
	}

	@Coluna(NomeColuna = "linhasprocessadas", MetodoGet = "getLinhasprocessadas", MetodoSet = "setLinhasprocessadas")
	private BigDecimal linhasprocessadas;

	public BigDecimal getLinhasprocessadas() {
		return linhasprocessadas;
	}

	public void setLinhasprocessadas(BigDecimal linhasprocessadas) {
		this.linhasprocessadas = linhasprocessadas;
	}

	@Coluna(NomeColuna = "valorprocessado", MetodoGet = "getValorprocessado", MetodoSet = "setValorprocessado")
	private BigDecimal valorprocessado;

	public BigDecimal getValorprocessado() {
		return valorprocessado;
	}

	public void setValorprocessado(BigDecimal valorprocessado) {
		this.valorprocessado = valorprocessado;
	}

	@Coluna(NomeColuna = "linhaprocessadaserro", MetodoGet = "getLinhaprocessadaserro", MetodoSet = "setLinhaprocessadaserro")
	private BigDecimal linhaprocessadaserro;

	public BigDecimal getLinhaprocessadaserro() {
		return linhaprocessadaserro;
	}

	public void setLinhaprocessadaserro(BigDecimal linhaprocessadaserro) {
		this.linhaprocessadaserro = linhaprocessadaserro;
	}

	@Coluna(NomeColuna = "valorprocessadaserro", MetodoGet = "getValorprocessadaserro", MetodoSet = "setValorprocessadaserro")
	private BigDecimal valorprocessadaserro;

	public BigDecimal getValorprocessadaserro() {
		return valorprocessadaserro;
	}

	public void setValorprocessadoerro(BigDecimal valorprocessadaserro) {
		this.valorprocessadaserro = valorprocessadaserro;
	}


	@Coluna(NomeColuna = "dataimportacao", MetodoGet = "getDataimportacao", MetodoSet = "setDataimportacao")
	private String dataimportacao;

	public String getDataimportacao() {
		return dataimportacao;
	}

	public void setDataimportacao(String dataimportacao) {
		this.dataimportacao = dataimportacao;
	}

}
