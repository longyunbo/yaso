package com.drag.yaso.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.drag.yaso.user.entity.UserTicketDetail;


public interface UserTicketDetailDao extends JpaRepository<UserTicketDetail, String>, JpaSpecificationExecutor<UserTicketDetail> {
	
	@Query(value = "select * from t_user_ticket_detail where ticket_id = ?1 order by create_time desc", nativeQuery = true)
	List<UserTicketDetail> findByTicketId(int ticketid);
	
}
