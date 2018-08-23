package com.drag.yaso.wm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.drag.yaso.wm.entity.OrderShipper;



public interface OrderShipperDao extends JpaRepository<OrderShipper, String>, JpaSpecificationExecutor<OrderShipper> {
	
	
	
}
