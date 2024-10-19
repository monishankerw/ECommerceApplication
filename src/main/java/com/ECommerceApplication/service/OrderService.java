package com.ECommerceApplication.service;

import com.ECommerceApplication.dto.OrderDTO;
import com.ECommerceApplication.payload.OrderResponse;

import java.util.List;



public interface OrderService {
	
	OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod);
	
	OrderDTO getOrder(String emailId, Long orderId);
	
	List<OrderDTO> getOrdersByUser(String emailId);
	
	OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	OrderDTO updateOrder(String emailId, Long orderId, String orderStatus);
}