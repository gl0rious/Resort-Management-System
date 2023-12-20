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

        @ManyToOne
        private Product product;

    public static ItemDTO from(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setOccupants(item.getOccupants());
        itemDTO.setCheckinDate(item.getCheckinDate());
        itemDTO.setCheckoutDate(item.getCheckoutDate());

        return itemDTO;
    }

    public Item to() {
        Item item = new Item();
        item.setId(this.getId());
        item.setOccupants(this.getOccupants());
        item.setCheckinDate(this.getCheckinDate());
        item.setCheckoutDate(this.getCheckoutDate());

        item.setProduct(this.getProduct());

        return item;
    }


}
