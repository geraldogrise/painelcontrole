<%
response.addHeader( "Pragma", "no-cache" );
response.addHeader( "Cache-Control", "no-cache, no-store" );
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/validacoes.js" />"></script>

<form:form method="post" commandName="Monitoramento_lote_erro" id="FRMCRUD">
	<input type="hidden" id="nomelote"    name="nomelote"    value="${nomelote}">
	<input type="hidden" id="nomearquivo" name="nomearquivo" value="${nomearquivo}">
	<div id="barraTopo">
		<div class="migalha">
			<a href="javascript:void(0)"
				onclick="mudaPagina('Fila_bfm_icfbf.html')">Fila BFM / ICFBF</a> <span>
				// </span> <span>Erros por Arquivo - Arquivo...</span>
		</div>
	</div>

	<div class="boxGrid">
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			class="tabelaSistema" id="dadoslista_monitoramento_lote_erro"></table>
	</div>
</form:form>
<script>
	fnPesquisar('#dadoslista_monitoramento_lote_erro',
			'monitoramento_lote_erro', 'Dados não existentes!');

	$(function() {
		alternaLinha();
	})

	function getChave() {

		var Retorno = '';

		if ($("#nomelote").val() != "")
			Retorno = AddSeparador(Retorno, ' {name:"nomelote", value:"'
					+ $("#nomelote").val() + '"}', ',');
		if ($("#nomearquivo").val() != "")
			Retorno = AddSeparador(Retorno, ' {name:"nomearquivo", value:"'
					+ $("#nomearquivo").val() + '"}', ',');
	
		return eval("[" + Retorno + "]");
	}
</script>