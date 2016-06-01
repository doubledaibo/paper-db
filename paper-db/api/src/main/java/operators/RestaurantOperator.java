package operators;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import entities.Restaurant;

public enum RestaurantOperator {
	INSTANCE;
	private JdbcTemplate template = null;

	
	RestaurantOperator() {
		template = Database.INSTANCE.getTemplate();
	}

	/**
	 * Check whether a name is valid.
	 */
	public boolean checkName(String name) {
		String q = "SELECT count(id) FROM restaurants WHERE name = ?;";
		return template.queryForObject(q, Integer.class, name) == 0;
	}

	/**
	 * Get restaurant id by restaurant name.
	 * @return Null for no account.
	 */
	public Integer getIdByName(String name) {
		String q = "SELECT id FROM restaurants WHERE name = ?;";
		Integer id = null;
		try {
			id = template.queryForObject(q, Integer.class, name);
		} catch (EmptyResultDataAccessException e) {
		}
		return id;
	}
	
	/**
	 * Get restaurant id by restaurant nameMultiLan.
	 * @return Null for no account.
	 */
	public Integer getIdByNameMultiLan(String nameMultiLan) {
		String q = "SELECT id FROM restaurants WHERE name_multilan = ?;";
		Integer id = null;
		try {
			id = template.queryForObject(q, Integer.class, nameMultiLan);
		} catch (EmptyResultDataAccessException e) {
		}
		return id;
	}

	/**
	 * Register an restaurant.
	 * @return False for restaurant already exist.
	 */
	public boolean register(String name, String nameMultiLan, String province, String city, String district, String street) {
		String q = "INSERT IGNORE INTO restaurants(name, name_multilan, province, city, district, street, "
				+ "register_time) VALUES (? ,?, ?, ?, ?, ?, CURRENT_TIMESTAMP());";
		return template.update(q, name, nameMultiLan, province, city, district, street) > 0;
	}

	/**
	 * Get restaurant by id.
	 * @return Restaurant
	 */
	public Restaurant getRestaurant(Integer id) {
		String q = "SELECT * FROM restaurants WHERE id = ?;";
		Restaurant restaurant = null;
		restaurant = template.queryForObject(q, new Restaurant.RestaurantMapper(), id);
		return restaurant;
	}
}
