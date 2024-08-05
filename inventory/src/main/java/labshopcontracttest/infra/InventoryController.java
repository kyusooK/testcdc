package labshopcontracttest.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import labshopcontracttest.domain.GetInventoryQuery;
import labshopcontracttest.domain.Inventory;
import labshopcontracttest.domain.InventoryRepository;

@RestController
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String apiUrl;

    // consumer

    // Provider
    @GetMapping(path = "/inventories/search/findByTestInventory/{id}")
    public Inventory testInventory(@PathVariable("id") Long id, GetInventoryQuery getInventoryQuery) {
        return inventoryRepository.findByTestInventory(id, getInventoryQuery.getStock());
    }
}
