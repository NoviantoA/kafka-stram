package com.noviantoanggoro.kafkastream.order.repository;

import com.noviantoanggoro.kafkastream.order.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}
