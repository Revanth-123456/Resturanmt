package com.example.restaurant.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@Entity
@AllArgsConstructor
@Table(name = "menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	@NotBlank private String name;
	@NotBlank private String description;
	@NotNull private double price;
	@NotNull private Boolean availability;
	@NotBlank private String category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id")
	@JsonBackReference
	private Restaurant restaurant;

	public Menu() {};

	public Menu(Long id, String name, String category, String description, double price, Boolean availability) {
		this.itemId = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.availability = availability;
	}

	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setRestaurant(Restaurant restaurant2) {
		this.restaurant = restaurant2;

	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setAvailability(Boolean availability2) {
		this.availability = availability2;
	}

}
