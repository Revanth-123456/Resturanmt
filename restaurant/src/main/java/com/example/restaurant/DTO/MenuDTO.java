package com.example.restaurant.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MenuDTO {
	private Long id;
	private String name;
	private String category;
	private String description;
	private double price;
	private Boolean availability;
	public MenuDTO(Long id, String name, String category, String description, Double price, Boolean availability) {
		this.id=id;
		this.name=name;
		this.category=category;
		this.description=description;
		this.price=price;
		this.availability=availability;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Boolean isAvailability() {
		return availability;
	}
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

}
