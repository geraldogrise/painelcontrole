 package br.com.controller;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.business.RelatorioBusiness;
import br.com.util.Auxiliar;

@Controller
public class RelatorioController {

	@RequestMapping("/relatorio/listar")
	public ModelAndView listar() throws Exception {

		ModelAndView modelAndView = new ModelAndView("/jsp/Relatorio/index");
		modelAndView.addObject("title", "Relatorio");

		return modelAndView;
	}
	
	
	@RequestMapping("/relatorio/imprimirrelatorio")
	public void imprimirrelatorio(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		String path = "";
		ResultSet rs = null;

		RelatorioBusiness business = new RelatorioBusiness();
		String nome_relatorio = "";
		String nome_procedure = "";
		String tipo_impressao = "";
		String dt_ini 		  = "";
		String dt_fin		  = "";
		String uf 			  = "";
		String tipo_terminal  = "";
		String sistema  	  = "";
		

		try{
			if (!request.getParameter("jasper").isEmpty()){
				nome_relatorio = request.getParameter("jasper");
			}
			if (!request.getParameter("procedure").isEmpty()){
				nome_procedure = request.getParameter("procedure");
			}
			if (!request.getParameter("tipoImpressao").isEmpty()){
				tipo_impressao = request.getParameter("tipoImpressao");
			}
			if (!request.getParameter("dt_ini").isEmpty()){
				dt_ini = request.getParameter("dt_ini").toString();
			}
			if (!request.getParameter("dt_fin").isEmpty()){
				dt_fin = request.getParameter("dt_fin").toString();
			}
			if (!request.getParameter("uf").isEmpty()){
				uf = request.getParameter("uf").toString();
			}

			if (!request.getParameter("sistema").isEmpty()){
				sistema = request.getParameter("sistema").toString();
			}
			if (!request.getParameter("terminal").isEmpty()){
				tipo_terminal = request.getParameter("terminal").toString();
			}
			
			if (!request.getParameter("ufselecionada").isEmpty()){
				uf =request.getParameter("ufselecionada").toString();
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}

		try {
			
			String _xml = "";
			
			_xml = montaXMLConsulta(dt_ini, dt_fin, uf, tipo_terminal, sistema);

			path = this.getClass().getResource("/../../jasper/" + nome_relatorio + ".jasper").getPath();
			rs = business.executaRelatorio(_xml, "{call " + nome_procedure + "( ?, ? ) }");

			Map<String, Object> parametro = new HashMap<String, Object>();
			
			if (!(dt_ini.isEmpty() & dt_fin.isEmpty())){
				parametro.put("p_periodo", dt_ini + " A " + dt_fin);	
			}else if (!dt_ini.isEmpty()) {
				parametro.put("p_periodo", dt_ini);	
			}
			parametro.put("p_sistema", sistema);					

			
			if (tipo_impressao.equalsIgnoreCase("excel")) {
				Auxiliar.geraxlsADN(rs, java.util.UUID.randomUUID().toString(),	request, response);
			} else {
				Auxiliar.execute(request, response, rs, parametro, path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				if (!rs.isClosed()) {
					rs.close();
				}
			}
		}

	}
	
	
	public String montaXMLConsulta(String dt_ini, String dt_fin, String uf, String tipoterminal, String sistema){
		
		String _xml = "";
		
		_xml += "<dt_ini>" + dt_ini + "</dt_ini>";
		_xml += "<dt_fin>" + dt_fin + "</dt_fin>";
		_xml += "<tipoterminal>" + tipoterminal + "</tipoterminal>";
		_xml += "<sistema>" + sistema + "</sistema>";
		_xml += retornaXMLConsulta("tab_uf", "nome", uf);
		_xml = Auxiliar.addPadrao(_xml);
		
		return _xml;
	}
	
	public String retornaXMLConsulta (String tabela, String chave, String valor)
	{
		
		Integer _reg_qtd = 0;
		String Body = "";
		String Footer = "";
		String Retorno = "";
		Integer tamanho = retornaTamanho(valor);
		
		for (int i = 0; i < valor.length(); i=i+(tamanho + 1))
		{
			Body += "<"+ chave +">" + valor.substring(i, i+tamanho) + "</"+ chave + ">";
			_reg_qtd++;
		}
		String Head = "<" + tabela + "><reg_qtd>" + _reg_qtd + "</reg_qtd> ";
		
		Footer += "</"+ tabela + ">";
		
		Retorno = Head + Body + Footer;
		return Retorno;
		
	}
	

	public Integer retornaTamanho(String valor)
	{
		Integer tamanho_campo = 0;
		
		if (valor.length() <= 0){
			return 0;
		}
		
		while (!valor.substring(tamanho_campo, (tamanho_campo + 1) ).equals(";")) {
			
			tamanho_campo++;
			
		}
		return tamanho_campo;
	}

}
