package operators;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import entities.User;

public enum UserOperator {
	INSTANCE;
	private JdbcTemplate template = null;

	UserOperator() {
		template = Database.INSTANCE.getTemplate();
	}

	/**
	 * Check whether a name is valid.
	 */
	public boolean checkName(String name) {
		String q = "SELECT count(id) FROM users WHERE name = ?;";
		return template.queryForObject(q, Integer.class, name) == 0;
	}

	/**
	 * Check whether a email is valid.
	 */
	public boolean checkEmail(String email) {
		String q = "SELECT count(id) FROM users WHERE email = ?;";
		return template.queryForObject(q, Integer.class, email) == 0;
	}

	/**
	 * Get user id by user name and password.
	 * @return Null for no account.
	 */
	public Integer getIdByName(String name, String passwd) {
		String q = "SELECT id FROM users WHERE name = ? AND password = ?;";
		Integer id = null;
		try {
			id = template.queryForObject(q, Integer.class, name, passwd);
		} catch (EmptyResultDataAccessException e) {
		}
		return id;
	}
	
	/**
	 * Get user id by user email and password.
	 * @return Null for no account.
	 */
	public Integer getIdByEmail(String email, String passwd) {
		String q = "SELECT id FROM users WHERE email = ? AND password = ?;";
		Integer id = null;
		try {
			id = template.queryForObject(q, Integer.class, email, passwd);
		} catch (EmptyResultDataAccessException e) {
		}
		return id;
	}

	/**
	 * Register an account.
	 * @return False for account already exist.
	 */
	public boolean register(String name, String email, String password) {
		String q = "INSERT IGNORE INTO users(name, email, password, "
				+ "restaurant_collections, dish_collections, "
				+ "register_time) VALUES (? ,?, ?, ?, ?, CURRENT_TIMESTAMP());";
		return template.update(q, name, email, password, "[]", "[]") > 0;
	}

	/**
	 * Get user by id.
	 * @return User
	 */
	public User getUser(Integer id) {
		String q = "SELECT * FROM users WHERE id = ?;";
		User user = null;
		user = template.queryForObject(q, new User.UserMapper(), id);
		return user;
	}
}
