package com.example.restaurant.Controller;

import com.example.restaurant.Model.Menu;
import com.example.restaurant.Model.Restaurant;
import com.example.restaurant.Service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/view")
public class RestaurantViewController {

    @Autowired
    private RestaurantService restaurantService;

    // Show restaurant management dashboard
    @GetMapping("/restaurant")
    public String showRestaurantManagement(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("companyName", "TastyTreatExpress");
        return "restaurant-management";
    }

    // Show all restaurants
    @GetMapping("/allRestaurants")
    public String showRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("newRestaurant", new Restaurant());
        return "restaurants";
    }

    @GetMapping("/addRestaurant")
    public String showAddRestaurantForm(Model model) {
        model.addAttribute("newRestaurant", new Restaurant());
        return "addRestaurant";
    }

    @GetMapping("/restaurants/{restaurantId}/menu")
public String showMenu(@PathVariable Long restaurantId, Model model) {
    Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
    if (restaurant == null) {
        return "redirect:/view/allRestaurants";
    }
    model.addAttribute("restaurant", restaurant);
    model.addAttribute("newMenu", new Menu());
    List<Menu> menuItems = restaurantService.getMenuItemsByRestaurantId(restaurantId); // New service method needed
    model.addAttribute("menuItems", menuItems);
    return "menu";
}

    // Add a new restaurant
    @PostMapping("/addRestaurant")
    public String addRestaurant(@ModelAttribute("newRestaurant") Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return "redirect:/view/allRestaurants";
    }

    // Show form to update a restaurant
    @GetMapping("/update-restaurant")
    public String showUpdateRestaurantForm(@RequestParam("id") Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        model.addAttribute("restaurant", restaurant);
        return "update-restaurant";
    }

    @PostMapping("/restaurants/{restaurantId}/update")
    public String updateRestaurant(@PathVariable Long restaurantId, @ModelAttribute Restaurant restaurant) {
        restaurantService.updateRestaurantById(restaurantId, restaurant);
        return "redirect:/view/allRestaurants";
    }

    @GetMapping("/addMenu/{restaurantId}")
public String addMenu(@PathVariable Long restaurantId, Model model) {
    model.addAttribute("newMenu", new Menu());
    model.addAttribute("restaurantId", restaurantId); 
    return "add-menu";
}
    
    // Add a new menu item
    @PostMapping("/restaurants/{restaurantId}/menu")
    public String addMenu(@PathVariable Long restaurantId, @ModelAttribute("newMenu") Menu menu) {
        restaurantService.createMenu(restaurantId, menu);
        return "redirect:/view/restaurants/" + restaurantId + "/menu"; 
    }
    
    // Show form to update a menu item
    @GetMapping("/menu/{itemId}/edit")
    public String showUpdateMenuForm(@PathVariable Long itemId, Model model) {
        Menu menu = restaurantService.findMenuById(itemId);
        model.addAttribute("menu", menu);
        return "update-menu";
    }

    // Update a menu item
    @PostMapping("/menu/{itemId}")
    public String updateMenu(@PathVariable Long itemId, @ModelAttribute("menu") Menu menu) {
        Menu existingMenu = restaurantService.findMenuById(itemId);
        if (existingMenu == null || existingMenu.getRestaurant() == null) {
            return "redirect:/view/allRestaurants";
        }
        restaurantService.updateMenu(menu, itemId);
        Long restaurantId = existingMenu.getRestaurant().getRestaurantId();
        return "redirect:/view/restaurants/" + restaurantId + "/menu";
    }

    @GetMapping("/menu/{itemId}")
    public String deleteMenu(@PathVariable Long itemId) {
        Menu menu = restaurantService.findMenuById(itemId);
        if (menu == null || menu.getRestaurant() == null) {
            return "redirect:/view/allRestaurants";
        }
        Long restaurantId = menu.getRestaurant().getRestaurantId();
        restaurantService.deleteMenu(itemId);
        return "redirect:/view/restaurants/" + restaurantId + "/menu";
    }
}