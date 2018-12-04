package ru.ralfidze.search.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.ralfidze.search.model.Item;
import ru.ralfidze.search.service.interfaces.ItemServiceI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @MockBean
    ItemServiceI itemServiceI;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSearchValueIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().reason("Required String parameter 'searchValue' is not present"))
                .andReturn();
    }

    @Test
    public void testGetItems() throws Exception {

        List<Item> testItems = getItems();

        when(itemServiceI.getItems(anyString()))
                .thenReturn(testItems);

        mockMvc.perform(MockMvcRequestBuilders.get("/search").param("searchValue", "someQuery").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(30)))
                .andExpect(jsonPath("$[0].is_answered", is(testItems.get(0).isAnswered())))
                .andExpect(jsonPath("$[20].title", is(testItems.get(20).getTitle())))
                .andExpect(jsonPath("$[15].creation_date", is(testItems.get(15).getCreationDate())))
                .andDo(print());
    }

    private  List<Item> getItems() {

        List<Item> itemList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Item item = new Item();
            item.setAnswered(Math.random() < 0.5);
            item.setTitle(RandomStringUtils.random(15, true, false));
            item.setCreationDate((int) (Math.random() * 1000));

            itemList.add(item);
        }
        return  itemList;
    }
}