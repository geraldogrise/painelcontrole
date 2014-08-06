function confirma(frase,funcao)
{
	var confirma=Object();	
	confirma.html='<div class="popup">';
	confirma.html+='<div class="popupDentro">';
	confirma.html+='<div class="tituloPopup">';
	confirma.html+='<span>Confirme</span>';
	confirma.html+='</div>';
	confirma.html+='<div class="areaCampos">';
	confirma.html+='<fieldset style="padding:5px">';
	confirma.html+='<legend style="font-size:14px;">'+frase+'</legend>';
	confirma.html+='<div class="bt_padrao_100">';
	confirma.html+='<a href="#" class="confirmaConfirma"><img height="9" border="0" src="../resources/imagem/images/aprovado.png">Confirmar</a>';
	confirma.html+='</div>';
	confirma.html+='<div class="bt_padrao_100" style="margin:0 0 0 5px">';
	confirma.html+='<a href="#" class="cancelConfirma"><img height="9" border="0" src="../resources/imagem/images/cancelar.png">Cancelar</a>';
	confirma.html+='</div>';
	confirma.html+='</fieldset>';
	confirma.html+='</div>';
	confirma.html+='</div>';
	confirma.html+='</div>';
	
	$.fancybox(confirma.html);
	$( ".fancybox-wrap").draggable();
	
	$(".confirmaConfirma").on("click",function()
	{
		$.fancybox.close(confirma.html);
		eval(funcao);
		return;	
	})
	$(".cancelConfirma").live("click",function()
	{
		$.fancybox.close(confirma.html);
		setId('');
		return
	})
}
