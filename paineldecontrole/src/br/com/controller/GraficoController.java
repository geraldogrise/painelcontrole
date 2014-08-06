package br.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GraficoController {

	@RequestMapping("/grafico/listar")
	public ModelAndView listar() throws Exception {

		ModelAndView modelAndView = new ModelAndView("/jsp/Grafico/index");
		modelAndView.addObject("title", "Gráfico");

		return modelAndView;
	}

}
