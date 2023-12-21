package edu.miu.cs.cs544.dto.response;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Product;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private Integer id;
    private Integer occupants;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer productId;

    public static ItemResponse from(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setOccupants(item.getOccupants());
        itemResponse.setCheckinDate(item.getCheckinDate());
        itemResponse.setCheckoutDate(item.getCheckoutDate());
        return itemResponse;
    }

    public static Item to(ItemResponse itemResponse) {
        Item item = new Item();
        item.setId(itemResponse.getId());
        item.setOccupants(itemResponse.getOccupants());
        item.setCheckinDate(itemResponse.getCheckinDate());
        item.setCheckoutDate(itemResponse.getCheckoutDate());
        return item;
    }

}
