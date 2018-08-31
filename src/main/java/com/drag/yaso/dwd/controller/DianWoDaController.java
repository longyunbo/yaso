package com.drag.yaso.dwd.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.drag.yaso.dwd.form.CallBackForm;
import com.drag.yaso.dwd.service.DianWoDaService;
import com.drag.yaso.wm.form.OrderInfoForm;
import com.drag.yaso.wm.resp.OrderResp;


@RestController
@RequestMapping(value = "/yaso/dwd")
public class DianWoDaController {
	
	@Autowired
	DianWoDaService dianWoDaService;
	private final static Logger log = LoggerFactory.getLogger(DianWoDaController.class);
	
	@RequestMapping(value = "/ordersend", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<OrderResp> getUser() {
		OrderInfoForm form = new OrderInfoForm();
		OrderResp resp = dianWoDaService.orderSend(form);
		return new ResponseEntity<OrderResp>(resp, HttpStatus.OK);
	}
	
	/**
	 * 获取订单信息
	 * @param order_original_id
	 * @return
	 */
	@RequestMapping(value = "/orderget", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<JSONObject> orderGet(@RequestParam String order_original_id) {
		JSONObject Json = dianWoDaService.orderGet(order_original_id);
		return new ResponseEntity<JSONObject>(Json, HttpStatus.OK);
	}
	
	/**
	 * 点我达回调函数
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/callback", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody ResponseEntity<JSONObject> callback(@RequestBody CallBackForm form) {
		JSONObject Json = dianWoDaService.callback(form);
		return new ResponseEntity<JSONObject>(Json, HttpStatus.OK);
	}
	
}
