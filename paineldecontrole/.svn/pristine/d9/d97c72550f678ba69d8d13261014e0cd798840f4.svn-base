package br.com.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import br.com.business.GenericBusiness;


public class ControllerBase<T,B> {

	public ModelAndView consultarPorId(HttpServletRequest request,Class<T> c, Class<B> b, String _view, String nmentity,
			String _acao, String Titulo) throws Exception {

		ModelAndView modelAndView = null;
		try {
			
			T objetoTeste = (T) c.newInstance();
			B business = (B) c.newInstance();
			
			
			//Ger_pmBusiness business = new Ger_pmBusiness();

			//_ger_pm.setPm_id(id);

			Object retorno = ((GenericBusiness<T>)business).consultarXML(request, objetoTeste, "selectById").get(0);

			modelAndView = new ModelAndView(_view, nmentity, retorno);
			modelAndView.addObject("acao", _acao);
			modelAndView.addObject("title", Titulo);
		} catch (Exception e) {
			modelAndView = new ModelAndView("/jsp/ErroPopup");
			modelAndView.addObject("erropopup", e.getMessage());
		}
		return modelAndView;
	}
	
}
