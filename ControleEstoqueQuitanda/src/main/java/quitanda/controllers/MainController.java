package quitanda.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	// inject via application.properties
	@Value("${application.name}")
	private String applicationName = "Hello World";
	
	private String hostname = "ec2-54-233-157-191.sa-east-1.compute.amazonaws.com:8080";
	//private String hostname = "localhost:8080";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("applicationName", this.applicationName);
		model.put("moduleName", "HOME");
		return "welcome";
	}
	
	@RequestMapping("/produtos")
	public String produtos(Map<String, Object> model) {
		model.put("applicationName", this.applicationName);
		model.put("moduleName", "PRODUTOS");
		return "produtos";
	}

	@RequestMapping("/compras")
	public String compras(Map<String, Object> model) {
		model.put("applicationName", this.applicationName);
		model.put("moduleName", "COMPRAS");
		model.put("hostname", hostname);
		return "compras";
	}

	@RequestMapping("/compras/incluir")
	public String incluirCompras(Map<String, Object> model) {
		model.put("applicationName", this.applicationName);
		model.put("moduleName", "INCLUIR COMPRAS");
		model.put("hostname", hostname);
		return "incluirCompras";
	}	
	
	@RequestMapping("/configuracoes")
	public String configuracoes(Map<String, Object> model) {
		model.put("applicationName", this.applicationName);
		model.put("moduleName", "CONFIGURACOES");
		return "configuracoes";
	}
	
}
