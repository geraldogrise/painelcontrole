<%
response.addHeader( "Pragma", "no-cache" );
response.addHeader( "Cache-Control", "no-cache, no-store" );
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/validacoes.js" />"></script>

<form:form method="post" commandName="Monitoramento_lote" id="FRMCRUD">
	<input type="hidden" id="nomelote" name="nomelote" value="${nomelote}">
	<div id="barraTopo">
		<div class="migalha">
			<a href="javascript:void(0)"
				onclick="mudaPagina('monitoramento')">Monitoramento
				dos Lotes</a> <span> // </span> <span>Arquivos do Lote - Lote
				XPTO...</span>
		</div>
	</div>
	<div class="boxGrid">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" class="tabelaSistema" id="dadoslista_monitoramento_lote"></table>
	</div>
</form:form>

<script>


	var _RetornoOutrasChaves;

	$(function() {
		alternaLinha();
	})
	
	fnPesquisar('#dadoslista_monitoramento_lote','monitoramento_lote','Dados não existentes!');
	
	
	function getChave() {

		var Retorno = '';

		if ($("#nomelote").val() != "")
			Retorno = AddSeparador(Retorno, ' {name:"nomelote", value:"'
					+ $("#nomelote").val() + '"}', ',');


		return eval("[" + Retorno + "]");
	}
	
	
	function setOutrasChavesIndex(_nomelote, _nomearquivo) {
		_RetornoOutrasChaves = "";
		if (_nomelote != ""){
			_RetornoOutrasChaves = AddSeparador(_RetornoOutrasChaves,
				' {name:"nomelote", value:"' + _nomelote + '"}', ',');
		}
		if (_nomearquivo != ""){
			setNomeArquivo(_nomearquivo);
			_RetornoOutrasChaves = AddSeparador(_RetornoOutrasChaves,
				' {name:"nomearquivo", value:"' + _nomearquivo + '"}', ',');
		}
	}
	
	function getChaveCrud() {
		

		return eval("[" + _RetornoOutrasChaves + "]");

	}
	function setNomeArquivo(_nomearquivo){
		
		$("#nomeArquivo_lote").val(_nomearquivo);
		
	}

	
</script>