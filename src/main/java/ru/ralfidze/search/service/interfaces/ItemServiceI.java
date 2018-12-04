package ru.ralfidze.search.service.interfaces;

import ru.ralfidze.search.model.Item;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ItemServiceI {
    List<Item> getItems(String title) throws IOException, URISyntaxException;
}
