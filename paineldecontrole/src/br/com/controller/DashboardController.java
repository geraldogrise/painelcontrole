package br.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

	@RequestMapping("/dashboard/listar")
	public ModelAndView listar() throws Exception {

		ModelAndView modelAndView = new ModelAndView("/jsp/Dashboard/index");
		modelAndView.addObject("title", "Dashboard");

		return modelAndView;
	}
//Geraldo 
}
