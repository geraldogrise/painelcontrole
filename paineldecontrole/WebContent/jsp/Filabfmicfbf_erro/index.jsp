<%
response.addHeader( "Pragma", "no-cache" );
response.addHeader( "Cache-Control", "no-cache, no-store" );
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/validacoes.js" />"></script>

<form:form method="post" commandName="Filabfmicfbf_erro" id="FRMCRUD">
	<input type="hidden" id="nomelote"    name="nomelote"    value="${nomelote}">
	<div id="barraTopo">
		<div class="migalha">
			<a href="javascript:void(0)"
				onclick="mudaPagina('filabfmicfbf')">Fila BFM / ICFBF</a> <span>
				// </span> <span>Erros por Arquivo - Arquivo...</span>
		</div>
	</div>

	<div class="boxGrid">
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			class="tabelaSistema" id="dadoslista_filabfmicfbf_erro"></table>
	</div>
</form:form>
<script>
	fnPesquisar('#dadoslista_filabfmicfbf_erro',
			'filabfmicfbf_erro', 'Dados não existentes!');

	$(function() {
		alternaLinha();
	})

	function getChave() {

		var Retorno = '';

		if ($("#nomelote").val() != "")
			Retorno = AddSeparador(Retorno, ' {name:"nomelote", value:"'
					+ $("#nomelote").val() + '"}', ',');
	
		return eval("[" + Retorno + "]");
	}
</script>