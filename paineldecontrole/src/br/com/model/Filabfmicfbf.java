package br.com.model;

import java.math.BigDecimal;

import br.com.util.Coluna;
import br.com.util.EntityBase;
import br.com.util.Tabela;
import br.com.util.TipoPKColuna;

@Tabela(NomeTabela = "Filabfmicfbf")
public class Filabfmicfbf extends EntityBase {

	@Coluna(NomeColuna = "nomelote", MetodoGet = "getNomelote", MetodoSet = "setNomelote", IsPK = true, TipoPK = TipoPKColuna.CAMPO)
	private String nomelote;

	public String getNomelote() {
		return nomelote;
	}

	public void setNomelote(String nomelote) {
		this.nomelote = nomelote;
	}

	@Coluna(NomeColuna = "totalregistro", MetodoGet = "getTotalregistro", MetodoSet = "setTotalregistro")
	private BigDecimal totalregistro;

	public BigDecimal getTotalregistro() {
		return totalregistro;
	}

	public void setTotalregistro(BigDecimal totalregistro) {
		this.totalregistro = totalregistro;
	}

	@Coluna(NomeColuna = "valorlote", MetodoGet = "getValorlote", MetodoSet = "setValorlote")
	private BigDecimal valorlote;

	public BigDecimal getValorlote() {
		return valorlote;
	}

	public void setValorlote(BigDecimal valorlote) {
		this.valorlote = valorlote;
	}

	/* BFM */

	@Coluna(NomeColuna = "registroprocessado_bfm", MetodoGet = "getRegistroprocessado_bfm", MetodoSet = "setRegistroprocessado_bfm")
	private BigDecimal registroprocessado_bfm;

	public BigDecimal getRegistroprocessado_bfm() {
		return registroprocessado_bfm;
	}

	public void setRegistroprocessado_bfm(BigDecimal registroprocessado_bfm) {
		this.registroprocessado_bfm = registroprocessado_bfm;
	}

	@Coluna(NomeColuna = "valorprocessado_bfm", MetodoGet = "getValorprocessado_bfm", MetodoSet = "setValorprocessado_bfm")
	private BigDecimal valorprocessado_bfm;

	public BigDecimal getValorprocessado_bfm() {
		return valorprocessado_bfm;
	}

	public void setValorprocessado_bfm(BigDecimal valorprocessado_bfm) {
		this.valorprocessado_bfm = valorprocessado_bfm;
	}

	@Coluna(NomeColuna = "registroprocessadoerro_bfm", MetodoGet = "getRegistroprocessadoerro_bfm", MetodoSet = "setRegistroprocessadoerro_bfm")
	private BigDecimal registroprocessadoerro_bfm;

	public BigDecimal getRegistroprocessadoerro_bfm() {
		return registroprocessadoerro_bfm;
	}

	public void setRegistroprocessadoerro_bfm(
			BigDecimal registroprocessadoerro_bfm) {
		this.registroprocessadoerro_bfm = registroprocessadoerro_bfm;
	}

	@Coluna(NomeColuna = "valorprocessadoerro_bfm", MetodoGet = "getValorprocessadoerro_bfm", MetodoSet = "setValorprocessadoerro_bfm")
	private BigDecimal valorprocessadoerro_bfm;

	public BigDecimal getValorprocessadoerro_bfm() {
		return valorprocessadoerro_bfm;
	}

	public void setValorprocessadoerro_bfm(BigDecimal valorprocessadoerro_bfm) {
		this.valorprocessadoerro_bfm = valorprocessadoerro_bfm;
	}

	@Coluna(NomeColuna = "ultimadata_bfm", MetodoGet = "getUltimadata_bfm", MetodoSet = "setUltimadata_bfm")
	private String ultimadata_bfm;

	public String getUltimadata_bfm() {
		return ultimadata_bfm;
	}

	public void setUltimadata_bfm(String ultimadata_bfm) {
		this.ultimadata_bfm = ultimadata_bfm;
	}

	/* ICFBF */

	@Coluna(NomeColuna = "registroprocessado_icfbf", MetodoGet = "getRegistroprocessado_icfbf", MetodoSet = "setRegistroprocessado_icfbf")
	private BigDecimal registroprocessado_icfbf;

	public BigDecimal getRegistroprocessado_icfbf() {
		return registroprocessado_icfbf;
	}

	public void setRegistroprocessado_icfbf(BigDecimal registroprocessado_icfbf) {
		this.registroprocessado_icfbf = registroprocessado_icfbf;
	}

	@Coluna(NomeColuna = "valorprocessado_icfbf", MetodoGet = "getValorprocessado_icfbf", MetodoSet = "setValorprocessado_icfbf")
	private BigDecimal valorprocessado_icfbf;

	public BigDecimal getValorprocessado_icfbf() {
		return valorprocessado_icfbf;
	}

	public void setValorprocessado_icfbf(BigDecimal valorprocessado_icfbf) {
		this.valorprocessado_icfbf = valorprocessado_icfbf;
	}

	@Coluna(NomeColuna = "registroprocessadoerro_icfbf", MetodoGet = "getRegistroprocessadoerro_icfbf", MetodoSet = "setRegistroprocessadoerro_icfbf")
	private BigDecimal registroprocessadoerro_icfbf;

	public BigDecimal getRegistroprocessadoerro_icfbf() {
		return registroprocessadoerro_icfbf;
	}

	public void setRegistroprocessadoerro_icfbf(
			BigDecimal registroprocessadoerro_icfbf) {
		this.registroprocessadoerro_icfbf = registroprocessadoerro_icfbf;
	}

	@Coluna(NomeColuna = "valorprocessadoerro_icfbf", MetodoGet = "getValorprocessadoerro_icfbf", MetodoSet = "setValorprocessadoerro_icfbf")
	private BigDecimal valorprocessadoerro_icfbf;

	public BigDecimal getValorprocessadoerro_icfbf() {
		return valorprocessadoerro_icfbf;
	}

	public void setValorprocessadoerro_icfbf(
			BigDecimal valorprocessadoerro_icfbf) {
		this.valorprocessadoerro_icfbf = valorprocessadoerro_icfbf;
	}

	@Coluna(NomeColuna = "ultimadata_icfbf", MetodoGet = "getUltimadata_icfbf", MetodoSet = "setUltimadata_icfbf")
	private String ultimadata_icfbf;

	public String getUltimadata_icfbf() {
		return ultimadata_icfbf;
	}

	public void setUltimadata_icfbf(String ultimadata_icfbf) {
		this.ultimadata_icfbf = ultimadata_icfbf;
	}

}