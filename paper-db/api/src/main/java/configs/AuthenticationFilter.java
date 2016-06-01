package configs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;

import utils.AES;

/**
 * A filter for getting user login info first.
 * 
 * @author ruochen.xu
 *
 */
@Configuration
public class AuthenticationFilter implements Filter {
	public static final String LOGIN_URL = "/login";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		setAuthData(request);
		chain.doFilter(request, response);
	}

	private void setAuthData(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authData = null;
		// Get auth cookie.
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(Config.COOKIE_AUTH_NAME)) {
					authData = cookie.getValue();
					break;
				}
			}
		}
		if (authData == null) {
			return;
		}
		// Decrypt auth data.
		try {
			authData = AES.AUTH.decrypt(authData);
		} catch (Exception e) {
			return;
		}
		request.setAttribute(Config.USER_INFO_NAME, authData);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
