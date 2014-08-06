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

import br.com.business.Monitoramento_loteBusiness;
import br.com.model.Monitoramento;
import br.com.model.Monitoramento_lote;
import br.com.util.JSonResult;

@Controller
public class Monitoramento_loteController {

	@RequestMapping(value = "/monitoramento_lote/listar", method = RequestMethod.POST )
	public ModelAndView listar(@ModelAttribute("Monitoramento") Monitoramento monitoramento, HttpServletRequest request) throws Exception {

		ModelAndView modelAndView = new ModelAndView("/jsp/Monitoramento_lote/index");
		modelAndView.addObject("title", "Monitoramento Lote");
		modelAndView.addObject("nomelote", monitoramento.getNomelote());

		return modelAndView;
	}

	@RequestMapping(value = "/monitoramento_lote/pesquisar", method = RequestMethod.POST)
	public @ResponseBody
	JSonResult pesquisar(
			@ModelAttribute("Monitoramento_lote") Monitoramento_lote monitoramento_lote,
			HttpServletRequest request) throws Exception {
		StringBuilder _Origem = null;
		StringBuilder _ldados = null;
		Monitoramento_loteBusiness business = new Monitoramento_loteBusiness();
		List<Monitoramento_lote> lista_geral = null;
		JSonResult jsonresult = new JSonResult();
		jsonresult.setErro(false);
		jsonresult.setMsg_erro(null);

		try {
			String _nomelote = "";
			
			if (!request.getParameter("nomelote").isEmpty()){
				_nomelote = request.getParameter("nomelote").toString();
			}
			
			lista_geral = business.consultar(_nomelote, "{ call UP_LISTAR_ARQUIVOS_LOTE(?, ?) }");
			_Origem = new StringBuilder("");
			_Origem.append("<tr>");
			_Origem.append("<td align='left'>");
			_Origem.append("<a title=\"CONSULTAR\" class=\"btnGrid\" href=\"#\" onclick=\"setOutrasChavesIndex('%s','%s');fn_ShowTelaCRUD(\'FRMLISTA\', \'monitoramento_lote_erro\', \'listar\', \'crudtela\', \'\', false);\"></a>");
			_Origem.append("</td>");
			_Origem.append("<td align='left'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("</tr>");
			
			_ldados = new StringBuilder("");
			_ldados.append("<tr>");
			_ldados.append("<th width='33' align='left'>&nbsp;</th>");
			_ldados.append("<th width='75' align='center'>Nome Arquivo</th>");
			_ldados.append("<th width='78' align='center'>Status</th>");
			_ldados.append("<th width='58' align='center'>Total Linhas</th>");
			_ldados.append("<th width='78' align='right'>Vl. Total do Arquivo</th>");
			_ldados.append("<th width='79' align='center'>Linhas Processadas</th>");
			_ldados.append("<th width='79' align='right'>Vl. Processado</th>");
			_ldados.append("<th width='77' align='center'>Linhas Processadas com Erro</th>");
			_ldados.append("<th width='79' align='right'>Vl Processado com Erro</th>");
			_ldados.append("<th width='64' align='center'>Data Importação</th>");
			_ldados.append("</tr>");
			
			
			
			DecimalFormat formatar 		= new DecimalFormat("###,###.##");
			for (Monitoramento_lote object : lista_geral) {
				_ldados.append(String.format(_Origem.toString(), _nomelote,
						object.getNomearquivo(), object.getNomearquivo(),
						object.getStatus(), formatar.format(object.getTotallinhas()),
						formatar.format(object.getValortotalarquivo()), formatar.format(object.getLinhasprocessadas()),
						formatar.format(object.getValorprocessado()), formatar.format(object.getLinhaprocessadaserro()),
						formatar.format(object.getValorprocessadaserro()), object.getDataimportacao()));
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
