package com.cnksi.extjs4mvc;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController
{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/login")
	public String login(Locale locale, Model model)
	{
		
		logger.info("用户登录");
		
		return "index";
	}
	
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model)
	{
		
		logger.info("用户登录");
		
		return "home";
	}

}
