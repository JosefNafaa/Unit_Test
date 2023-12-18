package com.unitTests.unittesting.controller;

import com.unitTests.unittesting.business.ItemBusinessService;
import com.unitTests.unittesting.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService itemBusinessService;
    @Test
    public void dummyItem_basic() throws Exception {

        //call -dummy-item
        RequestBuilder request= MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);
        //verify
        MvcResult result= mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                .andReturn();
        //JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);



    }
    @Test
    public void itemFromBusinessService_basic() throws Exception {

         when(itemBusinessService.retreiveHardcodedItem()).
                 thenReturn(new Item(2,"Tennis",150,10));
        //call -dummy-item
        RequestBuilder request= MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);
        //verify
        MvcResult result= mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\": 2,\"name\":\"Tennis\",\"price\":150,\"quantity\":10}"))
                .andReturn();
        //JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);



    }
    @Test
    public void retrieveAllItems_basic() throws Exception {
        // Sample data
        Item item1 = new Item(10001,"Item1",10,20);
        Item item2 = new Item(10002,"Item2",5,10);
        Item item3 = new Item(10003,"Item3",15,2);
        List<Item> itemList = Arrays.asList(item1, item2,item3);

        // Mocking the behavior of itemBusinessService.retrieveAllItems()
        when(itemBusinessService.retrieveAllItems()).thenReturn(itemList);

        //call -dummy-item
        RequestBuilder request= MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);
        //verify
        MvcResult result= mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))) // Assuming 3 items are returned
                .andExpect(content().json("[{\"id\":10001,\"name\":\"Item1\",\"price\":10,\"quantity\":20},{\"id\":10002,\"name\":\"Item2\",\"price\":5,\"quantity\":10},{\"id\":10003,\"name\":\"Item3\",\"price\":15,\"quantity\":2}]"))
                .andReturn();


    }
    @Test
    public void retrieveAllItems_noitems() throws Exception {
        when(itemBusinessService.retrieveAllItems()).thenReturn(
                Arrays.asList()
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[]"))
                .andReturn();
        //JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

}