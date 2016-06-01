package controllers;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import operators.RestaurantOperator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Status;
import entities.Restaurant;

/**
 * apis about restaurants.
 * 
 * @author bdai
 *
 */
@RestController
public class RestaurantsController {
	private static final RestaurantOperator RESTAURANT = RestaurantOperator.INSTANCE;
	
	@RequestMapping("/api/restaurants/search")
	public Status searchRestaurants(@RequestParam(value = "province") String province,
			@RequestParam(value = "city") String city, 
			@RequestParam(value = "district") String district) throws Exception {
		
		return new Status(false, 2);
	}
}
