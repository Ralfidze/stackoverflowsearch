package ru.ralfidze.search.service;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import ru.ralfidze.search.model.Item;
import ru.ralfidze.search.service.constants.Constants;
import ru.ralfidze.search.service.interfaces.ItemServiceI;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Service
public class ItemServiceImpl implements ItemServiceI {

    @Override
    public List<Item> getItems(String title) throws IOException, URISyntaxException {

        URI uriBuilder = new URIBuilder(Constants.STACKOVERFLOW_SEARCH_API)
                                                 .setParameter(Constants.ORDER_PARAMETER,Constants.ORDER_VALUE)
                                                 .setParameter(Constants.SORT_PARAMETER,Constants.SORT_VALUE)
                                                 .setParameter(Constants.INTITLE_PARAMETER,title)
                                                 .setParameter(Constants.SITE_PARAMETER, Constants.SITE_VALUE)
                                                 .build();

       HttpClient client = HttpClientBuilder.create().build();

       HttpGet request = new HttpGet(uriBuilder);
       request.setHeader("Content-Type","application/json");;
       HttpResponse response = client.execute(request);

       HttpEntity entity = response.getEntity();

       String json = EntityUtils.toString(entity,StandardCharsets.UTF_8);
       ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
       mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
       JsonNode root = mapper.readTree(json);
       JsonNode jsonItems = root.path("items");
       List<Item> items =  mapper.readerFor(new TypeReference<List<Item>>(){})
                                           .readValue(jsonItems);
       return items;
    }
}
