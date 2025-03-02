package com.example.restaurant.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
	private Long restId;
	private String name;
	public RestaurantDTO(Long restaurantId, String name) {
		this.restId=restaurantId;
		this.name= name;
	}
	public Long getRestId() {
		return restId;
	}
	public void setRestId(Long restId) {
		this.restId = restId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
