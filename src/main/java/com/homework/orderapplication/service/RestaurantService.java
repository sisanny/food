package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.RestaurantDTO;
import com.homework.orderapplication.exception.CustomException;
import com.homework.orderapplication.model.Restaurant;
import com.homework.orderapplication.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    public RestaurantDTO mapRestaurantDTOById(Long id) {
        Restaurant restaurant = getRestaurantById(id);
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new CustomException("Restaurant with id " + id + " not found", HttpStatus.NOT_FOUND));
    }

    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
    }
}
