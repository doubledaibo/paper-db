package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import configs.Config;
import entities.User;

public class Utils {
	/**
	 * Make cookie for a login user.
	 * 
	 * @return cookie string.
	 */
	public static String makeUserCookieData(User user) {
		ObjectNode cookie = JsonNodeFactory.instance.objectNode();
		cookie.put("id", user.getId());
		cookie.put("name", user.getName());
		cookie.put("email", user.getEmail());
		long now = System.currentTimeMillis();
		cookie.put("loginTime", now);
		cookie.put("expireTime", 1000l * Config.COOKIE_AUTH_EXPIRE_TIME);
		return cookie.toString();
	}

	public static String formatDate(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.CHINA);
		return format.format(date);
	}
}
