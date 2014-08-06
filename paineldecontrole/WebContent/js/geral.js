function selecionaChec(obj) {
	if ($(obj).is(":checked")) {
		$(obj).closest(".checkbox").find(".bgCheckbox").addClass("checado");
	} else {
		$(obj).closest(".checkbox").find(".bgCheckbox").removeClass("checado");
	}
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
	
	if($("#localdasmensagens").size()==0)
	{
		$("body").prepend("<div id='localdasmensagens'></div>");
	}

	$("#localdasmensagens")
			.append(
					"<div  id='"
							+ nomediv
							+ "' class='"
							+ classe
							+ " ' style='display:none'><div><a href='#' onclick='return RemoveMensagem(\""
							+ nomediv
							+ "\");' style='float:right'>Fechar</a></div> "
							+ mensagem + "</div>");

	$("#" + nomediv).fadeIn(1000);
	$("#" + nomediv).delay(10000).fadeOut(1000, function() {
		$(this).remove();
	});

}

function RemoveMensagem(nome) {

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

function alternaLinha(tabela) {
	if (tabela) {
		$(tabela).find("tr").removeClass("linhaEscura");
		$(tabela).find("tr:visible:odd").addClass("linhaEscura");
		return;
	}
	$(".tabelaSistema").find("tr:visible:odd").removeClass("linhaEscura");
	$(".tabelaSistema").each(function(index, element) {
		$(this).find("tr:visible:odd").addClass("linhaEscura");
	});
}

function mudaPagina(_url) {
	mostraPreload(); 
	var request = $.ajax({
		url : "../../painelcontrole/" + _url + "/listar",
		type : "GET",
		data : null,
		dataType : "HTML"
	});
	request.done(function(msg) {
		removePreload(); 
		$("#conteudo").html(msg);
	});

	request.fail(function(jqXHR, textStatus) {
		removePreload(); 
		alert("Falha");
	});

}

function mostraPreload() {
	$("body").prepend(
			'<div class="bgPreload"></div>' + '<div class="imgPreload"></div>')
}

function removePreload() {
	$(".bgPreload").remove();
	$(".imgPreload").remove();
}

function montaCombo(combo) {

	if ($(combo).closest("label.combo").eq(0).size() != 0) {
		return;
	}
	var label = null;

	var combo = $(combo);

	if ($(combo).closest("label").eq(0).size() == 0) {
		label = '<label></label>'
		combo.wrap(label);
	}

	label = combo.closest("label").eq(0);
	label.addClass("combo");

	if (combo.attr("readonly") == "readonly") {
		label.append('<input type="text" readonly="readonly" name="'
				+ combo.attr("name") + '" value="'
				+ $.trim(combo.find("option:selected").text()) + '" />');
		combo.remove();
		return;
	}

	label.append('<div class="btn"><div class="textoValorCombo"></div></div>');

	label.attr("style", combo.attr("style"));

	label.find(".textoValorCombo").text(
			$.trim(combo.find("option:selected").text()));

	combo.removeAttr("style");

	combo.change(function() {
		label.find(".textoValorCombo").text(
				$.trim(combo.find("option:selected").text()));
	})

}

function AddSeparador(stringold, stringnew, separador) {

	if (stringold != '')
		stringold += separador + stringnew;
	else
		stringold += stringnew;

	return stringold;

}

function fnPesquisar(tabela, entity, mensagem) {

	if (!validaFiltro()) {
		return false;
	}

	var formData = getChave();

	$(tabela).html("");
	mostraPreload();
	$.ajax({
		type : 'post',
		data : formData,
		url : "../../painelcontrole/" + entity + "/pesquisar",
		dataType : 'json',
		success : function(data) {
			if (data.erro) {
				$(tabela).append(
						"<tr><td align='center'>" + mensagem + "</td></tr>");
				MensagemGrowl(data.msg_erro, "ERRO");
				removePreload();
			} else {

				if (data.dados != "") {
					$(tabela).html(data.dados);
					alternaLinha();
					removePreload();
				} else {
					$(tabela)
							.append(
									"<tr><td align='center'>" + mensagem
											+ "</td></tr>");
					removePreload();
				}
			}
		}

	});

}

function mostraFiltro(obj) {
	$(obj).closest(".filtro").addClass("ativo");
}

function escondeFiltro(obj) {
	$(obj).closest(".filtro").removeClass("ativo");
	
}

function formatarData(src){
	mask = "##/##/####";
	var i = src.value.length;
	var saida = mask.substring(0,1);
	var texto = mask.substring(i)
	if (texto.substring(0,1) != saida){
		src.value += texto.substring(0,1);
	}
}

var moeda = {
		/**
		* retiraFormatacao
		* Remove a formata��o de uma string de moeda e retorna um float
		* @param {Object} num
		*/
		desformatar: function(num){
		num = num.replace(".","");
		num = num.replace(",",".");
		return parseFloat(num);
		},
		/**
		* formatar
		* Deixar um valor float no formato monet�rio
		* @param {Object} num
		*/
		formatar: function(num){
		x = 0;
		if(num<0){
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
		ret = num;
		if (x == 1 && ret!='0,00') ret = ' � ' + ret;return ret;
		},

		/**
		* arredondar
		* @abstract Arredonda um valor quebrado para duas casas
		* decimais.
		* @param {Object} num
		*/
		arredondar: function(num){
		return Math.round(num*Math.pow(10,2))/Math.pow(10,2);
		}};




