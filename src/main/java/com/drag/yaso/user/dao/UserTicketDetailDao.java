package com.drag.yaso.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.drag.yaso.user.entity.UserTicketDetail;


public interface UserTicketDetailDao extends JpaRepository<UserTicketDetail, String>, JpaSpecificationExecutor<UserTicketDetail> {
	
	
	
}
