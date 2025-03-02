package com.example.restaurant.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.DTO.MenuDTO;
import com.example.restaurant.DTO.RestaurantDTO;
import com.example.restaurant.Model.Menu;
import com.example.restaurant.Model.Restaurant;
import com.example.restaurant.Repository.MenuRepo;
import com.example.restaurant.Repository.RestaurantRepo;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepo restaurantRepository;
	@Autowired
	private MenuRepo menuRepo;

	//Add the menu with Parameters
	public Menu createMenu(Long restaurantId, String name, String description,String category, double price, Boolean availability) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new ObjectNotFoundException(Restaurant.class, "Restaurant not found"));
		Menu menu = new Menu();
		menu.setName(name);
		menu.setDescription(description);
		menu.setCategory(category);
		menu.setPrice(price);
		menu.setAvailability(availability);
		menu.setRestaurant(restaurant);
		restaurant.getMenu().add(menu);
		restaurantRepository.save(restaurant);
		return menu;
	}

	//Add the menu with JSON
	public Menu createMenu(Long restaurantId, Menu menu) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new ObjectNotFoundException(Restaurant.class, "Restaurant not found"));
		menu.setRestaurant(restaurant);
		restaurant.getMenu().add(menu);
		restaurantRepository.save(restaurant);
		return menu;
	}

	//Update the menu with Parameters
	public Menu updateMenu(Long itemId, String newName, String newDescription,String category, double newPrice, Boolean newAvailability) {
		// Implementation for updating a menu item
		Menu menu = menuRepo.findById(itemId).orElseThrow(()->new ObjectNotFoundException(Menu.class, "Menu item not found"));
		menu.setName(newName);
		menu.setDescription(newDescription);
		menu.setCategory(category);
		menu.setPrice(newPrice);
		menu.setAvailability(newAvailability);
		return menuRepo.save(menu);
	}

	//Update the menu with JSON
	public Menu updateMenu(Menu menu, Long itemId){
		return menuRepo.save(menuRepo.findById(itemId).map(item->{
			item.setName(menu.getName());
			item.setDescription(menu.getDescription());
			item.setCategory(menu.getCategory());
			item.setPrice(menu.getPrice());
			item.setAvailability(menu.getAvailability());
			return item;
		}).orElseGet(()->{
			menu.setItemId(itemId);
			return menu;
		}));
	}

	// Implementation for deleting a menu item
	public void deleteMenu(Long itemId) {
		menuRepo.findById(itemId).orElseThrow(()->new ObjectNotFoundException(Menu.class, "Menu item not found"));
		menuRepo.deleteById(itemId);
	}

	//Add a new Restaurant
	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	//Get the Restaurant by Id
	public Restaurant getRestaurantById(Long restaurantId) {
		return restaurantRepository.findById(restaurantId).orElseThrow(()->new ObjectNotFoundException(Restaurant.class, "Restaurant not found"));
	}

	//Gets all the Restaurants with Menu
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}

	//Update the Restaurant by Id
	public Restaurant updateRestaurantById(Long id, Restaurant restaurant){
		return restaurantRepository.save(restaurantRepository.findById(id).map(r->{
			r.setName(restaurant.getName());
			return r;
		}).orElseThrow(()-> new RuntimeException("Restaurant not found")));
	}

	//Gets all the Restaurants without Menu
	public List<RestaurantDTO> getAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants.stream()
				.map(r -> new RestaurantDTO(r.getRestaurantId(), r.getName()))
				.collect(Collectors.toList());
	}

	//View the Menu by Restaurant Id
	public List<MenuDTO> viewMenu(Long restaurantId) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RuntimeException("Restaurant not found"));
		List<Menu> menus = menuRepo.findByRestaurant(restaurant);
		return menus.stream()
				.map(menu -> new MenuDTO(
						menu.getItemId(),
						menu.getName(),
						menu.getCategory(),
						menu.getDescription(),
						menu.getPrice(),
						menu.getAvailability()
						))
				.collect(Collectors.toList());
	}

	//Delete the Restaurant by Id
	public String deleteRestaurant(Long RepositoryId) {
		restaurantRepository.deleteById(RepositoryId);
		return "Restaurant deleted successfully";
	}

    public Menu findMenuById(Long itemId) {
        return menuRepo.findMenuByItemId(itemId);
    }

    public List<Menu> getMenuItemsByRestaurantId(Long restaurantId) {
		return menuRepo.findByRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(()->new ObjectNotFoundException(Restaurant.class, "Restaurant not found")));
	}
}


