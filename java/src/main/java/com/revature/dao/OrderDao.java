package com.revature.dao;

import java.util.List;

import com.revature.beans.Order;

public interface OrderDao {
	public Long insertOrder(Order order);
	public List<Order> getAllSecurities();
	public Order selectOrderById(Long id);
	public Order updateOrder(Order s);
	public void removeOrderById(Long id);
}
