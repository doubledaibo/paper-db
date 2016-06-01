package controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Static apis.
 * 
 * @author ruochen.xu
 *
 */
@Controller
public class WebController implements ErrorController {
	/**
	 * Return layout template for all unknown urls and frontend will route.
	 * 
	 * @return layout template.
	 */
	@RequestMapping("/error")
	public String error(HttpServletResponse response) {
		// If frontend's html5mode is disabled, just add the # tag and redirect.
		// return "redirect:/#" + request.getRequestURI();
		// Handle utf8 pages.
		response.setContentType("text/html; charset=UTF-8");
		return "layout.html";
	}

	/**
	 * Useless, but must override it for changing error page.
	 */
	@Override
	public String getErrorPath() {
		return "/error";
	}
}