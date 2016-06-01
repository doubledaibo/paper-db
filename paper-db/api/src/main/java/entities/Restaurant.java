package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Restaurant {
	
	public static class RestaurantMapper implements RowMapper<Restaurant> {
		@Override
		public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
			Restaurant restaurant = new Restaurant();
			restaurant.setId(rs.getInt("id"));
			restaurant.setUserId(rs.getInt("user_id"));
			restaurant.setName(rs.getString("name"));
			restaurant.setNameMultiLan(rs.getString("name_multilan"));
			restaurant.setProvince(rs.getString("province"));
			restaurant.setCity(rs.getString("city"));
			restaurant.setDistrict(rs.getString("district"));
			restaurant.setStreet(rs.getString("street"));
			restaurant.setPhoneNumber(rs.getString("phone_number"));
			restaurant.setValid(rs.getBoolean("valid"));
			restaurant.setDescription(rs.getString("description"));
			restaurant.setDescriptionMultiLan(rs.getString("description_multilan"));
			restaurant.setRegisterTime(rs.getDate("register_time") + "  "
					+ rs.getTime("register_time"));
			restaurant.setMenu(rs.getString("menu"));
			restaurant.setRateCount(rs.getInt("rate_count"));
			restaurant.setRateTotal(rs.getInt("rate_total"));
			restaurant.setPraiseCount(rs.getInt("praise_count"));
			restaurant.setCollectCount(rs.getInt("collectCount"));
			restaurant.setTakeOut(rs.getBoolean("take_out"));
			restaurant.setOpen(rs.getBoolean("open"));
			restaurant.setCommentCount(rs.getInt("comment_count"));
			restaurant.setStaffCount(rs.getInt("staff_count"));
			restaurant.setDeleted(rs.getBoolean("deleted"));
			restaurant.setImageCount(rs.getInt("image_count"));
			restaurant.setImageDefault(rs.getString("image_default"));
			return restaurant;
		}
	}
	
	private Integer id = null;
	private Integer userId = null;
	private String name = null;
	private String nameMultiLan = null;
	private String province = null;
	private String city = null;
	private String district = null;
	private String street = null;
	private String phoneNumber = null;
	private Boolean valid = null;
	private String description = null;
	private String descriptionMultiLan = null;
	private String registerTime = null;
	private String menu = null;
	private Integer rateCount = null;
	private Integer rateTotal = null;
	private Integer praiseCount = null;
	private Integer collectCount = null;
	private Boolean takeOut = null;
	private Boolean open = null;
	private Integer commentCount = null;
	private Integer staffCount = null;
	private Boolean deleted = null;
	private Integer imageCount = null;
	private String imageDefault = null;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNameMultiLan(String nameMultiLan) {
		this.nameMultiLan = nameMultiLan;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDescriptionMultiLan(String descriptionMultiLan) {
		this.descriptionMultiLan = descriptionMultiLan;
	}
	
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	public void setRateCount(Integer rateCount) {
		this.rateCount = rateCount;
	}
	
	public void setRateTotal(Integer rateTotal) {
		this.rateTotal = rateTotal;
	}
	
	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}
	
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	
	public void setTakeOut(Boolean takeOut) {
		this.takeOut = takeOut;
	}
	
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	public void setStaffCount(Integer staffCount) {
		this.staffCount = staffCount;
	}
	
	public void setDeleted(Boolean deleted) { 
		this.deleted = deleted;
	}
	
	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}
	
	public void setImageDefault(String imageDefault) {
		this.imageDefault = imageDefault;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getUserId() { 
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNameMultiLan() {
		return nameMultiLan;
	}
	
	public String getProvince() {
		return province;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public Boolean getValid() {
		return valid;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDescriptionMultiLan() {
		return descriptionMultiLan;
	}
	
	public String getRegisterTime() {
		return registerTime;
	}
	
	public String getMenu() {
		return menu;
	}
	
	public Integer getRateCount() {
		return rateCount;
	}
	
	public Integer getRateTotal() {
		return rateTotal;
	}
	
	public Integer getPraiseCount() {
		return praiseCount;
	}
	
	public Integer getCollectCount() {
		return collectCount;
	}
	
	public Boolean getTakeOut() {
		return takeOut;
	}
	
	public Boolean getOpen() {
		return open;
	}
	
	public Integer getCommentCount() {
		return commentCount;
	}
	
	public Integer getStaffCount() {
		return staffCount;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public Integer getImageCount() {
		return imageCount;
	}
	
	public String getImageDefault() {
		return imageDefault;
	}
	
}
