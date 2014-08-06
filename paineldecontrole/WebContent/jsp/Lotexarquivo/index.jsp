<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.controller.MonitoramentoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="ADNTags" uri="ADNFwTaglib"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html"%>

<div class="ajax lista">
	<div id="barraTopo">
		<div class="migalha">
			<span>Lote X Arquivos</span>
		</div>

		<div class="filtro" id="filtroLotexarquivo">

			<div class="conteudoFiltro" style="width: 250px">
				<span class="fecharFiltro" onclick="escondeFiltro(this)"></span>
				<fieldset>
					<legend>PERÍODO</legend>
					<label style="padding-top: 0"> <ADNTags:data acao="C"
							nome="dt_ini" id="dt_ini" formulario="" valor=""
							imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
							style="width:70px;" /> </label> <label> <span>A</span> </label> <label
						style="padding-top: 0"> <ADNTags:data acao="C"
							nome="dt_fin" id="dt_fin" formulario="" valor=""
							imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
							style="width:70px;" /> </label>
				</fieldset>

				<div class="limpa"></div>
				<label> <span>Arquivo Recebido</span> <input type="text" id="arquivorecebido"
					style="width: 220px" /> </label>
				<div class="limpa"></div>
				<div style="float: right; padding: 5px 0 0 0">
					<a class="btnPadrao" href="javascript:void(0)"  onclick="fnPesquisar('#dadoslista_lote_arquivoprocessado','lote_arquivoprocessado','Dados não existentes!');"><i
						class="consultar"> Pesquisar</i> </a>
				</div>
				<div class="limpa"></div>
			</div>

			<i class="lupa" onclick="mostraFiltro(this)"></i>
		</div>

	</div>


	<div class="boxGrid">
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			class="tabelaSistema" id="dadoslista_lote_arquivoprocessado"></table>

	</div>
</div>
<div class="ajax crudtela"></div>

<script>


	mostraFiltro("#filtroLotexarquivo");
	
	$(function() {
		alternaLinha();
		montaCombo("#terminal");
	})

	function getChave() {

		var Retorno = '';

		if ($("#dt_ini").val() != "")
			Retorno = AddSeparador(Retorno, ' {name:"dt_ini", value:"'
					+ $("#dt_ini").val() + '"}', ',');
		if ($("#dt_fin").val() != "")
			Retorno = AddSeparador(Retorno, ' {name:"dt_fin", value:"'
					+ $("#dt_fin").val() + '"}', ',');
		if ($("#arquivorecebido").val() != "")
			Retorno = AddSeparador(Retorno, ' {name:"arquivo_recebido", value:"'
					+ $("#arquivorecebido").val() + '"}', ',');

		return eval("[" + Retorno + "]");
	}
	
	function validaFiltro() {

		if ($("#dt_ini").val() > $("#dt_fin").val()) {
			MensagemGrowl('Data Inicial não pode ser maior que Data Final',
					'VALIDACAO');
			return false;
		}

		if ($("#dt_ini").val() == '' || $("#dt_fin").val() == '') {
			MensagemGrowl('Data Inicial e Data Final devem ser preenchidas!',
					'VALIDACAO');
			return false;
		}

		return true;

	}

	<ADNTags:flushscript/> 
</script>