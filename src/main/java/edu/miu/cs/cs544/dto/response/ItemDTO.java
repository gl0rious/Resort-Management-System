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
public class ItemDTO {
    private Integer id;
    private Integer occupants;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer productId;

    public static ItemDTO from(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setOccupants(item.getOccupants());
        itemDTO.setCheckinDate(item.getCheckinDate());
        itemDTO.setCheckoutDate(item.getCheckoutDate());
        return itemDTO;
    }

    public static Item to(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setOccupants(itemDTO.getOccupants());
        item.setCheckinDate(itemDTO.getCheckinDate());
        item.setCheckoutDate(itemDTO.getCheckoutDate());
        return item;
    }

}
