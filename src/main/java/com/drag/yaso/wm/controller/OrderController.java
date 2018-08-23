package com.drag.yaso.wm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.drag.yaso.wm.form.OrderInfoForm;
import com.drag.yaso.wm.resp.OrderResp;
import com.drag.yaso.wm.service.OrderService;
import com.drag.yaso.wm.vo.OrderDetailVo;
import com.drag.yaso.wm.vo.OrderInfoVo;



@RestController
@RequestMapping(value = "/yaso/order")
public class OrderController {
	
	private final static Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderInfoService;
	
	/**
	 * 外卖购买下单
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/purchase", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<OrderResp> purchase(@RequestBody OrderInfoForm form) {
		OrderResp detailVo = orderInfoService.purchase(form);
		return new ResponseEntity<OrderResp>(detailVo, HttpStatus.OK);
	}
	
	
	/**
	 * 获取我的订单
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/myorders", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<List<OrderInfoVo>> myorders(@RequestParam(required = true)  String openid,@RequestParam String type) {
		List<OrderInfoVo> list = orderInfoService.myOrders(openid,type);
		return new ResponseEntity<List<OrderInfoVo>>(list, HttpStatus.OK);
	}
	
	/**
	 * 订单详情
	 * @param orderid
	 * @return
	 */
	@RequestMapping(value = "/orderdetail", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<List<OrderDetailVo>> orderDetail(@RequestParam(required = true)  String orderid) {
		List<OrderDetailVo> list = orderInfoService.orderDetail(orderid);
		return new ResponseEntity<List<OrderDetailVo>>(list, HttpStatus.OK);
	}
	
	/**
	 * 外卖评论
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/comment", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<OrderResp> comment(@RequestBody OrderInfoForm form) {
		OrderResp detailVo = orderInfoService.comment(form);
		return new ResponseEntity<OrderResp>(detailVo, HttpStatus.OK);
	}
	
}
