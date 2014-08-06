package br.com.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.business.FilabfmicfbfBusiness;
import br.com.model.Filabfmicfbf;
import br.com.util.Auxiliar;
import br.com.util.JSonResult;

@Controller
public class FilabfmicfbfController {

	@RequestMapping("/filabfmicfbf/listar")
	public ModelAndView listar() throws Exception {

		ModelAndView modelAndView = new ModelAndView("/jsp/Filabfmicfbf/index");
		modelAndView.addObject("title", "Fila");

		return modelAndView;
	}
	
	@RequestMapping(value = "/filabfmicfbf/pesquisar", method = RequestMethod.POST)
	public @ResponseBody
	JSonResult pesquisar(
			@ModelAttribute("Filabfmicfbf") Filabfmicfbf filabfmicfbf,
			HttpServletRequest request) throws Exception {
		StringBuilder _Origem = null;
		StringBuilder _ldados = null;
		FilabfmicfbfBusiness business = new FilabfmicfbfBusiness();
		List<Filabfmicfbf> lista_geral = null;
		JSonResult jsonresult = new JSonResult();
		jsonresult.setErro(false);
		jsonresult.setMsg_erro(null);

		try {
			
			String dt_ini = "";
			String dt_fin = "";
			String _xml = "";
			
			if (!request.getParameter("dt_ini").isEmpty()){
				dt_ini = request.getParameter("dt_ini").toString();
			}
			if (!request.getParameter("dt_fin").isEmpty()){
				dt_fin = request.getParameter("dt_fin").toString();
			}
			
			_xml = montaXMLConsulta(dt_ini, dt_fin);
			
			lista_geral = business.consultar(_xml, "{ call UP_FILA_BFM_ICFBF(?, ?) }");
			_Origem = new StringBuilder("");
			_Origem.append("<tr>");
			_Origem.append("<td width='33'>");
			_Origem.append("<a title=\"CONSULTAR\" class=\"btnGrid\" href=\"#\" onclick=\"setOutrasChavesIndex('%s');fn_ShowTelaCRUD(\'FRMLISTA\', \'filabfmicfbf_erro\', \'listar\', \'crudtela\', \'\', false);\"></a>");
			_Origem.append("</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("<td align='right'>%s</td>");
			_Origem.append("<td align='center'>%s</td>");
			_Origem.append("</tr>");
			
			_ldados = new StringBuilder("");
			_ldados.append("<tr>");
			_ldados.append("<td colspan='3'>&nbsp;</td>");
			_ldados.append("<th colspan='5' align='center' class='thBg2'>BFM</th>");
			_ldados.append("<th colspan='5' align='center' class='thBg2'>ICFBF</th>");
			_ldados.append("</tr>");
			_ldados.append("<tr>");
			_ldados.append("<th width='21'  align='left'>&nbsp;</th>");
			_ldados.append("<th width='80'  align='center'>Total Registro</th>");
			_ldados.append("<th width='90'  align='right'>Valor Lote</th>");
			_ldados.append("<th width='90'  align='center'>Reg. Processado</th>");
			_ldados.append("<th width='100' align='right'>Vl. Processado</th>");
			_ldados.append("<th width='120' align='center'>Reg.<br />Process. Erro</th>");
			_ldados.append("<th width='100' align='right'>Vl. Process.<br />com Erro</th>");
			_ldados.append("<th width='70'  align='center'>Última Dt. Importação</th>");
			_ldados.append("<th width='90'  align='center'>Regis. Process.</th>");
			_ldados.append("<th width='90'  align='right'>Vl. Process.</th>");
			_ldados.append("<th width='180' align='center'>Regis. Não<br />Process. com Erro</th>");
			_ldados.append("<th width='80'  align='right'>Vl. Process.<br />com Erro</th>");
			_ldados.append("<th width='80'  align='center'>Última Dt. Consumo</th>");
			_ldados.append("</tr>");
			
			DecimalFormat formatar 			= new DecimalFormat("###,###.##");
			String dataicfbf = "";
			String databfm   = "";
			
			for (Filabfmicfbf object : lista_geral) {
				
				if (object.getUltimadata_bfm().isEmpty()){
					dataicfbf = "";
				}else{
					dataicfbf = object.getUltimadata_bfm();
				}
				if (object.getUltimadata_icfbf().isEmpty()){
					databfm = object.getUltimadata_icfbf();
				}else{
					databfm = "";
				}
				
				_ldados.append(String.format(_Origem.toString(),
						object.getNomelote(), formatar.format(object.getTotalregistro()), formatar.format(object.getValorlote()),
						formatar.format(object.getRegistroprocessado_bfm()), formatar.format(object.getValorprocessado_bfm()),
						formatar.format(object.getRegistroprocessadoerro_bfm()), formatar.format(object.getValorprocessadoerro_bfm()), databfm,
						formatar.format(object.getRegistroprocessado_icfbf()), formatar.format(object.getValorprocessado_icfbf()),
						formatar.format(object.getRegistroprocessadoerro_icfbf()), formatar.format(object.getValorprocessadoerro_icfbf()), dataicfbf));
			}
			jsonresult.setDados(_ldados.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			jsonresult.setErro(true);
			jsonresult.setMsg_erro(e.getMessage());

		}

		_Origem = null;
		_ldados = null;
		business = null;
		lista_geral = null;

		return jsonresult;
	}

	
	public String montaXMLConsulta(String dt_ini, String dt_fin){
		
		String _xml = "";
		
		_xml += "<dt_ini>" + dt_ini + "</dt_ini>";
		_xml += "<dt_fin>" + dt_fin + "</dt_fin>";
		_xml = Auxiliar.addPadrao(_xml);
		
		return _xml;
	}
}
