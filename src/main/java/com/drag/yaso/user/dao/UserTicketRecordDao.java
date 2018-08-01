package com.drag.yaso.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.drag.yaso.user.entity.UserTicketRecord;


public interface UserTicketRecordDao extends JpaRepository<UserTicketRecord, String>, JpaSpecificationExecutor<UserTicketRecord> {
	
	
	
}
