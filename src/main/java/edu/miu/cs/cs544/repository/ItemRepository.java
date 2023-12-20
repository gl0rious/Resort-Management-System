package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
