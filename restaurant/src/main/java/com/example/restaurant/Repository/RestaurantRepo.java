package com.example.restaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.DTO.RestaurantDTO;
import com.example.restaurant.Model.Restaurant;


public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    RestaurantDTO findByRestaurantId(Long restaurantId);
}
