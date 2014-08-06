package br.com.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.business.Filabfmicfbf_erroBusiness;
import br.com.model.Filabfmicfbf;
import br.com.model.Filabfmicfbf_erro;
import br.com.util.JSonResult;

@Controller
public class Filabfmicfbf_erroController {

	@RequestMapping(value = "/filabfmicfbf_erro/listar", method = RequestMethod.POST )
	public ModelAndView listar(@ModelAttribute("Filabfmicfbf") Filabfmicfbf Filabfmicfbf, HttpServletRequest request) throws Exception {
		
/*		String _nomelote = "";
		
		if (request.getParameter("nomelote") != null){
			_nomelote = request.getParameter("nomelote"); 
		}
		
*/		ModelAndView modelAndView = new ModelAndView("/jsp/Filabfmicfbf_erro/index");
		modelAndView.addObject("title", "Fila BFM / ICFBF - Erro");
		modelAndView.addObject("nomelote", Filabfmicfbf.getNomelote());
		
		return modelAndView;
	}

	@RequestMapping(value = "/filabfmicfbf_erro/pesquisar", method = RequestMethod.POST)
	public @ResponseBody
	JSonResult pesquisar(
			@ModelAttribute("Filabfmicfbf_erro") Filabfmicfbf_erro filabfmicfbf_erro,
			HttpServletRequest request) throws Exception {

		StringBuilder _Origem = null;
		StringBuilder _ldados = null;
		
		Filabfmicfbf_erroBusiness business = new Filabfmicfbf_erroBusiness();
		List<Filabfmicfbf_erro> lista_geral = null;
		
		JSonResult jsonresult = new JSonResult();
		jsonresult.setErro(false);
		jsonresult.setMsg_erro(null);

		try {
			String _nomelote = "";
			
			if (!request.getParameter("nomelote").isEmpty()){
				_nomelote = request.getParameter("nomelote").toString();
			}
			
			lista_geral = business.consultar(_nomelote, "{ call UP_LISTAR_ERROS_FILA_BFM(?, ?, ?) }");
			_Origem = new StringBuilder("");
			_Origem.append("<tr>");
			_Origem.append("<td align='left'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("</tr>");
			
			_ldados = new StringBuilder("");
			_ldados.append("<tr>");
			_ldados.append("<th align='left' style='min-width:200px'>Descrição    do Erro</th>");
			_ldados.append("<th align='center'>Registros    impactados</th>");
			_ldados.append("<th align='center'>Valor impactado</th>");
			_ldados.append("</tr>");
			
			DecimalFormat formatar 		= new DecimalFormat("###,###.##");
			for (Filabfmicfbf_erro object : lista_geral) {
				_ldados.append(String.format(_Origem.toString(),
						object.getDescricaoerro(), formatar.format(object.getQuantidadeimpactado()),
						formatar.format(object.getValorimpactado())));
			}
			jsonresult.setDados(_ldados.toString());

		} catch (Exception e) {
			jsonresult.setErro(true);
			jsonresult.setMsg_erro(e.getMessage());

		}

		_Origem = null;
		_ldados = null;
		business = null;
		lista_geral = null;

		return jsonresult;
	}
	
}
