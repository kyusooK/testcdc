package labshopcontracttest.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopcontracttest.OrderApplication;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String productId;

    private String productName;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    //<<< Clean Arch / Port Method
    public void order(OrderCommand orderCommand) {
        //implement business logic here:

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

        labshopcontracttest.external.GetInventoryQuery getInventoryQuery = new labshopcontracttest.external.GetInventoryQuery();
        getInventoryQuery.setId(1L);

        OrderApplication.applicationContext
            .getBean(labshopcontracttest.external.InventoryService.class)
            .testInventory(getInventoryQuery.getId(), getInventoryQuery);
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
