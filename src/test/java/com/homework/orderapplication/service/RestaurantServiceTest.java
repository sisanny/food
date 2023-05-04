package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.RestaurantDTO;
import com.homework.orderapplication.model.Restaurant;
import com.homework.orderapplication.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository, modelMapper);
    }

    @Test
    public void testMapRestaurantDTOById() {
        Long id = 1L;
        Restaurant restaurant = new Restaurant();
        RestaurantDTO expectedDTO = new RestaurantDTO();
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));
        when(modelMapper.map(restaurant, RestaurantDTO.class)).thenReturn(expectedDTO);

        RestaurantDTO resultDTO = restaurantService.mapRestaurantDTOById(id);

        assertEquals(expectedDTO, resultDTO);
        verify(restaurantRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(restaurant, RestaurantDTO.class);
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant(), new Restaurant(), new Restaurant());
        List<RestaurantDTO> expectedDTOs = Arrays.asList(new RestaurantDTO(), new RestaurantDTO(), new RestaurantDTO());
        when(restaurantRepository.findAll()).thenReturn(restaurants);
        when(modelMapper.map(any(), eq(RestaurantDTO.class))).thenReturn(new RestaurantDTO());

        List<RestaurantDTO> resultDTOs = restaurantService.getAllRestaurants();

        assertEquals(expectedDTOs.size(), resultDTOs.size());
        verify(restaurantRepository, times(1)).findAll();
        verify(modelMapper, times(restaurants.size())).map(any(), eq(RestaurantDTO.class));
    }

    @Test
    public void testGetRestaurantByIdFound() {
        Long id = 1L;
        Restaurant expectedRestaurant = new Restaurant();
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(expectedRestaurant));

        Restaurant resultRestaurant = restaurantService.getRestaurantById(id);

        assertEquals(expectedRestaurant, resultRestaurant);
        verify(restaurantRepository, times(1)).findById(id);
    }
}
