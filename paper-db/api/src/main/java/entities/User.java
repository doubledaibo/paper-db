package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class User {

	public static class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setLocation(rs.getString("location"));
			user.setBirthday(rs.getString("birthday"));
			user.setDescription(rs.getString("description"));
			user.setScore(rs.getInt("score"));
			user.setRegisterTime(rs.getDate("register_time") + "  "
					+ rs.getTime("register_time"));
			user.setShowData(rs.getBoolean("show_data"));
			user.setShowRestaurants(rs.getBoolean("show_restaurants"));
			user.setShowCollections(rs.getBoolean("show_collections"));
			user.setShowOrders(rs.getBoolean("show_orders"));
			user.setShowItems(rs.getBoolean("show_items"));
			user.setImageCount(rs.getInt("image_count"));
			user.setImageDefault(rs.getString("image_default"));
			user.setRestaurantCollections(rs
					.getString("restaurant_collections"));
			user.setDishCollections(rs.getString("dish_collections"));
			return user;
		}
	}

	private Integer id = null;
	private String name = null;
	private String email = null;
	private String phoneNumber = null;
	private String location = null;
	private String birthday = null;
	private String description = null;
	private Integer score = null;
	private String restaurantCollections = null;
	private String dishCollections = null;
	private String registerTime = null;
	private Boolean showData = null;
	private Boolean showRestaurants = null;
	private Boolean showCollections = null;
	private Boolean showOrders = null;
	private Boolean showItems = null;
	private Integer imageCount = null;
	private String imageDefault = null;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setRestaurantCollections(String collections) {
		this.restaurantCollections = collections;
	}

	public void setDishCollections(String collections) {
		this.dishCollections = collections;
	}

	public void setRegisterTime(String time) {
		this.registerTime = time;
	}

	public void setShowData(Boolean show) {
		this.showData = show;
	}

	public void setShowRestaurants(Boolean show) {
		this.showRestaurants = show;
	}

	public void setShowCollections(Boolean show) {
		this.showCollections = show;
	}

	public void setShowOrders(Boolean show) {
		this.showOrders = show;
	}

	public void setShowItems(Boolean show) {
		this.showItems = show;
	}

	public void setImageCount(Integer count) {
		this.imageCount = count;
	}

	public void setImageDefault(String image) {
		this.imageDefault = image;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getDescription() {
		return description;
	}

	public Integer getScore() {
		return score;
	}

	public String getRestaurantCollections() {
		return restaurantCollections;
	}

	public String getDishCollections() {
		return dishCollections;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public Boolean getShowData() {
		return showData;
	}

	public Boolean getShowRestaurants() {
		return showRestaurants;
	}

	public Boolean getShowCollections() {
		return showCollections;
	}

	public Boolean getShowOrders() {
		return showOrders;
	}

	public Boolean getShowItems() {
		return showItems;
	}

	public Integer getImageCount() {
		return imageCount;
	}

	public String getImageDefault() {
		return imageDefault;
	}

}
