/*function Salvar(formulario, lista, entity, qtdcolunas) {
	
	var formData = $("#" + formulario).serializeArray();
	var acao = $("#acao").val();
	
	
	if (!Valida(acao)) {
		return false;
	}
	
	
	$.post(acao, formData, function(data) {
	
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}
		
		var _crudtela = $("#crudtela");
		
		if ($(_crudtela).length > 0) {
			$(_crudtela).hide();
			$("#localcrud").show();
		} else {
			$("#popup_" + "crud" + entity).dialog("close");
		}
		
		fnPesquisar(lista, entity, qtdcolunas, "Dados não existentes!");

		MensagemGrowl(data.msg_erro, "SUCESSO");
		return true;
	}, 'json');
	
	return false;
};*/

function SalvarDetail(formulario, entitypai, entityfilho, id, qtdcolunas) {
	var formData = $("#" + formulario).serializeArray();
	var acao 	 = $("#" + formulario).find("#acaodetail").val();

	if (!Valida(acao)) {
		return false;
	}
	
	$.post(acao + "/" + entityfilho, formData, function(data) {

		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}

		$("#popup_" + entityfilho).dialog("close");
		
		MensagemGrowl(data.msg_erro, "SUCESSO");
		
		
		if (id != null && id != "" ) {
			fnPesquisarDetail(entityfilho.toLowerCase(), entitypai, entityfilho, id, qtdcolunas, "Dados não existentes!");
		}else{
			fnPesquisarDetailCrud(entityfilho, entitypai, entityfilho, qtdcolunas, "Dados não existentes!");
		}
		
		return true;
	}, 'json');

	return false;
};

function OpenFiltro(objeto, classe) {
	var filtro = $("#popup_filtro" + objeto);
	if ( $(filtro).html() == '' ) {
	  OpenPopUpRelatorio("filtro" + objeto, "FRMCRUD", 370, 410, classe + "/filtro" + objeto);
	} else {
		$("#popup_filtro" + objeto).dialog('open');
	}
}

function OpenPopUpRelatorio(filho, formulario, width, height, url) {
	//if ($("#popup_" + filho).html() == "") {
		SetPopUpRelatorio(filho, width, height);
	//}

	var formData = $("#" + formulario).serializeArray();

	var jqxhr = $.post("../../sva/" + url, formData,
			function(data) {
				$("#popup_" + filho).html(data);
				
				if ($("#popup_" + filho).find(":first-child").length > 0)
					$("#popup_" + filho).dialog('open');
					});
	}

/*******************************************************************************
 * Funcoes para a tela de popup detalhe com submit
 ******************************************************************************/

function OpenPopUpDetail(filho, formulario, id, width, height, entitypai,
		entityfilho, acao, idfilho) {

	if ($("#popup_" + filho).html() == "") {
		SetPopUp(filho, width, height, '', '');
	}
	if (idfilho != "")
		idfilho = "/" + idfilho;

	var formData = $("#" + formulario).serializeArray();

	var jqxhr = $.get("../" + entitypai + "/" + acao + "/" + entityfilho + "/"
			+ id + idfilho, function(data) {
		$("#popup_" + filho).html(data);
		if ($("#popup_" + filho).find(":first-child").length > 0)
			$("#popup_" + filho).dialog('open');
	});

}

function fn_PopUpDetail(entitypai, entityfilho, divpopup, formulario, width, height, acao) {
	
	$(".ui-dialog-titlebar").hide();
	$("#aguarde_mensagem").dialog('open');
	
	if ($("#popup_" + divpopup).html() == "") {
		fn_SetPopUp(divpopup, width, height);
	}
	//var formData = $(formulario).serializeArray();
	var formData = getChaveCrudtela(divpopup);
	var jqxhr = $.post("../" + entitypai + "/" + acao + "/" + entityfilho, formData, function(data) {
		$("#popup_" + divpopup).html(data);
		if ($("#popup_" + divpopup).find(":first-child").length > 0){
			$("#aguarde_mensagem").dialog('close');
			$("#localcrud").hide();
			$(".ui-dialog-titlebar").show();
			$("#popup_" + divpopup).dialog('open');
		}
	});

}

function fn_SetPopUp(divpopup, width, height) {
	
	$("#dialog:ui-dialog").dialog("destroy");
	$('#popup_' + divpopup).dialog({

		open : function() {
			 $("#ui-datepicker-div").css("z-index",
			 $(this).parents(".ui-dialog").css("z-index") + 1);
		},
		autoOpen : false,
		width : width,
		height : height,
		bgiframe : true,
		modal : true,
		zIndex : 999997
	});
}

/*
 * TelaCrud
 */
function fn_ShowTelaCRUD(formulario, entity, acao, ondeVaiRenderizar, dados, popup)
{
	mostraPreload();
	dados = getChaveCrud();
	$.ajax({
		type : 'post',
		data : dados,
		url : "/painelcontrole/" + entity + "/" + acao,
		success : function(retorno) {
			if (popup){
				$.fancybox(retorno);
				$(".fancybox-wrap").draggable();
				
				$("a.fancybox-close").live("click",function(){
					$(".autocomplete-suggestions").remove();	
				});
				removePreload();
				
			} else {
				$(".ajax").hide(1);
				$(".ajax." + ondeVaiRenderizar).show(1).html(retorno);
				removePreload();
			}

		}

	});
	return;
}
function fn_ShowTelaCRUD_AJAX(formulario, entity, acao) {
	var formData = getChaveCrud();

	$.ajax({
		  url:"../" + entity + "/" + acao, 
		  type: "POST", 
		  data: formData ,
		  success:function (data){ $("#localcrud").hide();
		  
			$("#crudtela").empty().html(data);
			$("#crudtela").show();
		  },
		  contentType: "text/plain; charset=ISO-8859-1" } );

	return false;
}

function Go(url) {
	window.location = url;
}

function AddSeparador(stringold, stringnew, separador) {

	if (stringold != '')
		stringold += separador + stringnew;
	else
		stringold += stringnew;

	return stringold;

}

function MontaChave(  objetojson ) 
{
   var chave = {};
   for (var key in objetojson ) {
       chave[objetojson[key].chave] = key;

   }
   
   return chave;

}


function CamposJoin(objetojson, posicao) {
	return objetojson[posicao].valor;
}

function fnPesquisarDetail(lista, entitypai, entityfilho, id, qtdcolunas, mensagem) {

	carregaLista("../../sva/" + entitypai + "/pesquisar" + "/" + entityfilho
			+ "/" + id, lista, qtdcolunas, mensagem);
}

function fnPesquisarDetailCrud(lista, entitypai, entityfilho, qtdcolunas, mensagem) {

	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	$("#dadoslista_" + lista.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista.toLowerCase());

	
	carregaListaDetail("../../sva/" + entitypai + "/pesquisar" + "/" + entityfilho, lista, qtdcolunas, mensagem );
}

/*function fnPesquisar(lista, entity, qtdcolunas, mensagem) {
	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	$("#dadoslista_" + lista.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista.toLowerCase());


	carregaLista("../../sva/" + entity + "/pesquisar", lista, qtdcolunas, mensagem);
}*/

function fnPesquisarTab(lista, entity, tab, qtdcolunas, mensagem) {
	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	$("#dadoslista_" + lista.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista.toLowerCase());

	carregaLista("../../sva/" + entity + "/pesquisar" + tab, lista, qtdcolunas, mensagem);
	
}

function carregaListaDetail(URL, lista, qtdcolunas, mensagem) {

	var formData = getChaveCrudtela();	
	var jsonvazio = [{ 'mensagem': mensagem, 'qtdcolunas': qtdcolunas}];
	
	$.post(URL, formData, function(data) {
		
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			jsonvazio = [{ 'mensagem': "Erro na consulta.", 'qtdcolunas': qtdcolunas}];
			$("#dadoslista_" + lista.toLowerCase()).empty();
			$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista.toLowerCase());
			return false;
		}else{
			$("#dadoslista_" + lista.toLowerCase()).empty();
			
			if (data.dados != ""){
				// preenche o vetor de chave associado ao Template 
				eval( "ch_" + lista.toLowerCase() + "= MontaChave(data.dados[0].join )");
				$("#tmpl_" + lista.toLowerCase()).tmpl(data.dados).appendTo("#dadoslista_" + lista.toLowerCase());

				jQuery(document).ready(function($)
						{
						  $('#thetable').tableScroll({height:320 , width:980});
						});
			}else{
				$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista.toLowerCase());
			}
		}	
		return false;
	}, 'json');

	return false;
}


function carregaLista(URL, lista, qtdcolunas, mensagem) {
	
	var formData = "";
	var _crudtela = $("#FRMCRUD");
	
	formData = getChave();
	
	var jsonvazio = [{ 'mensagem': mensagem, 'qtdcolunas': qtdcolunas}];
	$.post(URL, formData, function(data) {
		
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			jsonvazio = [{ 'mensagem': "Erro na consulta.", 'qtdcolunas': qtdcolunas}];
			$("#dadoslista_" + lista.toLowerCase()).empty();
			$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista.toLowerCase());
			return false;
		}else{
			$("#dadoslista_" + lista.toLowerCase()).empty();
			
			if (data.dados != ""){
				// preenche o vetor de chave associado ao Template 
				eval( "ch_" + lista.toLowerCase() + "= MontaChave(data.dados[0].join )");
				$("#tmpl_" + lista.toLowerCase()).tmpl(data.dados).appendTo("#dadoslista_" + lista.toLowerCase());

				jQuery(document).ready(function($)
						{
						  $('#thetable').tableScroll({height:320 , width:980});
						});
			}else{
				$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista.toLowerCase());
			}
		}	
		return false;
	}, 'json');

	return false;
}

function Excluir(id, lista, entity, qtdcolunas) {
	var acao = "excluir";
	
	$.get(acao + "/" + id, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}
		MensagemGrowl(data.msg_erro, "SUCESSO");
		fnPesquisar(lista, entity, qtdcolunas, "Dados não existentes!");
		$("#dialog-confirm").dialog('close');
		return true;
	}, 'json');

	return false;
};

function PerguntaExcluir(id, lista, entity, qtdcolunas) {

	$("#dialog:ui-dialog").dialog("destroy");

	$("#dialog-confirm").html("<br><br><p>Deseja realmente apagar o registro?</p>");
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : false,
		height : 200,
		modal : true,
		buttons : {
			"OK" : function() {
				Excluir(id, lista, entity, qtdcolunas);
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-confirm").dialog('open');
}

function ShowTelaCRUD(formulario, id, entity) {
	
	var formData = $("#" + formulario).serializeArray();
	$(".ui-dialog-titlebar").hide();
	$("#aguarde_mensagem").dialog('open');
	
	if (id != "") {
		var jqxhr = $.get("../" + entity + "/editar/" + id, function(data) {
			
			$(".ui-dialog-titlebar").hide();
			$("#aguarde_mensagem").dialog('close');
			$("#localcrud").hide();
			$(".ui-dialog-titlebar").show();

			$("#crudtela").empty().html(data);
			$("#crudtela").show();
		});
	} else {
		var jqxhr = $.post("../" + entity + "/inserir", formData,
				function(data) {
			$(".ui-dialog-titlebar").show();
			$("#aguarde_mensagem").dialog('close');

			
					$("#localcrud").hide();
					$(".ui-dialog-titlebar").show();

					$("#crudtela").empty().html(data);
					$("#crudtela").show();

				});
	}

	return false;

}

function OpenPopUp(filho, formulario, id, width, height, lista, entity) {
	
	$(".ui-dialog-titlebar").hide();
	$("#aguarde_mensagem").dialog('open');

	if ($("#popup_" + filho).html() == "") {
		SetPopUp(filho, width, height, lista, entity);
	}

	var formData = $("#" + formulario).serializeArray();

	if (id != "") {
		var jqxhr = $.get("../" + entity + "/editar/" + id, function(data) {
			
			$(".ui-dialog-titlebar").show();
			$("#aguarde_mensagem").dialog('close');
			$(".ui-dialog-titlebar").show();

			$("#popup_" + filho).html(data);
			if ($("#popup_" + filho).find(":first-child").length > 0)
				$("#popup_" + filho).dialog('open');
		});
	} else {
		var jqxhr = $.post("../" + entity + "/inserir", formData,
				function(data) {
			
					$("#aguarde_mensagem").dialog('close');
					$(".ui-dialog-titlebar").show();
					$("#popup_" + filho).html(data);
					if ($("#popup_" + filho).find(":first-child").length > 0)
						$("#popup_" + filho).dialog('open');
				});
	}
}

function OpenPopUpPost(filho, formulario, width, height, lista, entity, action) {
	if ($("#popup_" + filho).html() == "") {
		SetPopUp(filho, width, height, lista, entity);
	}

	var formData = getChave();

	var jqxhr = $.post("../" + entity + "/" + action, formData,
			function(data) {
				$("#popup_" + filho).html(data);
				if ($("#popup_" + filho).find(":first-child").length > 0)
					$("#popup_" + filho).dialog('open');
			});	
}

function SetPopUp(filho, width, height, lista, entity) {
	$("#dialog:ui-dialog").dialog("destroy");
	$('#popup_' + filho).dialog({

		open : function() {
			// $("#ui-datepicker-div").css("z-index",
			// $(this).parents(".ui-dialog").css("z-index") + 1);
		},
		autoOpen : false,
		width : width,
		height : height,
		bgiframe : true,
		modal : true,
		zIndex : 999997
	});
}

function SetPopUpRelatorio(filho, width, height) {
//	$("#popup_" + filho + ":ui-dialog").dialog("destroy");
	$('#popup_' + filho).dialog({

		open : function() {
			
		},
		autoOpen : false,
		width : width,
		height : height,
		bgiframe : true,
		modal : true,
		zIndex : 999999
	});
}

function guidGenerator() {
	var S4 = function() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	};
	return (S4() + S4() + "" + S4() + "" + S4() + "" + S4() + "" + S4() + S4() + S4());
}

function ShowMensagem(mensagem, classe) {
	
	
	
	var id = guidGenerator();
	nomediv = 'msgerro_' + id;

		
$("#localdasmensagens").append(
			"<div  id='" + nomediv + "' class='" + classe
					+ " ' style='display:none'><div><a href='#' onclick='return RemoveMensagem(\""+nomediv+"\");' style='float:right'>Fechar</a></div> " + mensagem + "</div>");
					
					

	$("#" + nomediv).fadeIn(1000);
	$("#" + nomediv).delay(10000).fadeOut(1000, function() {
		$(this).remove();
	});

}

function RemoveMensagem( nome ) {

	
	$("#" + nome).remove();
	return false;
}

function MensagemGrowl(Mensagem, tipomsg) {

	var classe = "";

	if (tipomsg == "ERRO")
		classe = "warning-box";
	else if (tipomsg == "VALIDACAO")
		classe = "note-box";
	else
		classe = "download-box";

	ShowMensagem(Mensagem, classe);

}

function fn_VoltarCRUD() {
	$("#crudtela").empty();
	$("#crudtela").hide();
	$("#localcrud").show();
	
}

function PerguntaExcluirDetail(id, idfilho, lista, entitypai, entityfilho, qtdcolunas) {
	
	$("#dialog:ui-dialog").dialog("destroy");

	$("#dialog-confirm").html("<br><br><p>Deseja realmente apagar o registro?</p>");
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : false,
		height : 200,
		modal : true,
		buttons : {
			"OK" : function() {
				ExcluirDetail(id, idfilho, lista, entitypai, entityfilho, qtdcolunas);
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-confirm").dialog('open');
}

function ExcluirDetail(id, idfilho, lista, entitypai, entityfilho, qtdcolunas) {
	
	var acao = "excluir";
	
	$.get(acao + "/" + entityfilho + "/" + id + "/" + idfilho, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}
		
		MensagemGrowl(data.msg_erro, "SUCESSO");
		
		//fnPesquisarDetail(entityfilho.toLowerCase(), entitypai, entityfilho, id, qtdcolunas, "Dados não existentes!");
		fnPesquisarDetailCrud(lista, entitypai, entityfilho.toLowerCase(), qtdcolunas, "Dados não existentes!");
		
		$("#dialog-confirm").dialog('close');
		return true;
	}, 'json');

	return false;
};

function PerguntaExcluirDetalhe(lista, entitypai, entityfilho, qtdcolunas, menssagem ) {

	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-confirm").html("<br><br><p>Deseja realmente apagar o registro?</p>");
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : false,
		height : 200,
		modal : true,
		buttons : {
			"OK" : function() {
				ExcluirDetalhe(lista, entitypai, entityfilho, qtdcolunas, menssagem);
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-confirm").dialog('open');
}

function ExcluirDetalhe(lista, entitypai, entityfilho, qtdcolunas, menssagem) {
	
	var acao = "excluir";
	
	var formData = getChaveCrudtela(lista);
	
	$.post(acao + "/" + entityfilho, formData, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}

		MensagemGrowl(data.msg_erro, "SUCESSO");
		
		fnPesquisarDetailCrud(entityfilho, entitypai, entityfilho, qtdcolunas, menssagem);
		
		$("#dialog-confirm").dialog('close');
		
		return true;
	}, 'json');
	
	return false;
};

function PerguntaExcluirIndexPost(lista, entity, qtdcolunas, menssagem) {

	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-confirm").html("<br><br><p>Deseja realmente apagar o registro?</p>");
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : false,
		height : 200,
		modal : true,
		buttons : {
			"OK" : function() {
				ExcluirIndexPost(lista, entity, qtdcolunas, menssagem);
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-confirm").dialog('open');
}

/*function ExcluirIndexPost(lista, entity, qtdcolunas, menssagem) {
	
	var acao = "excluir";
	
	var formData = getChaveExcluir();
	
	$.post("../" + entity + "/" + acao, formData, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}
		MensagemGrowl(data.msg_erro, "SUCESSO");
		fnPesquisar(lista, entity, qtdcolunas, menssagem);
		
		$("#dialog-confirm").dialog('close');
		
		return true;
	}, 'json');
	
	return false;
};*/

function PerguntaExcluirConciliacaoPost(lista, entity, qtdcolunas, menssagem) {

	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-confirm").html("<br><br><p>Deseja realmente apagar o registro?</p>");
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : false,
		height : 200,
		modal : true,
		buttons : {
			"OK" : function() {
				ExcluirConciliacaoPost(lista, entity, qtdcolunas, menssagem);
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-confirm").dialog('open');
}

function ExcluirConciliacaoPost(lista, entity, qtdcolunas, menssagem) {
	
	var acao = "excluir";
	
	var formData = getChaveExcluir();
	
	$.post("../" + entity + "/" + acao, formData, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
		}else{
			MensagemGrowl(data.msg_erro, "SUCESSO");
			fnPesquisarConciliacao('conciliar_esquerda','conciliar_direita','conciliar_esquerda', 5, 'Dados n�o existentes!');
		}
		$("#dialog-confirm").dialog('close');
		
		return true;
	}, 'json');
	
	return false;
};

function PerguntaExcluirLista(lista, entitypai, qtdcolunas, mensagem) {

	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-confirm").html("<br><br><p>Deseja realmente apagar o registro?</p>");
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : false,
		height : 200,
		modal : true,
		buttons : {
			"OK" : function() {
				ExcluirLista(lista, entitypai, qtdcolunas, mensagem);
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-confirm").dialog('open');
}

function ExcluirLista(lista, entitypai, qtdcolunas, mensagem) {
	
	var acao = "excluir";
	
	var formData = getChaveCrud();
	
	$.post("../" + entitypai + "/" + acao, formData, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}

		MensagemGrowl(data.msg_erro, "SUCESSO");
		
		fnPesquisar(lista, entitypai, qtdcolunas, mensagem);
		
		$("#dialog-confirm").dialog('close');
		
		return true;
	}, 'json');
	
	return false;
};

//Formatar o valor para o formato monet�rio
function float2moeda(num) {

x = 0;

if(num<0) {
   num = Math.abs(num);
   x = 1;
}
if(isNaN(num)) num = "0";
   cents = Math.floor((num*100+0.5)%100);

num = Math.floor((num*100+0.5)/100).toString();

if(cents < 10) cents = "0" + cents;
   for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
      num = num.substring(0,num.length-(4*i+3))+'.'
            +num.substring(num.length-(4*i+3));
            ret = num + ',' + cents;
            if (x == 1) ret = ' - ' + ret; return ret;

}

function moeda(z){ 
	v = z.value; 
	v=v.replace(/\D/g,""); // permite digitar apenas numero 
	v=v.replace(/(\d{1})(\d{14})$/,"$1.$2"); // coloca ponto antes dos ultimos digitos 
	v=v.replace(/(\d{1})(\d{11})$/,"$1.$2"); // coloca ponto antes dos ultimos 11 digitos 
	v=v.replace(/(\d{1})(\d{8})$/,"$1.$2"); // coloca ponto antes dos ultimos 8 digitos 
	v=v.replace(/(\d{1})(\d{5})$/,"$1.$2"); // coloca ponto antes dos ultimos 5 digitos 
	v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2"); // coloca virgula antes dos ultimos 2 digitos 
	z.value = v; 
}

function GetRowClass(classe1, classe2) {
	TelaRowId++;
	var rowClass = TelaRowId % 2 == 0 ? classe1 : classe2;

	return rowClass;
}

function mascara(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout("execmascara()", 1);
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value);
}

function Data(v) {
	v = v.replace(/\D/g, "");
	//v=v.replace(/(\d{2})(\d)/,"$1/$2"); 
	v = v.replace(/(\d{2})(\d)/, "$1/$2");
	return v;
}

function mdata(v){
    v=v.replace(/\D/g,"");                    //Remove tudo o que não é dígito
    v=v.replace(/(\d{2})(\d)/,"$1/$2");       
    v=v.replace(/(\d{2})(\d)/,"$1/$2");       
                                             
    v=v.replace(/(\d{2})(\d{2})$/,"$1$2");
    return v;
}

function GetStatus(status) {

	switch (status) {
	case "01":
		 return 'label l-notice';
	case "02":
		 return 'label l-success';		
	case "03":
		 return 'label l-important';
	case "08":
		 return 'label l-medium';
	case "09":
		 return 'label l-pending';
	case "99":
		 return 'label l-suspend';
	default:
		 return 'label l-default';
	}
}

/***********************************************************************
* 
 * Chama relat�rio
* 
 ***********************************************************************/

function carregaRelatorio(classe, URL, lista) {

       var formData = getParametro();
       
       $.post(classe + "/" + URL, formData, function(data) {
             if (data.erro) {
                    MensagemGrowl(data.msg_erro, "ERRO");
                    return false;
             }
             $("#dadoslista_" + lista.toLowerCase()).empty();
             $("#tmpl_" + lista.toLowerCase()).tmpl(data.dados).appendTo(
                           "#dadoslista_" + lista.toLowerCase());
             return false;
       }, 'json');

       return false;
}

function fnPesquisarConciliacao(lista_esquerda, lista_direita, entity, qtdcolunas, mensagem) {
	
	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	$("#dadoslista_" + lista_esquerda.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_esquerda.toLowerCase());
	
	$("#dadoslista_" + lista_direita.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_direita.toLowerCase());
	
	carregaListaConciliacao("../../sva/" + entity + "/pesquisar", lista_esquerda, lista_direita, qtdcolunas, mensagem);
}

function carregaListaConciliacao(URL, lista_esquerda, lista_direita, qtdcolunas, mensagem) {
	
	var formData = getChaveCrudtela();
	
	var jsonvazio = [{ 'mensagem': mensagem, 'qtdcolunas': qtdcolunas}];
	
	
	$.post(URL, formData, function(data) {
			
			$("#dadoslista_" + lista_esquerda.toLowerCase()).empty();
			$("#dadoslista_" + lista_direita.toLowerCase()).empty();
			
			if (data.dados != ""){
				
				$("#tmpl_" + lista_esquerda.toLowerCase()).tmpl(data.dados[0]).appendTo("#dadoslista_" + lista_esquerda.toLowerCase());
				$("#tmpl_" + lista_direita.toLowerCase()).tmpl(data.dados[1]).appendTo("#dadoslista_" + lista_direita.toLowerCase());
				$("#data_conciliacao").val(data.dados[2]);
				
				

			}else{
				
				$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista_esquerda.toLowerCase());
				$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista_direita.toLowerCase());
				
			}
			if (data.erro) {
				MensagemGrowl(data.msg_erro, "ERRO");
			}
			
			$("#extrato_selecionado").val("");
			$("#lancamento_selecionado").val("");
		return false;
	}, 'json');

	return false;
}

function fnConciliacaoAutomatica(lista_esquerda, lista_direita, entity, qtdcolunas, mensagem) {
	
	if (!verificaStatusConciliacao('automatica')){
		return false;
	}
	
	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	$("#dadoslista_" + lista_esquerda.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_esquerda.toLowerCase());
	
	$("#dadoslista_" + lista_direita.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_direita.toLowerCase());
	
	carregaListaConciliacao("../../sva/" + entity + "/automatica", lista_esquerda, lista_direita, qtdcolunas, mensagem);
}

function fnConciliacaoManual(lista_esquerda, lista_direita, entity, qtdcolunas, mensagem) {
	
	if (!verificaStatusConciliacao('manual')){
		return false;
	}
	
	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	$("#dadoslista_" + lista_esquerda.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_esquerda.toLowerCase());
	
	$("#dadoslista_" + lista_direita.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_direita.toLowerCase());
	
	carregaListaConciliacao("../../sva/" + entity + "/manual", lista_esquerda, lista_direita, qtdcolunas, mensagem);
}

function fnConciliacaoCancelamento(lista_esquerda, lista_direita, entity, qtdcolunas, mensagem) {
	
	if (!verificaStatusConciliacao('cancelamento')){
		return false;
	}
	
	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	$("#dadoslista_" + lista_esquerda.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_esquerda.toLowerCase());
	
	$("#dadoslista_" + lista_direita.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista_direita.toLowerCase());
	
	carregaListaConciliacao("../../sva/" + entity + "/cancelamento", lista_esquerda, lista_direita, qtdcolunas, mensagem);
//	carregaListaConciliacao("../../sva/" + entity + "/pesquisar", lista_esquerda, lista_direita, qtdcolunas, mensagem);
}

function ValidaDate(data1, data2){

	Data1 = new Date(data1.val().substr(6, 4), data1.val().substr(3, 2) - 1, data1.val().substr(0, 2));
    Data2 = new Date(data2.val().substr(6, 4), data2.val().substr(3, 2) - 1, data2.val().substr(0, 2));

    var tempo = Math.ceil((Data2.getTime() - Data1.getTime()) / 1000 / 60 / 60 / 24);

    if (tempo <= 0) {
        $(data2).addClass("errovalida");
        $(data1).addClass("errovalida");
        return false;
    }
    else{
    	$(data2).removeClass();
        $(data1).removeClass();
        return true;
    }  
}

function ConvertJsonToData(datetime) {
    if (datetime != null) {
        var jsonWithDates = datetime.replace('/Date(', 'new Date(');
        jsonWithDates = jsonWithDates.replace(')\/', ')');
        jsonWithDates = jsonWithDates.replace('-0300', '-0000');

        var data = eval(jsonWithDates);

        return data.getDateBR();
    }
    else {
        return "";
    }
}

function fn_PopUpDetailConciliacao(entitypai, entityfilho, divpopup, formulario, width, height, acao) {
	
	if (!verificaStatusConciliacao(entitypai)){
		return false;
	}
	
	$(".ui-dialog-titlebar").hide();
	$("#aguarde_mensagem").dialog('open');
	
	if ($("#popup_" + divpopup).html() == "") {
		fn_SetPopUp(divpopup, width, height);
	}
	//var formData = $(formulario).serializeArray();
	var formData = getChaveCrudtela(divpopup);
	var jqxhr = $.post("../" + entitypai + "/" + acao + "/" + entityfilho, formData, function(data) {
		$("#popup_" + divpopup).html(data);
		if ($("#popup_" + divpopup).find(":first-child").length > 0){
			$("#aguarde_mensagem").dialog('close');
			$("#localcrud").hide();
			$(".ui-dialog-titlebar").show();
			$("#popup_" + divpopup).dialog('open');
		}
	});

}

function fn_PopUpTemplateConciliacao(entity, divpopup, width, height, acao) {
	
	if (!verificaStatusConciliacao(entity)){
		return false;
	}
	
	$(".ui-dialog-titlebar").hide();
	$("#aguarde_mensagem").dialog('open');
	
	if ($("#popup_" + divpopup).html() == "") {
		fn_SetPopUp(divpopup, width, height);
	}
	var formData = getChaveCrudtela(divpopup);
	var jqxhr = $.post("../" + entity + "/" + acao + "/", formData, function(data) {
		$("#popup_" + divpopup).html(data);
		if ($("#popup_" + divpopup).find(":first-child").length > 0){
			$("#aguarde_mensagem").dialog('close');
			$("#localcrud").hide();
			$(".ui-dialog-titlebar").show();
			$("#popup_" + divpopup).dialog('open');
		}
	});

}

function fn_PopUpPesquisaTemplateConciliacao(lista, entity, qtdcolunas, mensagem) {
	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

	
	$("#dadoslista_" + lista.toLowerCase()).empty();
	$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista.toLowerCase());


	fn_PopUpCarregaTemplateConciliacao("../../sva/" + entity + "/pesquisar", lista, qtdcolunas, mensagem);
}

function fn_PopUpCarregaTemplateConciliacao(URL, lista, qtdcolunas, mensagem) {

	var formData = getChaveCrudtela();
	
	var jsonvazio = [{ 'mensagem': mensagem, 'qtdcolunas': qtdcolunas}];
	
	
	$.post(URL, formData, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}else{
			$("#dadoslista_" + lista.toLowerCase()).empty();
			
			if (data.dados != ""){
				
				// preenche o vetor de chave associado ao Template 
//				eval( "ch_" + lista.toLowerCase() + "= MontaChave(data.dados[0].join )");
				$("#tmpl_" + lista.toLowerCase()).tmpl(data.dados).appendTo("#dadoslista_" + lista.toLowerCase());

//				jQuery(document).ready(function($)
//						{
//						  $('#thetable').tableScroll({height:320 , width:980});
//						});
//				$("#linha_1").hide();
			}
			else{
				$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista.toLowerCase());
				
			}
		}	
		return false;
	}, 'json');

	return false;
}



function PerguntaExcluirTemplateConciliacao(id, lista, entity, qtdcolunas) {
	$("#dialog:ui-dialog").dialog("destroy");

	$("#dialog-confirm").html("<br><br><p>Deseja realmente apagar o registro?</p>");
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : false,
		height : 200,
		modal : true,
		buttons : {
			"OK" : function() {
				ExcluirTemplateConciliacao(id, lista, entity, qtdcolunas);
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-confirm").dialog('open');
}

function ExcluirTemplateConciliacao(id, lista, entity, qtdcolunas) {
	var acao = "excluir";
	
	$.get(acao + "/" + id, function(data) {
		if (data.erro) {
			MensagemGrowl(data.msg_erro, "ERRO");
			return false;
		}
		MensagemGrowl(data.msg_erro, "SUCESSO");
		fn_PopUpPesquisaTemplateConciliacao(lista, entity, qtdcolunas, "Carregando dados...");
		$("#dialog-confirm").dialog('close');
		return true;
	}, 'json');

	return false;
};

function SalvarTemplateConciliacao(formulario, entitypai, entityfilho, id, qtdcolunas, divpopup) {
		var formData = $("#" + formulario).serializeArray();
		var acao = $("#" + formulario).find("#acaodetail").val();

		if (!Valida(acao)) {
			return false;
		}

		
		$.post(acao + "/" + entityfilho, formData, function(data) {

			if (data.erro) {
				MensagemGrowl(data.msg_erro, "ERRO");
				return false;
			}
			$("#popup_" + divpopup).dialog("close");

			MensagemGrowl(data.msg_erro, "SUCESSO");
			
			fn_PopUpPesquisaTemplateConciliacao(entityfilho, entityfilho, qtdcolunas, "Carregando dados...");
			
			return true;
		}, 'json');

		return false;
	};
	
	
	function onlyNumbers(e){
        var keybottom=(window.event)?event.keyCode:e.which;  
        if((keybottom>47 && keybottom<58))
            return true;
        else
        {
            if (keybottom==8 || keybottom==0) 
                 return true;
            else  
                 return false;
        }
    };
    
    
    
    function fnPesquisarRobo(lista, entity, qtdcolunas, mensagem, funcaoposcargarobo) {
    	var jsoncarre = [{ 'mensagem': 'Carregando dados...', 'qtdcolunas': qtdcolunas}];

    	//$("#dadoslista_" + lista.toLowerCase()).empty();
    	//$("#tmplvazio").tmpl(jsoncarre).appendTo("#dadoslista_" + lista.toLowerCase());


    	carregaListaRobo("../../sva/" + entity + "/pesquisar", lista, qtdcolunas, mensagem, funcaoposcargarobo);
    }
    
    function carregaListaRobo(URL, lista, qtdcolunas, mensagem, funcaoposcargarobo) {
    	
    	var formData = "";
    	var _crudtela = $("#FRMCRUD");
    	
    	formData = getChave();
    	
    	var jsonvazio = [{ 'mensagem': mensagem, 'qtdcolunas': qtdcolunas}];
    	$.post(URL, formData, function(data) {
    		
    		if (data.erro) {
    			MensagemGrowl(data.msg_erro, "ERRO");
    			jsonvazio = [{ 'mensagem': "Erro na consulta.", 'qtdcolunas': qtdcolunas}];
    			//$("#dadoslista_" + lista.toLowerCase()).empty();
    			$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista.toLowerCase());
    			return false;
    		}else{
    		//	$("#dadoslista_" + lista.toLowerCase()).empty();
    			
    			if (data.dados != ""){
    				// preenche o vetor de chave associado ao Template 
    				eval( "ch_" + lista.toLowerCase() + "= MontaChave(data.dados[0].join )");
    				$("#tmpl_" + lista.toLowerCase()).tmpl(data.dados).appendTo("#dadoslista_" + lista.toLowerCase());

    				jQuery(document).ready(function($)
    						{
    						  $('#thetable').tableScroll({height:320 , width:980});
    						});
    			}else{
    				//$("#tmplvazio").tmpl(jsonvazio).appendTo("#dadoslista_" + lista.toLowerCase());
    			}
    		}
    		
    		eval( funcaoposcargarobo );
    		return false;
    	}, 'json');

    	return false;
    }

