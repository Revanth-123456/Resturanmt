package com.example.restaurant.Controller;

import com.example.restaurant.DTO.MenuDTO;
import com.example.restaurant.DTO.RestaurantDTO;
import com.example.restaurant.Model.Menu;
import com.example.restaurant.Model.Restaurant;
import com.example.restaurant.Service.RestaurantService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class RestaurantController {
	private final RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@PostMapping("/addRestaurant")
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}

	@PostMapping("/{restaurantId}/menu")
	public void createMenu(@PathVariable Long restaurantId, @RequestParam String name, @RequestParam String description,@RequestParam String category, @RequestParam double price, @RequestParam Boolean availability) {
		restaurantService.createMenu(restaurantId, name, description,category, price, availability);
	}

	@PostMapping("/{restaurantId}/_menu")
	public void createMenu(@PathVariable Long restaurantId, @RequestBody Menu menu) {
		restaurantService.createMenu(restaurantId, menu);
	}

	@PutMapping("/menu/{itemId}")
	public void updateMenu(@PathVariable Long itemId, @RequestParam String newName, @RequestParam String newDescription,@RequestParam String newCategory, @RequestParam double newPrice, @RequestParam Boolean newAvailability) {
		restaurantService.updateMenu(itemId, newName, newDescription,newCategory, newPrice, newAvailability);
	}

	@PutMapping("/_menu/{itemId}")
	public void updateMenu(@PathVariable Long itemId, @RequestBody Menu menu) {
		restaurantService.updateMenu(menu, itemId);
	}

	@PutMapping("updateRestaurant/{id}")
	public Restaurant putMethodName(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		return restaurantService.updateRestaurantById(id, restaurant);
	}

	@DeleteMapping("/delRestaurant/{restaurantId}")
	public void deleteRestaurant(@PathVariable Long restaurantId) {
		restaurantService.deleteRestaurant(restaurantId);
	}

	@DeleteMapping("/menu/{itemId}")
	public void deleteMenu(@PathVariable Long itemId) {
		restaurantService.deleteMenu(itemId);
	}

	@GetMapping("/showAllRestaurant")
	public List<RestaurantDTO> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	@GetMapping("/{restaurantId}/menu")
	public List<MenuDTO> getMenuByRestaurantId(@PathVariable Long restaurantId) {
		return restaurantService.viewMenu(restaurantId);
	}

	@GetMapping("/showAllRestaurants")
	public List<Restaurant> getAllRestaurant() {
		return restaurantService.getAllRestaurant();
	}

}
