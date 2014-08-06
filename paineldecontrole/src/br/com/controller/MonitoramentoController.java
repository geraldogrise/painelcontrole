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

import br.com.business.MonitoramentoBusiness;
import br.com.model.Monitoramento;
import br.com.util.Auxiliar;
import br.com.util.JSonResult;

@Controller
public class MonitoramentoController {

	@RequestMapping("/monitoramento/listar")
	public ModelAndView listar() throws Exception {

		ModelAndView modelAndView = new ModelAndView("/jsp/Monitoramento/index");
		modelAndView.addObject("title", "Monitoramento");

		return modelAndView;
	}

	@RequestMapping(value = "/monitoramento/pesquisar", method = RequestMethod.POST)
	public @ResponseBody
	JSonResult pesquisar(
			@ModelAttribute("Monitoramento") Monitoramento monitoramento,
			HttpServletRequest request) throws Exception {
		StringBuilder _Origem = null;
		StringBuilder _ldados = null;
		MonitoramentoBusiness business = new MonitoramentoBusiness();
		List<Monitoramento> lista_geral = null;
		JSonResult jsonresult = new JSonResult();
		jsonresult.setErro(false);
		jsonresult.setMsg_erro(null);

		try {
			String dt_ini = "";
			String dt_fin = "";
			String tipoterminal = "";
			String _xml = "";

			if (!request.getParameter("dt_ini").isEmpty()) {
				dt_ini = request.getParameter("dt_ini").toString();
			}
			if (!request.getParameter("dt_fin").isEmpty()) {
				dt_fin = request.getParameter("dt_fin").toString();
			}
			if (!request.getParameter("tipoterminal").isEmpty()) {
				tipoterminal = request.getParameter("tipoterminal").toString();
			}

			_xml = montaXMLConsulta(dt_ini, dt_fin, tipoterminal);

			lista_geral = business.consultar(_xml,
					"{ call UP_LISTAR_LOTES(?, ?, ?) }");
			_Origem = new StringBuilder("");
			_Origem.append("<tr>");
			_Origem.append("<td width='33'>");
			_Origem.append("<a title=\"CONSULTAR\" class=\"btnGrid consultar\" href=\"#\" onclick=\"setOutrasChavesIndex('%s');fn_ShowTelaCRUD(\'FRMLISTA\', \'monitoramento_lote\', \'listar\', \'crudtela\', \'\', false);\"></a>");
			_Origem.append("</td>");
			_Origem.append("<td width='86' align='left'>%s</td>");
			_Origem.append("<td width='68' align='left'>%s</td>");
			_Origem.append("<td width='40' align='center'>%s</td>");
			_Origem.append("<td width='48' align='center'>%s</td>");
			_Origem.append("<td width='79' align='right' >%s</td>");
			_Origem.append("<td width='58' align='center'>%s</td>");
			_Origem.append("<td width='60' align='center'>%s</td>");
			_Origem.append("<td width='88' align='center'>%s</td>");
			_Origem.append("<td width='77' align='center'>%s</td>");
			_Origem.append("</tr>");

			_ldados = new StringBuilder("");
			_ldados.append("<tr>");
			_ldados.append("<th width='33' align='left'>&nbsp;</th>");
			_ldados.append("<th width='86' align='center'>Nome do Lote</th>");
			_ldados.append("<th width='68' align='center'>Status do Lote</th>");
			_ldados.append("<th width='40' align='center'>Total Arquivos</th>");
			_ldados.append("<th width='48' align='right'>Total de Registros</th>");
			_ldados.append("<th width='79' align='center'>Valor Lote</th>");
			_ldados.append("<th width='58' align='right'>Arqs. Processados</th>");
			_ldados.append("<th width='60' align='center'>Arqs. Não Processados</th>");
			_ldados.append("<th width='88' align='right'>Arqs. Processado com Erro</th>");
			_ldados.append("<th width='77' align='center'>Data Importação</th>");
			_ldados.append("</tr>");

			DecimalFormat formatar = new DecimalFormat("###,###.##");
			for (Monitoramento object : lista_geral) {
				_ldados.append(String.format(_Origem.toString(),
						object.getNomelote(), object.getNomelote(),
						object.getStatuslote(),
						formatar.format(object.getTotalarquivo()),
						formatar.format(object.getTotalregistro()),
						formatar.format(object.getValorlote()),
						formatar.format(object.getArquivonaoprocessado()),
						formatar.format(object.getArquivonaoprocessado()),
						formatar.format(object.getArquivoprocessadoerro()),
						object.getDataimportacao()));
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

	public String montaXMLConsulta(String dt_ini, String dt_fin,
			String tipoterminal) {

		String _xml = "";

		_xml += "<dt_ini>" + dt_ini + "</dt_ini>";
		_xml += "<dt_fin>" + dt_fin + "</dt_fin>";
		_xml += "<tipoterminal>" + tipoterminal + "</tipoterminal>";
		_xml = Auxiliar.addPadrao(_xml);

		return _xml;
	}
}
