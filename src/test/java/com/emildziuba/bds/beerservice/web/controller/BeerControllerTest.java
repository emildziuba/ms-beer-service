package com.emildziuba.bds.beerservice.web.controller;

import com.emildziuba.bds.beerservice.web.model.BeerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void getBeer() throws Exception {
        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder().build();
        String beerJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = BeerDto.builder().build();
        String beerJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/{beerId}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isNoContent());
    }
}