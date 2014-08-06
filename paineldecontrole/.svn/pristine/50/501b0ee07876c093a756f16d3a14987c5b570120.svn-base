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

import br.com.business.Monitoramento_lote_erroBusiness;
import br.com.model.Monitoramento;
import br.com.model.Monitoramento_lote_erro;
import br.com.util.JSonResult;

@Controller
public class Monitoramento_lote_erroController {

	@RequestMapping(value = "/monitoramento_lote_erro/listar", method = RequestMethod.POST )
	public ModelAndView listar(@ModelAttribute("Monitoramento") Monitoramento monitoramento, HttpServletRequest request) throws Exception {
		
		String _nomearquivo = "";
		
		if (request.getParameter("nomearquivo") != null){
			_nomearquivo = request.getParameter("nomearquivo"); 
		}
		
		ModelAndView modelAndView = new ModelAndView("/jsp/Monitoramento_lote_erro/index");
		modelAndView.addObject("title", "Monitoramento Lote Erro");
		modelAndView.addObject("nomelote", monitoramento.getNomelote());
		modelAndView.addObject("nomearquivo", _nomearquivo);

		
		return modelAndView;
	}

	@RequestMapping(value = "/monitoramento_lote_erro/pesquisar", method = RequestMethod.POST)
	public @ResponseBody
	JSonResult pesquisar(
			@ModelAttribute("Monitoramento_lote_erro") Monitoramento_lote_erro monitoramento_lote_erro,
			HttpServletRequest request) throws Exception {
		StringBuilder _Origem = null;
		StringBuilder _ldados = null;
		Monitoramento_lote_erroBusiness business = new Monitoramento_lote_erroBusiness();
		List<Monitoramento_lote_erro> lista_geral = null;
		JSonResult jsonresult = new JSonResult();
		jsonresult.setErro(false);
		jsonresult.setMsg_erro(null);

		try {
			String _nomelote = "";
			String _nomearquivo = "";
			
			if (!request.getParameter("nomelote").isEmpty()){
				_nomelote = request.getParameter("nomelote").toString();
			}
			if (!request.getParameter("nomearquivo").isEmpty()){
				_nomearquivo = request.getParameter("nomearquivo").toString();
			}
			
			lista_geral = business.consultar(_nomelote, _nomearquivo,  "{ call UP_LISTAR_ERROS_ARQUIVOS_LOTE(?, ?, ?) }");
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
			for (Monitoramento_lote_erro object : lista_geral) {
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
