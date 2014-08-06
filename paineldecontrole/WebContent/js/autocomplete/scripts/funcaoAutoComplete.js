
function complete(obj,lista)
{
	var guardaObj=$(obj).attr("id");
	var getId="get_id_"+$(obj).attr("id");
	$(obj).after('<input type="hidden" name="'+getId+'" id="'+getId+'" />');
	var resultadoDaBusca = $.map(lista, function (valor, chave) {
	 if($(obj).val()!="" && $(obj).val()==chave)
	 {
		 $("#"+getId).val(chave);
		 $(obj).val(valor);
	 }
	   return { value: valor, data: chave }; }),
            countries = $.map(lista, function (valor) { return valor; });
        // Initialize autocomplete with local lookup:
        $(obj).autocomplete({
            lookup: resultadoDaBusca,
			minChars: 1,
            onSelect: function (suggestion){
				$("#get_id_"+$(obj).attr("id")).val(suggestion.data);
            }
        });
        
};

