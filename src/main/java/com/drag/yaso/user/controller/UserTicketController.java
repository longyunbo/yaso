package com.drag.yaso.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.drag.yaso.user.resp.UserTicketResp;
import com.drag.yaso.user.service.UserTicketService;
import com.drag.yaso.user.vo.UserTicketTemplateVo;
import com.drag.yaso.user.vo.UserTicketVo;
import com.drag.yaso.wm.form.OrderInfoForm;
import com.drag.yaso.wm.resp.OrderResp;


@RestController
@RequestMapping(value = "/yaso/ticket")
@CrossOrigin
public class UserTicketController {
	
	private final static Logger log = LoggerFactory.getLogger(UserTicketController.class);

	@Autowired
	private UserTicketService userTicketService;
	
	/**
	 * 获取卡券列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<List<UserTicketVo>> listTicket(@RequestParam(required = true) String openid) {
		List<UserTicketVo> rows= userTicketService.listTicket(openid);
		return new ResponseEntity<List<UserTicketVo>>(rows, HttpStatus.OK);
	}
	
	/**
	 * 核销卡券
	 * @param ticketId
	 * @return
	 */
	@RequestMapping(value = "/destory", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<UserTicketResp> destory(@RequestParam(required = true) int ticketId) {
		UserTicketResp resp = userTicketService.destoryTicket(ticketId);
		return new ResponseEntity<UserTicketResp>(resp, HttpStatus.OK);
	}
	
	/**
	 * 获取礼品卡模板
	 * @return
	 */
	@RequestMapping(value = "/listgift", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<List<UserTicketTemplateVo>> listGift() {
		List<UserTicketTemplateVo> rows= userTicketService.listGift("lpk");
		return new ResponseEntity<List<UserTicketTemplateVo>>(rows, HttpStatus.OK);
	}
	
	
	/**
	 * 礼品卡购买
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/purchase", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<OrderResp> purchase(@RequestBody OrderInfoForm form) {
		OrderResp detailVo = userTicketService.purchase(form);
		return new ResponseEntity<OrderResp>(detailVo, HttpStatus.OK);
	}
}
