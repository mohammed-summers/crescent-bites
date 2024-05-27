package com.mks.crescentbites.controller;

import com.mks.crescentbites.dto.RestaurantDto;
import com.mks.crescentbites.service.RestaurantService;
import com.mks.crescentbites.service.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantServiceImpl restaurantServiceImpl;

    @Test
    public void shouldGetListOfRestaurant() throws Exception {
        when(restaurantService.getAllRestaurants()).thenReturn(List.of(
                new RestaurantDto("res1", "des1", "add1", "cit2", "TX", "111", "arb"
                        , 1, "men1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()))
        ));

        String jsonResponse = mockMvc.perform(get("/api/v1/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }
}
