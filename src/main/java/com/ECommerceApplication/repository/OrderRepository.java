package com.ECommerceApplication.repository;

import java.util.List;

import com.ECommerceApplication.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o WHERE o.email = ?1 AND o.id = ?2")
	Order findOrderByEmailAndOrderId(String email, Long cartId);

	List<Order> findAllByEmail(String emailId);
	
}