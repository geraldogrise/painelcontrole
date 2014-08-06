<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Painel de Controle</title>
<link href="../../painelcontrole/resources/css/geral.css" 
	rel="stylesheet" type="text/css" />
<link href="../../painelcontrole/resources/css/btns.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="../../painelcontrole/resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="../../painelcontrole/resources/js/geral.js"></script>
<script type="text/javascript"
	src="../../painelcontrole/resources/js/util.js"></script>
<!-- styles needed by jScrollPane -->
<link type="text/css"
	href="../../painelcontrole/resources/css/jquery.jscrollpane.css"
	rel="stylesheet" media="all" />

<link href="../../painelcontrole/resources/css/layout.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="../../painelcontrole/resources/styles/ui-lightness/jquery-ui-1.8.17.custom.css" rel="stylesheet" media="all" />

<link href="../../painelcontrole/resources/styles/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" media="all" />
	
<script type="text/javascript" src="../../painelcontrole/resources/js/jquery-ui-1.9.2.custom.js"/></script>
<script type="text/javascript" src="../../painelcontrole/resources/js/jquery-ui-1.8.17.custom.min.js" /></script>
<script type="text/javascript" src="../../painelcontrole/resources/js/jquery-ui-1.8.18.custom.min.js" /></script>
	

<!-- the mousewheel plugin - optional to provide mousewheel support -->
<script type="text/javascript"
	src="../../painelcontrole/resources/js/jquery.mousewheel.js"></script>

<!-- the jScrollPane script -->
<script type="text/javascript"
	src="../../painelcontrole/resources/js/jquery.jscrollpane.min.js"></script>



<!-- INSTRUMENTOS -->
<script
	src="../../painelcontrole/resources/js/highcharts-4.0.1/js/highcharts.js"></script>
<script
	src="../../painelcontrole/resources/js/highcharts-4.0.1/js/modules/data.js"></script>
<script
	src="../../painelcontrole/resources/js/highcharts-4.0.1/js/modules/drilldown.js"></script>

<script src="../../painelcontrole/resources/js/highcharts-4.0.1/js/highcharts-more.js"></script>
<script src="../../painelcontrole/resources/js/highcharts-4.0.1/js/modules/solid-gauge.src.js"></script>
<resources mapping="/resources/**" location="/resources/" />



<script type="text/javascript">
	$(function() {
		mudaPagina("dashboard");
		$("#ladoEsq").hover(function()
				{
				
					$(this).find(".conteudoLadoEsq").stop().animate({
						left:0
						},500,function(){
								
								$("#ladoEsq").addClass("show");
							});
				},function(){
					
					
						$(this).find(".conteudoLadoEsq").stop().animate({
						left:-1000
						},500,function(){
							$("#ladoEsq").removeClass("show");
							});
						
					});
	});
	
	
</script>

<style>
/*** ORATOR ***/




</style>
</head>
<body>

   <div id="localhiden">
       <input type='hidden' id='nome_lote' value=''/>
       <input type='hidden' id='data_inicial_lote' value=''/>
       <input type='hidden' id='data_final_lote' value=''/>
       <input type='hidden' id='tipoterminal_lote' value=''/>
       <input type='hidden' id='nomeArquivo_lote' value=''/>
       
   
   </div>

	<div id="localdasmensagens" class="localmsg-box"></div>
	<div id="topo">
		<div id="topoSite">
			<a href="#" id="logoTopo"><img
				src="../../painelcontrole/resources/images/logoTopo.png" /> </a>

			<div id="bemVindo">
				<span style="font-family:orator, Arial, sans-serif;">Herberte Gon�alves</span>
				<div class="limpa"></div>
				<a title="Sair do Sistema" class="sairSistema" href="index.html">Sair
					do Sistema</a>
				<div class="limpa"></div>
			</div>
			<div class="nomeSistema">
				<div>
					<span>Painel de Controle</span>
				</div>
			</div>

		</div>
	</div>
<div id="ladoEsq">
      <span class="iconMenu">F</span>
	<div class="conteudoLadoEsq">
		<div id="menuEsq">
			
		
			
			<div class="item ativo primeiro">
				<span>Dashboard</span> <a><img
					src="../../painelcontrole/resources/images/dash.png"
					onclick="mudaPagina('dashboard');"  height="70" width="160" /> </a>
			</div>


			<div class="item">
				<span>Gr�ficos</span> <a><img
					src="../../painelcontrole/resources/images/graficos.jpg"
					onclick="mudaPagina('grafico');" height="70" width="160"  />
				</a>
			</div>


			<div class="item">
				<span>Monitoramento dos Lotes</span> <a><img
					src="../../painelcontrole/resources/images/grid1.jpg"
					onclick="mudaPagina('monitoramento');" height="70" width="160"  /> </a>
			</div>

			<div class="item">
				<span>Fila BFM / ICFBF</span> <a><img
					src="../../painelcontrole/resources/images/grid2.jpg"
					onclick="mudaPagina('filabfmicfbf');" height="70" width="160"  /> </a>
			</div>

			<div class="item">
				<span>Lote X Arquivos</span> <a><img
					src="../../painelcontrole/resources/images/grid1.jpg"
					onclick="mudaPagina('lotexarquivo');"  height="70" width="160" /> </a>
			</div>

			<div class="item">
				<span>Relat�rios</span> <a><img
					src="../../painelcontrole/resources/images/relatorios1.jpg"
					onclick="mudaPagina('relatorio');" height="70" width="160"  /> </a>
			</div>


		</div>
	</div>
	
</div>
	<div id="conteudo"></div>
</body>
</html>

<script>
function LimparDados(){
	
    dataInicial = "";
    dataFinal = "";
    tipoterminal = "";
    
    $("#data_inicial_lote").val(dataInicial);
    $("#data_final_lote").val(dataFinal);
    $("#tipoterminal_lote").val(tipoterminal);
    $("#nome_lote").val(nomeLote);
 }


</script>


