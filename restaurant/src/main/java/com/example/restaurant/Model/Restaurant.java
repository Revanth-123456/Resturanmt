package com.example.restaurant.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="restaurant")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurantId;
	@NotBlank(message = "Name cannot be blank")private String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant",orphanRemoval = true)
	@JsonIgnoreProperties("restaurant")
	@JsonManagedReference
	private List<Menu> menu= new ArrayList<>();


	public Long getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Menu> getMenu() {
		return menu;
	}


	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

}
