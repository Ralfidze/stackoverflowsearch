package ru.ralfidze.search.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import ru.ralfidze.search.model.Item;
import ru.ralfidze.search.service.interfaces.ItemServiceI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    public ItemServiceI itemService;

    @CrossOrigin
    @RequestMapping("/search")
    public ResponseEntity<?> getSearchValue(@RequestParam(name = "searchValue") String searchValue) throws IOException, URISyntaxException {

        if (searchValue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No data.");
        }

        return ResponseEntity.ok(itemService.getItems(searchValue));
    }
}
