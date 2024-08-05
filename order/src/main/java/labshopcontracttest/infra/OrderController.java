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

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.inventory}")
    private String apiUrl;

    @RequestMapping(
        value = "orders/order",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order order(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody Order order
    ) throws Exception {
        System.out.println("##### /order/order  called #####");
        order.order(ordercommand);
        orderRepository.save(order);
        return order;
    }

    // consumer

    @GetMapping("/order/validateInventory/search/findByGetInventory")
    public ResponseEntity<String> inventoryStockCheck() {
        String inventoryUrl = apiUrl + "/inventories/search/findByGetInventory";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> inventoryEntity = restTemplate.exchange(
            inventoryUrl,
            HttpMethod.GET,
            entity,
            String.class
        );

        return inventoryEntity;
    }
    // Provider

}
