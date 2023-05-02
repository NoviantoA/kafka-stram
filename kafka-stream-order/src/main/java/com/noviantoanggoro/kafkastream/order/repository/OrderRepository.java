package com.noviantoanggoro.kafkastream.order.repository;

import com.noviantoanggoro.kafkastream.order.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
