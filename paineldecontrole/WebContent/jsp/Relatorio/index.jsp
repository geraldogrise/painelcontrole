
<%
	response.addHeader("Pragma", "no-cache");
	response.addHeader("Cache-Control", "no-cache, no-store");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/validacoes.js" />"></script>
<%@ taglib prefix="ADNTags" uri="ADNFwTaglib"%>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@page import="java.util.*"%>





<input type="hidden" value='despesa' id='flag_filtro3'>
<div id="barraTopo">
	<div class="migalha">
		<span>Relatórios </span>
	</div>


	<div class="filtro"></div>


</div>



<div class="bgMpR">
	<div class="bgCimaMpR">
		<div class="mapaRelatorios">

			<div class="boxRelatorios">
				<a href="javascript:void(0)"
					onclick="mudaPaginaRelatorio(this,'#filtro1')"> <img
					src="../../painelcontrole/resources/images/rel1.jpg" /> <span>Relatório
						Por UF</span> </a>
			</div>

			<div class="boxRelatorios">
				<a href="javascript:void(0)"
					onclick="mudaPaginaRelatorio(this,'#filtro1b')"> <img
					src="../../painelcontrole/resources/images/rel1.jpg" /> <span>Processamento
						por data BFM / ICFBF</span> </a>
			</div>


			<div class="boxRelatorios">
				<a href="javascript:void(0)"
					onclick="mudaPaginaRelatorio(this,'#filtro2')"> <img
					src="../../painelcontrole/resources/images/rel1.jpg" /> <span>Processamento
						por data BFF / ICFBF</span> </a>
			</div>

			<div class="boxRelatorios">
				<a href="javascript:void(0)"
					onclick="setFiltro3('relatorio_tipo_uso_provider');mudaPaginaRelatorio(this,'#filtro3');">
					<img src="../../painelcontrole/resources/images/rel1.jpg" /> <span>Tipo
						de uso provider - BFM</span> </a>
			</div>

			<div class="boxRelatorios">
				<a href="javascript:void(0)"
					onclick="setFiltro3('relatorio_tipo_uso_provider_despesa');mudaPaginaRelatorio(this,'#filtro3');">
					<img src="../../painelcontrole/resources/images/rel1.jpg" /> <span>Tipo
						de uso provider e despesas</span> </a>
			</div>

			<!--  <div class="boxRelatorios">
				<a href="javascript:void(0)"
					onclick="mudaPaginaRel!atorio(this,'#filtro4')"> <img
					src="../../painelcontrole/resources/images/rel1.jpg" /> <span>BFF</span>
				</a>
			</div>-->


			<div class="boxRelatorios">
				<a href="javascript:void(0)"
					onclick="mudaPaginaRelatorio(this,'#filtro5')"> <img
					src="../../painelcontrole/resources/images/rel1.jpg" /> <span>Clientes</span>
				</a>
			</div>

			<div class="limpa"></div>
		</div>
	</div>
</div>


<div id="guardaFiltros">
	<form:form method="post" commandName="relatorio" id="FRMCRUD">
		<input type="hidden" id="tipoImpressao" name="tipoImpressao">
		<input type="hidden" id="procedure" name="procedure">
		<input type="hidden" id="jasper" name="jasper">
		<input type="hidden" id="dt_ini" name="dt_ini">
		<input type="hidden" id="dt_fin" name="dt_fin">
		<input type="hidden" id="ufselecionada" name="ufselecionada">

<!-- Filtro Relatorio ... -->
		<div id="filtro1" class="bFiltros">
			<div class="conteudoFiltro" style="width: 260px"
				id="envia_post_filtro1">
				<span class="fecharFiltro" onclick="escondeFiltro(this)"></span>
				<fieldset>
					<legend>PERÍODO</legend>
					<label style="padding-top: 0"> <ADNTags:data acao="C"
							nome="dt_ini_filtro1" id="dt_ini_filtro1" formulario="" valor=""
							imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
							style="width:70px;" class="data" /> </label> <label> <span>Até</span>
					</label> <label style="padding-top: 0"> <ADNTags:data acao="C"
							nome="dt_fin_filtro1" id="dt_fin_filtro1" formulario="" valor=""
							imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
							style="width:70px;" class="data" /> </label>
				</fieldset>
				<div class="boxGrid">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tabelaSistema">
						<tr>
							<th width="43" align="center"><input type="checkbox"
								id="todos"
								onclick="chekarTodos('#'+$(this).attr('id'),'.marcar')" class="" />
							</th>
							<th align="center">UF&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						</tr>
					</table>
				</div>
				<div class="boxGrid" style="overflow-y: scroll; height: 150px"
					id="uf_filtro1b">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tabelaSistema">
						<tr>
							<td width="43" align="center"><input type="checkbox"
								value="RO" class="marcar" onclick="selecionaUf(this)"
								name="uf_td" /></td>
							<td align="center">RO</td>
						</tr>
						<tr>
							<td align="center"><input value="AC" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">AC</td>
						</tr>
						<tr>
							<td align="center"><input value="AM" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">AM</td>
						</tr>
						<tr>
							<td align="center"><input value="RR" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">RR</td>
						</tr>
						<tr>
							<td align="center"><input value="PA" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">PA</td>
						</tr>
						<tr>
							<td align="center"><input value="AP" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">AP</td>
						</tr>
						<tr>
							<td align="center"><input value="TO" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">TO</td>
						</tr>
						<tr>
							<td align="center"><input value="MA" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">MA</td>
						</tr>
						<tr>
							<td align="center"><input value="PI" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">PI</td>
						</tr>
						<tr>
							<td align="center"><input value="CE" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">CE</td>
						</tr>
						<tr>
							<td align="center"><input value="RN" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">RN</td>
						</tr>
						<tr>
							<td align="center"><input value="PB" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">PB</td>
						</tr>
						<tr>
							<td align="center"><input value="PE" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">PE</td>
						</tr>
						<tr>
							<td align="center"><input value="AL" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">AL</td>
						</tr>
						<tr>
							<td align="center"><input value="SE" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">SE</td>
						</tr>
						<tr>
							<td align="center"><input value="BA" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">BA</td>
						</tr>
						<tr>
							<td align="center"><input value="MG" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">MG</td>
						</tr>
						<tr>
							<td align="center"><input value="ES" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">ES</td>
						</tr>
						<tr>
							<td align="center"><input value=RJ name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">RJ</td>
						</tr>
						<tr>
							<td align="center"><input value="SP" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">SP</td>
						</tr>
						<tr>
							<td align="center"><input value="PR" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">PR</td>
						</tr>
						<tr>
							<td align="center"><input value="SC" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">SC</td>
						</tr>
						<tr>
							<td align="center"><input value="RS" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">RS</td>
						</tr>
						<tr>
							<td align="center"><input value="MS" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">MS</td>
						</tr>
						<tr>
							<td align="center"><input value="MT" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">MT</td>
						</tr>
						<tr>
							<td align="center"><input value="GO" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">GO</td>
						</tr>
						<tr>
							<td align="center"><input value="DF" name="uf_td"
								type="checkbox" class="marcar" onclick="selecionaUf(this)" />
							</td>
							<td align="center">DF</td>
						</tr>


					</table>
				</div>


				<div class="limpa"></div>
				<label> <span>Terminal</span> <select id="terminal"
					class="combo1" style="width: 112px">
						<option>PRÉ</option>
						<option>PÓS</option>
				</select> </label> <label> <span>Sistema</span> <select id="sistema"
					class="combo2" style="width: 112px">
						<option>BFM</option>
						<option>BFF</option>
						<option>ICFBF</option>
				</select> </label>

				<div class="limpa"></div>

				<a class="btnPadrao" href="javascript:void(0)"
					onclick='if(Valida("filtro1")){MontoChamadaPost("pdf", "UP_REL_DATA_BFF_ICFBF", "relatorio_por_uf_bfm", "envia_post_filtro1");}'
					style="float: right; margin-top: 5px;"><i class="imprimir">
						Imprimir</i> </a> <a class="btnPadrao" href="javascript:void(0)"
					onclick='if(Valida("filtro1")){MontoChamadaPost("excel", "UP_REL_DATA_BFF_ICFBF", "relatorio_por_uf_bfm", "envia_post_filtro1");}'
					style="float: right; margin-top: 5px;"><i class="exportar">
						Exportar</i> </a>
			</div>
			<i class="lupa" onclick="mostraFiltro(this)"></i>
		</div>
	</form:form>

	<div id="filtro1b" class="bFiltros">
		<div class="conteudoFiltro" style="width: 260px"
			id="envia_post_filtro1b">
			<span class="fecharFiltro" onclick="escondeFiltro(this)"></span>
			<fieldset>
				<legend>PERÍODO</legend>
				<label style="padding-top: 0"> <ADNTags:data acao="C"
						nome="dt_ini_filtro1b" id="dt_ini_filtro1b" formulario="" valor=""
						imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
						style="width:70px;" class="data" /> </label> <label> <span>Até</span>
				</label> <label style="padding-top: 0"> <ADNTags:data acao="C"
						nome="dt_fin_filtro1b" id="dt_fin_filtro1b" formulario="" valor=""
						imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
						style="width:70px;" class="data" /> </label>
			</fieldset>

			<div class="boxGrid">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="tabelaSistema">
					<tr>
						<th width="43" align="center"><input
							onclick="chekarTodos('#'+$(this).attr('id'),'.mfiltro', 'uf_filtro1b')"
							id="todos2" type="checkbox" class="" /></th>
						<th align="center">UF&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
					</tr>
				</table>
			</div>
			<div class="boxGrid" style="overflow-y: scroll; height: 150px">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="tabelaSistema">
					<tr>
						<td width="43" align="center"><input type="checkbox"
							value="RO" class="mfiltro" onclick="selecionaUf(this)"
							name="uf_td" /></td>
						<td align="center">RO</td>
					</tr>
					<tr>
						<td align="center"><input value="AC" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">AC</td>
					</tr>
					<tr>
						<td align="center"><input value="AM" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">AM</td>
					</tr>
					<tr>
						<td align="center"><input value="RR" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">RR</td>
					</tr>
					<tr>
						<td align="center"><input value="PA" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">PA</td>
					</tr>
					<tr>
						<td align="center"><input value="AP" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">AP</td>
					</tr>
					<tr>
						<td align="center"><input value="TO" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">TO</td>
					</tr>
					<tr>
						<td align="center"><input value="MA" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">MA</td>
					</tr>
					<tr>
						<td align="center"><input value="PI" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">PI</td>
					</tr>
					<tr>
						<td align="center"><input value="CE" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">CE</td>
					</tr>
					<tr>
						<td align="center"><input value="RN" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">RN</td>
					</tr>
					<tr>
						<td align="center"><input value="PB" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">PB</td>
					</tr>
					<tr>
						<td align="center"><input value="PE" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">PE</td>
					</tr>
					<tr>
						<td align="center"><input value="AL" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">AL</td>
					</tr>
					<tr>
						<td align="center"><input value="SE" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">SE</td>
					</tr>
					<tr>
						<td align="center"><input value="BA" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">BA</td>
					</tr>
					<tr>
						<td align="center"><input value="MG" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">MG</td>
					</tr>
					<tr>
						<td align="center"><input value="ES" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">ES</td>
					</tr>
					<tr>
						<td align="center"><input value=RJ name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">RJ</td>
					</tr>
					<tr>
						<td align="center"><input value="SP" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">SP</td>
					</tr>
					<tr>
						<td align="center"><input value="PR" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">PR</td>
					</tr>
					<tr>
						<td align="center"><input value="SC" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">SC</td>
					</tr>
					<tr>
						<td align="center"><input value="RS" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">RS</td>
					</tr>
					<tr>
						<td align="center"><input value="MS" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">MS</td>
					</tr>
					<tr>
						<td align="center"><input value="MT" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">MT</td>
					</tr>
					<tr>
						<td align="center"><input value="GO" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">GO</td>
					</tr>
					<tr>
						<td align="center"><input value="DF" name="uf_td"
							type="checkbox" class="mfiltro" onclick="selecionaUf(this)" />
						</td>
						<td align="center">DF</td>
					</tr>

				</table>
			</div>


			<div class="limpa"></div>
			<label> <span>Terminal</span> <select id="terminal"
				class="combo1" style="width: 232px">
					<option>PRÉ</option>
					<option>PÓS</option>
			</select> </label>

			<div class="limpa"></div>

			<a class="btnPadrao" href="javascript:void(0)"
				onclick='if(Valida("filtro1b")){MontoChamadaPost("pdf", "UP_REL_DATA_BFM_ICFBF", "relatorio_processamento_por_data_bfm", "envia_post_filtro1b");}'
				style="float: right; margin-top: 5px;"><i class="imprimir">
					Imprimir</i> </a> <a class="btnPadrao" href="javascript:void(0)"
					onclick='if(Valida("filtro1b")){MontoChamadaPost("excel", "UP_REL_DATA_BFM_ICFBF", "relatorio_processamento_por_data_bfm", "envia_post_filtro1b");}'
				style="float: right; margin-top: 5px;"><i class="exportar">
					Exportar</i> </a>
		</div>
		<i class="lupa" onclick="mostraFiltro(this)"></i>
	</div>


	<div id="filtro2" class="bFiltros">
		<div class="conteudoFiltro" style="width: 260px"
			id="envia_post_filtro2">
			<span class="fecharFiltro" onclick="escondeFiltro(this)"></span>
			<fieldset>
				<legend>PERÍODO</legend>
				<label style="padding-top: 0"> <ADNTags:data acao="C"
						nome="dt_ini_filtro3" id="dt_ini_filtro3" formulario="" valor=""
						imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
						style="width:70px;" class="data" /> </label> <label> <span>Até</span>
				</label> <label style="padding-top: 0"> <ADNTags:data acao="C"
						nome="dt_fin_filtro3" id="dt_fin_filtro3" formulario="" valor=""
						imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
						style="width:70px;" class="data" /> </label>
			</fieldset>
			<div class="limpa"></div>
			<a class="btnPadrao" href="javascript:void(0)"
				onclick='if(Valida("filtro2")){MontoChamadaPost("pdf", "UP_REL_DATA_BFF_ICFBF", "relatorio_processamento_por_data_bff", "envia_post_filtro2");}'
				style="float: right; margin-top: 5px;"><i class="imprimir">
					Imprimir</i> </a> <a class="btnPadrao" href="javascript:void(0)"
				onclick='if(Valida("filtro2")){MontoChamadaPost("excel", "UP_REL_DATA_BFF_ICFBF", "relatorio_processamento_por_data_bff", "envia_post_filtro2");}'
				style="float: right; margin-top: 5px;"><i class="exportar">
					Exportar</i> </a>
		</div>
		<i class="lupa" onclick="mostraFiltro(this)"></i>
	</div>

	<div id="filtro3" class="bFiltros">
		<div class="conteudoFiltro"
			style="width: 321px; position: relative; height: 120px"
			id="envia_post_filtro3">

			<span class="fecharFiltro" onclick="escondeFiltro(this)"></span> <label
				class=""> <span>Dt. Inclusão</span> <ADNTags:data acao="C"
					nome="dt_ini_filtro3" id="dt_ini_filtro3" formulario=""
					valor="28/04/2014"
					imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
					style="width:70px;" class="data" /> </label>				
			<div class="boxGrid"
				style="width: 200px; position: absolute; z-index: 3; left: 110px; top: 21px; background: #fff;"
				onclick="siwtchBox(this,'#eN')">
				<div class="comboDiferente">
					Evento de Negócio&nbsp;<b>(<span class="quaItem">0</span>)</b>
				</div>
				<table width="100%" id="eN" cellspacing="0" cellpadding="0"
					border="0" style="min-width: inherit;"
					class="tabelaSistema gridComDif">

					<tr>
						<td width="10px" align="center"><input class="marcaTodos"
							type="checkbox"
							onclick="marcaTodos(this,$(this).closest('table').find('input:checkbox'))" />
						</td>
						<td align="left">Todos</td>
					</tr>

					<tr>
						<td width="10px" align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Evento 1</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Evento 2</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Evento 3</td>
					</tr>
				</table>
			</div>

			<div class="boxGrid"
				style="width: 150px; position: absolute; z-index: 1; left: 8px; top: 57px; background: #fff;"
				onclick="siwtchBox(this,'#tP')">
				<div class="comboDiferente">
					Tipo de Uso&nbsp;<b>(<span class="quaItem">0</span>)</b>
				</div>
				<table width="100%" id="tP" cellspacing="0" cellpadding="0"
					border="0" style="min-width: inherit;"
					class="tabelaSistema gridComDif">

					<tr>
						<td width="10px" align="center"><input class="marcaTodos"
							type="checkbox"
							onclick="marcaTodos(this,$(this).closest('table').find('input:checkbox'))" />
						</td>
						<td align="left">Todos</td>
					</tr>

					<tr>
						<td width="10px" align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Tipo de Uso 1</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Tipo de Uso 2</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Tipo de Uso 3</td>
					</tr>
				</table>
			</div>

			<div class="boxGrid"
				style="width: 150px; position: absolute; z-index: 1; left: 163px; top: 57px; background: #fff;"
				onclick="siwtchBox(this,'#pro')">
				<div class="comboDiferente">
					Tipo de Uso&nbsp;<b>(<span class="quaItem">0</span>)</b>
				</div>
				<table width="100%" id="pro" cellspacing="0" cellpadding="0"
					border="0" style="min-width: inherit;"
					class="tabelaSistema gridComDif">

					<tr>
						<td width="10px" align="center"><input class="marcaTodos"
							type="checkbox"
							onclick="marcaTodos(this,$(this).closest('table').find('input:checkbox'))" />
						</td>
						<td align="left">Todos</td>
					</tr>

					<tr>
						<td width="10px" align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Tipo de Uso 1</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Tipo de Uso 2</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox"
							onclick="mostraQuant($(this).closest('table').find('input:checked'),$(this).closest('.boxGrid').find('.quaItem'))" />
						</td>
						<td align="left">Tipo de Uso 3</td>
					</tr>
				</table>
			</div>




			<div class="limpa" style="padding: 36px 0 0 0"></div>
			<a class="btnPadrao" href="javascript:void(0)"
				onclick='if(Valida("")){MontoChamadaPost("pdf", "UP_REL_TIPOUSO_PROVIDER_BFM",$("#flag_filtro3").val(), "envia_post_filtro3");}'
				style="float: right; margin-top: 5px;"><i class="imprimir">
					Imprimir</i> </a> <a class="btnPadrao" href="javascript:void(0)"
				onclick='if(Valida("")){MontoChamadaPost("excel", "UP_REL_TIPOUSO_PROVIDER_BFM",$("#flag_filtro3").val(), "envia_post_filtro3");}'
				style="float: right; margin-top: 5px;"><i class="exportar">
					Exportar</i> </a>
		</div>
		<i class="lupa" onclick="mostraFiltro(this)"
			style="position: absolute; right: 0; top: 0;"></i>
	</div>


	<div id="filtro4" class="bFiltros">
		<div class="conteudoFiltro" style="width: 172px">
			<span class="fecharFiltro" onclick="escondeFiltro(this)"></span>
			<div class="limpa"></div>
			<a class="btnPadrao" href="javascript:void(0)"
				onclick='if(Valida()){MontoChamadaPost("pdf", "UP_REL_TIPOUSO_PROVIDER_BFM", "relatorio_tipo_uso_provider_despesa", "envia_post_filtro3");}'
				style="float: right; margin-top: 5px;"><i class="imprimir">
					Imprimir</i> </a> <a class="btnPadrao" href="javascript:void(0)"
					onclick='if(Valida()){MontoChamadaPost("excel", "UP_REL_TIPOUSO_PROVIDER_BFM", "relatorio_tipo_uso_provider_despesa", "envia_post_filtro3");}'
				style="float: right; margin-top: 5px;"><i class="exportar">
					Exportar</i> </a>
		</div>
		<i class="lupa" onclick="mostraFiltro(this)"></i>
	</div>


	<div id="filtro5" class="bFiltros">
		<div class="conteudoFiltro" style="width: 350px"
			id="envia_post_filtro5">
			<span class="fecharFiltro" onclick="escondeFiltro(this)"></span> <label>
				<span>RES / NRES</span> <select id="terminal" class="combo1"
				style="width: 72px">
					<option>RES</option>
					<option>NRES</option>
			</select> </label>

			<fieldset>
				<legend>PERÍODO</legend>
				<label style="padding-top: 0"> <ADNTags:data acao="C"
						nome="dt_ini_filtro5" id="dt_ini_filtro5" formulario=""
						valor="03/06/2014"
						imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
						style="width:70px;" class="data" /> </label> <label> <span>Até</span>
				</label> <label style="padding-top: 0"> <ADNTags:data acao="C"
						nome="dt_fin_filtro5" id="dt_fin_filtro5" formulario="" valor=""
						imagem="../../painelcontrole/resources/imagem/images/bt_calendario.png"
						style="width:70px;" class="data" /> </label>
			</fieldset>
			<div class="limpa"></div>
			<a class="btnPadrao" href="javascript:void(0)"
				onclick='if(Valida("filtro5")){MontoChamadaPost("pdf", "UP_REL_CLIENTES", "relatorio_cliente_bff", "envia_post_filtro5");}'
				style="float: right; margin-top: 5px;"><i class="imprimir">
					Imprimir</i> </a> <a class="btnPadrao" href="javascript:void(0)"
					onclick='if(Valida("filtro5")){MontoChamadaPost("excel", "UP_REL_CLIENTES", "relatorio_cliente_bff", "envia_post_filtro5");}'
				style="float: right; margin-top: 5px;"><i class="exportar">
					Exportar</i> </a>
		</div>
		<i class="lupa" onclick="mostraFiltro(this);"></i>
	</div>
</div>

<div id="localformpost" style="display: none; visibility: hidden;"></div>

<script type="text/javascript">
	function Valida(form) {
		
		if(form != ""){
			form = form.replace('2', '3');
			if ($("#dt_ini_" + form).val() > $("#dt_fin_" + form).val()) {
				MensagemGrowl('Data Inicial não pode ser maior que Data Final',
						'VALIDACAO');
				return false;
			}
	
			if ($("#dt_ini_" + form).val() == ''
					|| $("#dt_fin_" + form).val() == '') {
				MensagemGrowl('Data Inicial e Data Final devem ser preenchidas!',
						'VALIDACAO');
				return false;
			}
		}

		return true;

	}

	function MontoChamadaPost(tipoImpressao, procedure, jasper, form) {

		$("#tipoImpressao").val(tipoImpressao);
		$("#procedure").val(procedure);
		$("#jasper").val(jasper);

		$("#localformpost")
				.html(
						'<form id="relatorio_post" name="relatorio_post" action="../../painelcontrole/relatorio/imprimirrelatorio" method="post" target="_blank">'
								+ '<input type="hidden" id="tipoImpressao" name="tipoImpressao" value="">'
								+ '<input type="hidden" id="dt_ini" 	name="dt_ini" 	value="">'
								+ '<input type="hidden" id="dt_fin" 	name="dt_fin" 	value="">'
								+ '<input type="hidden" id="uf" 		name="uf" 	  	value="">'
								+ '<input type="hidden" id="terminal" 	name="terminal" value="">'
								+ '<input type="hidden" id="sistema"	name="sistema" 	value="">'
								+ '<input type="hidden" id="procedure" 	name="procedure" value="">'
								+ '<input type="hidden" id="jasper" 	name="jasper" 	value="">'
								+ '<input type="hidden" id="tb_uf_selecionada" 	name="ufselecionada" 	value="'
								+ $('#ufselecionada').val() + '">'

								+ '</form>');

		var $inputs = $('#' + form + ' :input');
		$inputs.each(function() {
			try {
				id = this.id;
				valor = $(this).val();
				separador = "";
				if ($(this).hasClass('data')) {
					id = this.id.substring(0, 6);

				}

				$("#relatorio_post").find("#" + id).val(valor);
			} catch (e) {

			}
		});
		if (form == "envia_post_filtro1") {

			id = "uf";
			$('input[name="uf_td"]:checked').each(function() {

				valor = valor + separador + $(this).val();
				separador = ";";
			});
			$("#relatorio_post").find("#" + id).val(valor);
		}

		$("#relatorio_post").find("#tipoImpressao").val(tipoImpressao);
		$("#relatorio_post").find("#procedure").val(procedure);
		$("#relatorio_post").find("#jasper").val(jasper);

		$("#relatorio_post").submit();
		$("#relatorio_post").html("");

	}
	$(function() {

		$(".boxRelatorios a").hover(function() {
			$(this).css("opacity", 1)
		}, function() {
			$(this).css("opacity", 0.5)
		});

		$("body").mouseup(function() {
			$(".gridComDif").hide(0);
		})

	})

	function mudaPaginaRelatorio(obj, filtro) {
		$(".boxRelatorios a").removeClass("ativo");
		$(obj).addClass("ativo");
		$(".filtro").find(".bFiltros").remove();
		$(".filtro").addClass("ativo").append($(filtro).clone());
		setDatapicker(filtro);
		montaCombo($(".filtro").find(".combo1"));
		montaCombo($(".filtro").find(".combo2"));
		alternaLinha();
	}
	function escondeFiltro(obj) {
		$(obj).closest(".filtro").removeClass("ativo");
		
	}

	function siwtchBox(obj, box) {
		$("*").removeClass("boxGridAtivo");
		$(box).slideToggle().closest("div").addClass("boxGridAtivo");
		alternaLinha(box);
		$(".gridComDif").not(box).hide(0);
	}

	function mostraQuant(objs, local) {
		$(local).text($(objs).not(".marcaTodos").size());
	}

	function marcaTodos(obj, objs) {
		if ($(obj).is(":checked")) {
			$(objs).attr("checked", true);
			mostraQuant($(objs).not(obj), $(obj).closest('.boxGrid').find(
					'.quaItem'))
		} else {
			$(objs).attr("checked", false);
			$(obj).closest('.boxGrid').find('.quaItem').text(0);
		}

	}
	function setFiltro3(jasper) {

		$('#flag_filtro3').val(jasper);

	}

	function chekarTodos(id, classe) {
		if ($(id).attr("checked")) {

			$('.filtro.ativo').find(classe).each(function() {
				$(this).attr("checked", true);
				selecionaUf($(this));

			}

			);

		} else {
			$('.filtro.ativo').find(classe).each(function() {
				$(this).attr("checked", false);
				selecionaUf($(this));

			});

		}
	}

	function selecionaUf(uf) {
		var antes = $("#ufselecionada").val();
		if ($(uf).attr("checked") == "checked") {
			$("#ufselecionada").val(antes + $(uf).val() + ";");

		} else {
			valorSubstituir = $(uf).val() + ";";
			$("#ufselecionada").val(antes.replace(valorSubstituir, ""));
		}
	}
	
	function setDatapicker(filtro){
		$(filtro).find('img.ui-datepicker-trigger').hide().append('body');
		$(filtro).find('.data').each(function(){
		 id =$(this).attr('id');
		  $("#"+id).removeClass('hasDatepicker');
		  
		   $("#"+id).datepicker({ 
		     dateFormat: 'dd/mm/yy', 
		     showOn: 'button', 
		     buttonImage: '../../painelcontrole/resources/imagem/images/bt_calendario.png', 
		     buttonImageOnly: true 
		   });



		});
		
		
		
	}
	<ADNTags:flushscript/>
</script>