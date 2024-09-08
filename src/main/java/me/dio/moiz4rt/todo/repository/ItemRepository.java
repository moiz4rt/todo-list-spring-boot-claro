package me.dio.moiz4rt.todo.repository;

import me.dio.moiz4rt.todo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
{
    
}
