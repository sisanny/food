package com.homework.orderapplication.controller;

import com.homework.orderapplication.dto.ItemDTO;
import com.homework.orderapplication.dto.RestaurantDTO;
import com.homework.orderapplication.exception.CustomError;
import com.homework.orderapplication.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurants")
@Api(value = "Restaurant", tags = {"Restaurant"})
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ApiOperation(value = "Get all restaurants", notes = "Retrieve all restaurants")
    @GetMapping()
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @ApiOperation(value = "Get a restaurant by ID", notes = "Retrieve a restaurant by its ID")
    @ApiResponse(code = 404, message = "Restaurant not found", response = CustomError.class)
    @GetMapping(path = "/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable("id") @Positive Long id) {
        RestaurantDTO restaurant = restaurantService.mapRestaurantDTOById(id);
        return ResponseEntity.ok(restaurant);
    }

    @ApiOperation(value = "Get the menu of a restaurant by ID", notes = "Retrieve the menu of a restaurant by its ID")
    @ApiResponse(code = 404, message = "Restaurant not found", response = CustomError.class)
    @GetMapping(path = "/{id}/menu")
    public ResponseEntity<List<ItemDTO>> getMenuOfRestaurantById(@PathVariable("id") @Positive Long id) {
        List<ItemDTO> menu = restaurantService.mapRestaurantDTOById(id).getMenu();
        return ResponseEntity.ok(menu);
    }
}
