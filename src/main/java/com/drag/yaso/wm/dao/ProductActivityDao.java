package com.drag.yaso.wm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.drag.yaso.wm.entity.ProductActivity;



public interface ProductActivityDao extends JpaRepository<ProductActivity, String>, JpaSpecificationExecutor<ProductActivity> {
	
	
	
}
