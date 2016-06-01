package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operators.UserOperator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import configs.Config;
import utils.AES;
import utils.Utils;
import entities.Status;
import entities.User;

/**
 * Apis about users.
 * 
 * @author ruochen.xu
 *
 */
@RestController
public class UsersController {
	private static final UserOperator USER = UserOperator.INSTANCE;

	/**
	 * Get user's login info.
	 * 
	 * @return Json string of user info.
	 */
	@RequestMapping("/api/users/info")
	public Status getUserInfo(HttpServletRequest request) {
		Object info = request.getAttribute("user_info");
		if (info == null) {
			return new Status(false, 2);
		} else {
			return new Status(true, info.toString());
		}
	}

	/**
	 * Register a new account.
	 * @return Status
	 */
	@RequestMapping("/api/users/register")
	public Status register(@RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) throws Exception {
		if (name.length() > 32) {
			return new Status(false, "用户名长度不能超过32。");
		}
		if (email.length() > 64) {
			return new Status(false, "邮箱长度不能超过64。");
		}
		if (password.length() > 32) {
			return new Status(false, "密码长度不能超过32。");
		}
		String passwd = AES.PASSWD.encrypt(password);
		if (USER.register(name, email, passwd)) {
			return new Status(true, 1);
		}
		if (!USER.checkName(name)) {
			return new Status(false, "用户名已经存在。");
		}		
		if (!USER.checkEmail(email)) {
			return new Status(false, "邮箱已经存在。");
		}
		return new Status(false, "未知错误。");
	}

	/**
	 * Account login. Make cookie.
	 * @return Status
	 */
	@RequestMapping("/api/users/login")
	public Status login(HttpServletResponse response,
			@RequestParam(value = "account") String account,
			@RequestParam(value = "password") String password) throws Exception {
		String passwd = AES.PASSWD.encrypt(password);
		Integer id = null;
		// Check name login or email login.
		if (account.contains("@")) {
			id = USER.getIdByEmail(account, passwd);
		} else {
			id = USER.getIdByName(account, passwd);
		}
		if (id == null) {
			return new Status(false, "账号与密码不匹配。");
		}
		User user = USER.getUser(id);
		String cookies = Utils.makeUserCookieData(user);
		cookies = AES.AUTH.encrypt(cookies);
		Cookie cookie = new Cookie(Config.COOKIE_AUTH_NAME, cookies);
		cookie.setMaxAge(Config.COOKIE_AUTH_EXPIRE_TIME);
		cookie.setPath(Config.COOKIE_AUTH_PATH);
		response.addCookie(cookie);
		return new Status(true, 1);
	}

	/**
	 * Account logout. Clear cookie.
	 */
	@RequestMapping("/api/users/logout")
	public Status logout(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getAttribute(Config.USER_INFO_NAME) == null) {
			return new Status(false, 2);
		}
		Cookie cookie = new Cookie(Config.COOKIE_AUTH_NAME, null);
		cookie.setMaxAge(0);
		cookie.setPath(Config.COOKIE_AUTH_PATH);
		response.addCookie(cookie);
		return new Status(true, 1);
	}
}
