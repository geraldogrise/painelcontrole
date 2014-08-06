function Trim(str) {
    try {
        if (typeof (str) != 'undefined')
            return str.replace(/^\s+|\s+$/g, "");
    }
    catch (ex) { }

}

String.prototype.GetMsgValidator = function(texto) {

    if (this == "")
        return  texto ;
    else
        return this + "<li>" + texto + "</li>";
  
};

(function($) {
    $.fn.Validacao = function(options) {

        var opts = $.extend({}, $.fn.Validacao.defaults, options);
    };

    // plugin defaults
    $.fn.Validacao.defaults = {
        Mensagem: '',
        isValido: true
    };

    $.fn.Validacao.clear = function() {
        $.fn.Validacao.defaults.Mensagem = '';
        $.fn.Validacao.defaults.isValido = true;
    };


    $.fn.Validacao.isValido = function() {
        return $.fn.Validacao.defaults.isValido;
    };

    $.fn.Validacao.getMensagem = function() {
        return $.fn.Validacao.defaults.Mensagem;
    };

    $.fn.Validacao.setMensagem = function(valor) {
    $.fn.Validacao.defaults.Mensagem = $.fn.Validacao.defaults.Mensagem.GetMsgValidator(valor);
         $.fn.Validacao.defaults.isValido = false;
    };



})(jQuery);


jQuery.fn.extend({

    ValidaCampo_isEmpty: function(valorvazio, mensagem) {

        if (Trim(this.attr("value")) == valorvazio) {
            $(window).Validacao.setMensagem(mensagem);
            this.addClass("errorbox");
        }
        else {
        	 this.removeClass("errorbox");
        }
    },

    ValidaCampo_isZero: function(mensagem) {
        var valor = Trim(this.attr("value"));
        if (valor != "")
            valor = parseFloat(valor);

        if (valor == 0) {
            $(window).Validacao.setMensagem(mensagem);
            //this.css("background", "red");
            //this.css("border-color", "red");
        }
    },

    ValidaCampo_isNotChecked: function(mensagem) {
        preencheu = false;
        qtdRadios = this.length;
        for (i = 0; i < qtdRadios; i++) {
            if (this[i].checked) {
                preencheu = true;
                break;
            }
        }
        if (preencheu == false) {
            $(window).Validacao.setMensagem(mensagem);
        }
    },

    ValidaCampo_isNumeric: function(mensagem) {

        var ValidChars = "0123456789.,";
        var IsNumber = true;
        var Char;
        if (this.attr("value") != null) {
            for (i = 0; i < this.attr("value").length && IsNumber == true; i++) {
                Char = this.attr("value").charAt(i);
                if (ValidChars.indexOf(Char) == -1) {
                    IsNumber = false;
                }
            }
        }
        if (!IsNumber) {
            $(window).Validacao.setMensagem(mensagem);
            this.addClass("errorbox");
        }else {
       	 	this.removeClass("errorbox");
       }
    },

    ValidaCampo_isReal: function(mensagem) {

        var reTipo = /^[+-]?((\d+|\d{1,3}(\.\d{3})+)(\,\d*)?|\,\d+)$/;
        if (!reTipo.test(this.attr("value"))) {
            $(window).Validacao.setMensagem(mensagem);
        }

    },

    /*
     * Verify if the date have the correct format
     */
    ValidaCampo_isDate: function(mensagem) {

         if (!isDate(this.attr("value"), "dd/MM/yyyy")) {
            $(window).Validacao.setMensagem(mensagem);
            this.addClass("errorbox");
         }
         else {
         	 this.removeClass("errorbox");
        }
    },

    ValidaCampo_isInteger: function(mensagem) {
        var ValidChars = "0123456789";
        var IsInteger = true;
        var Char;
        if (this.attr("value") != null) {
            for (i = 0; i < this.attr("value").length && IsInteger == true; i++) {
                Char = this.attr("value").charAt(i);
                if (ValidChars.indexOf(Char) == -1) {
                    IsInteger = false;
                }
            }
        }
        if (!IsInteger) {
            $(window).Validacao.setMensagem(mensagem);
        }
    },

    ValidaCampo_isMaiorque: function(isigual, mensagem) {
        $(window).Validacao.setMensagem(mensagem);
    },

    ValidaCampo_isMenorque: function(isigual, mensagem) {
        var valor = this.attr("value");
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        valor = valor.replace("- ", "-");
        valor = parseFloat(valor);

        var minimo = parseFloat(isigual);

        if (valor < minimo) {
            $(window).Validacao.setMensagem(mensagem);
        }
    },

    ValidaCampo_isMaiorque_and_Menorque: function(isigualmaior, isigualmenor, mensagem) {
        $(window).Validacao.setMensagem(mensagem);
    },

    ValidaCampo_isHora24: function(mensagem) {

        var reTipo = /^([0-1]\d|2[0-3]):[0-5]\d$/;
        if (!reTipo.test(this.attr("value"))) {
            $(window).Validacao.setMensagem(mensagem);

        }

    },

    ValidaCampo_isMaxLength: function(tamanho, mensagem) {
        if (this.attr("value").length > tamanho) {
            $(window).Validacao.setMensagem(mensagem);
        }
    },

    ValidaCampo_QtdDias: function(Dias, data1, mensagem) {
        data2 = this.attr("value");
        Data1 = new Date(data1.substr(6, 4), data1.substr(3, 2) - 1, data1.substr(0, 2));
        Data2 = new Date(data2.substr(6, 4), data2.substr(3, 2) - 1, data2.substr(0, 2));

        var tempo = Math.ceil((Data2.getTime() - Data1.getTime()) / 1000 / 60 / 60 / 24);

        if (tempo > Dias) {
            $(window).Validacao.setMensagem(mensagem);
        }
    },

    ValidaCampo_Periodo: function(data1, mensagem) {
        data2 = this.attr("value");
        Data1 = new Date(data1.substr(6, 4), data1.substr(3, 2) - 1, data1.substr(0, 2));
        Data2 = new Date(data2.substr(6, 4), data2.substr(3, 2) - 1, data2.substr(0, 2));

        var tempo = Math.ceil((Data2.getTime() - Data1.getTime()) / 1000 / 60 / 60 / 24);

        if (tempo < 0) {
            $(window).Validacao.setMensagem(mensagem);
            //this.css("border-color", "red");
            //this.css("background", "white");
        } else {
        //this.css("border-color", "#7C7C7C");
        //this.css("background", "white");

        }

    },

    ValidaCampo_CompareTo: function(valor2, simbolo, mensagem) {

        if (simbolo == "==") {
            if (this.attr("value") == valor2) {
                $(window).Validacao.setMensagem(mensagem);
            }
        }
        if (simbolo == "!=") {
            if (this.attr("value") != valor2) {
                $(window).Validacao.setMensagem(mensagem);
            }
        }

    },


    ValidaCampo_Funcao: function(funcao) {

        retorno = eval(funcao + "();");
        if (Trim(retorno) != "")
            $(window).Validacao.setMensagem(retorno);
    },
    
    /*
     * Test if the Date is greater than DateTest
     */
    ValidaCampo_isDateAfter: function(DateTest, mensagem) {

        if (compareDates(this.attr("value"), "dd/MM/yyyy", DateTest, "dd/MM/yyyy") != 1) {
           $(window).Validacao.setMensagem(mensagem);
           this.addClass("errorbox");
        }
        else {
        	 this.removeClass("errorbox");
       }
   }



});