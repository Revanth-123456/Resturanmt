package com.example.restaurant.Repository;
import com.example.restaurant.Model.Menu;
import com.example.restaurant.Model.Restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    List<Menu> findByCategory(String category);
    Menu findMenuByItemId(Long itemId);
    List<Menu> findByName(String name);
    List<Menu> findByRestaurant(Restaurant restaurant);
}
