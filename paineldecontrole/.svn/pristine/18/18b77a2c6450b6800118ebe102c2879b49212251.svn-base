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

import br.com.business.Lote_arquivoprocessadoBusiness;
import br.com.model.Lote_arquivoprocessado;
import br.com.util.Auxiliar;
import br.com.util.JSonResult;

@Controller
public class Lote_arquivoprocessadoController {

	@RequestMapping("/lotexarquivo/listar")
	public ModelAndView listar() throws Exception {

		ModelAndView modelAndView = new ModelAndView("/jsp/Lotexarquivo/index");
		modelAndView.addObject("title", "Lote x Arquivo");

		return modelAndView;
	}
	
	

	@RequestMapping(value = "/lote_arquivoprocessado/pesquisar", method = RequestMethod.POST)
	public @ResponseBody
	JSonResult pesquisar(
			@ModelAttribute("Lote_arquivoprocessado") Lote_arquivoprocessado Lote_arquivoprocessado,
			HttpServletRequest request) throws Exception {
		
		StringBuilder _Origem = null;
		StringBuilder _ldados = null;
		
		Lote_arquivoprocessadoBusiness business = new Lote_arquivoprocessadoBusiness();
		List<Lote_arquivoprocessado> lista_geral = null;
		
		JSonResult jsonresult = new JSonResult();
		jsonresult.setErro(false);
		jsonresult.setMsg_erro(null);

		try {
			
			String dt_ini = "";
			String dt_fin = "";
			String arquivo_recebido = "";
			String _xml = "";
			
			try
			{
				if (!request.getParameter("dt_ini").isEmpty()){
					dt_ini = request.getParameter("dt_ini").toString();
				}
			}catch (Exception e) {
			}

			try
			{
				if (!request.getParameter("dt_fin").isEmpty()){
					dt_fin = request.getParameter("dt_fin").toString();
				}
			}catch (Exception e) {
			}
			try{
				if (!request.getParameter("arquivo_recebido").isEmpty()){
					arquivo_recebido = request.getParameter("arquivo_recebido").toString();
				}
			}catch (Exception e) {
			}

			_xml = montaXMLConsulta(dt_ini, dt_fin, arquivo_recebido);
			

			lista_geral = business.consultar(_xml, "{ call UP_LISTAR_LOTE_ARQUIVOS (?, ?, ?) }");
			_Origem = new StringBuilder("");
			_Origem.append("<tr>");
			_Origem.append("<td align='left'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='rigth'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("</tr>");
			
			_ldados = new StringBuilder("");
			_ldados.append("<tr>");
			_ldados.append("<th align='left'>Nome Lote</th>");
			_ldados.append("<th align='center'>Qtde. Arquivos</th>");
			_ldados.append("<th align='center'>Total Registros</th>");
			_ldados.append("<th align='rigth'>Valor</th>");
			_ldados.append("<th align='center'>Processamento</th>");
			_ldados.append("</tr>");
			
			DecimalFormat formatar 		= new DecimalFormat("###,###.##");

			for (Lote_arquivoprocessado object : lista_geral) {
				_ldados.append(String.format(_Origem.toString(),
						object.getNomelote(), formatar.format(object.getQuantidadearquivo()),
						formatar.format(object.getTotalregistro()), formatar.format(object.getValor()), object.getDataprocessamento()));
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
	
	
	public String montaXMLConsulta(String dt_ini, String dt_fin, String arquivo_recebido){
		
		String _xml = "";
		
		_xml += "<dt_ini>" + dt_ini + "</dt_ini>";
		_xml += "<dt_fin>" + dt_fin + "</dt_fin>";
		_xml += "<arquivo_recebido>" + arquivo_recebido + "</arquivo_recebido>";
		_xml = Auxiliar.addPadrao(_xml);
		
		return _xml;
	}

}
