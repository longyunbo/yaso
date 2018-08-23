package com.drag.yaso.wm.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.drag.yaso.common.Constant;
import com.drag.yaso.common.exception.AMPException;
import com.drag.yaso.user.dao.UserDao;
import com.drag.yaso.user.entity.User;
import com.drag.yaso.utils.BeanUtils;
import com.drag.yaso.utils.DateUtil;
import com.drag.yaso.utils.StringUtil;
import com.drag.yaso.wm.dao.OrderDetailDao;
import com.drag.yaso.wm.dao.OrderInfoDao;
import com.drag.yaso.wm.dao.OrderShipperDao;
import com.drag.yaso.wm.dao.ProductInfoDao;
import com.drag.yaso.wm.entity.OrderDetail;
import com.drag.yaso.wm.entity.OrderInfo;
import com.drag.yaso.wm.entity.OrderShipper;
import com.drag.yaso.wm.entity.ProductInfo;
import com.drag.yaso.wm.form.OrderDetailForm;
import com.drag.yaso.wm.form.OrderInfoForm;
import com.drag.yaso.wm.resp.OrderResp;
import com.drag.yaso.wm.vo.OrderDetailVo;
import com.drag.yaso.wm.vo.OrderInfoVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderDetailDao orderDetailDao;
	@Autowired
	private OrderShipperDao orderShipperDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductInfoDao productInfoDao;
	/**
	 * 外卖购买下单
	 * @param form
	 * @return
	 */
	@Transactional
	public OrderResp purchase(OrderInfoForm form) {
		log.info("【外卖下单传入参数】:{}",JSON.toJSONString(form));
		OrderResp resp = new OrderResp();
		try {
			String orderid = StringUtil.uuid();
			int goodsId = form.getGoodsId();
			String goodsName = form.getGoodsName();
			String type = form.getType();
			//购买总数量
			int number = form.getNumber();
			//消耗总金额
			BigDecimal price = form.getPrice();
			String openid = form.getOpenid();
			String buyName = form.getBuyName();
			String phone = form.getPhone();
			//收货人
			String receiptName = form.getReceiptName();
			//收货人联系方式
			String receiptTel = form.getReceiptTel();
			//所在区域
			String region = form.getRegion();
			//邮政编码
			String postalcode = form.getPostalcode();
			//地址
			String receiptAddress  = form.getReceiptAddress();
			User user = userDao.findByOpenid(openid);
			ProductInfo goods = productInfoDao.findGoodsDetail(goodsId);
			if(user != null) {
				int uid = user.getId();
				//验证参数
				resp = this.checkParam(user,goods,form);
				String returnCode = resp.getReturnCode();
				if(!returnCode.equals(Constant.SUCCESS)) {
					return resp;
				}
				//插入订单表
				OrderInfo order = new OrderInfo();
				order.setId(order.getId());
				order.setOrderid(orderid);
				order.setGoodsId(goodsId);
				order.setGoodsName(goodsName + "等商品");
				order.setGoodsImg(goods.getGoodsImgs());
				order.setType(type);
				order.setNumber(number);
				order.setPrice(price);
				order.setOrderstatus(OrderInfo.ORDERSTATUS_SUCCESS);
				order.setUid(uid);
				order.setBuyName(buyName);
				order.setPhone(phone);
				order.setDeliverystatus(OrderInfo.STATUS_PD);
				order.setReceiptName(receiptName);
				order.setReceiptTel(receiptTel);
				order.setRegion(region);
				order.setPostalcode(postalcode);
				order.setReceiptAddress(receiptAddress);
				order.setCreateTime(new Timestamp(System.currentTimeMillis()));
				order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				orderInfoDao.save(order);
				
				//插入物流表
				OrderShipper shipper = new OrderShipper();
				shipper.setId(shipper.getId());
				shipper.setOrderid(orderid);
				shipper.setUid(uid);
				shipper.setReceiptName(receiptName);
				shipper.setReceiptTel(receiptTel);
				shipper.setReceiptAddress(receiptAddress);
				shipper.setCreateTime(new Timestamp(System.currentTimeMillis()));
				orderShipperDao.save(shipper);
				
				List<OrderDetailForm> orderList = form.getOrderDetail();
				if(orderList != null && orderList.size() > 0) {
					for(OrderDetailForm detail : orderList) {
						//插入订单详情
						int dGoodsId = detail.getGoodsId();
						String dGoodsName = detail.getGoodsName();
						String dNorms = detail.getNorms();
						int dNumber = detail.getNumber();
						BigDecimal dPrice  = detail.getPrice();
						OrderDetail orderDetail = new OrderDetail();
						orderDetail.setId(orderDetail.getId());
						orderDetail.setUid(uid);
						orderDetail.setOrderid(orderid);
						orderDetail.setGoodsId(dGoodsId);
						orderDetail.setGoodsName(dGoodsName);
						orderDetail.setNorms(dNorms);
						orderDetail.setPrice(dPrice);
						orderDetail.setNumber(dNumber);
						orderDetail.setType(type);
						orderDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
						orderDetail.setUpdateTime(new Timestamp(System.currentTimeMillis()));
						orderDetailDao.save(orderDetail);
					}
				}else {
					resp.setReturnCode(Constant.ORDERNOTEXISTS);
					resp.setErrorMessage("订单详情不存在，请添加商品!");
					log.error("【外卖商品下单订单参数错误】,{}",JSON.toJSONString(orderList));
					return resp;
				}
				
				//新增购买人数次数
				this.addSuccTimes(goods);
				
				resp.setReturnCode(Constant.SUCCESS);
				resp.setErrorMessage("下单成功!");
				
			}
		} catch (Exception e) {
			log.error("系统异常,{}",e);
			throw AMPException.getException("下单异常!");
		}
		return resp;
	}
	
	
	
	
	/**
	 * 订单详情
	 * @param orderid
	 * @return
	 */
	public List<OrderDetailVo> orderDetail(String orderid){
		log.info("【订单详情传入参数】:{}", orderid);
		List<OrderDetailVo> orderResp = new ArrayList<OrderDetailVo>();
		List<OrderDetail> details = orderDetailDao.findByOrderId(orderid);
		
		List<ProductInfo> products = productInfoDao.findAll();
		Map<Integer,ProductInfo> proMap = new HashMap<Integer,ProductInfo>();
		if(products != null && products.size() > 0) {
			for(ProductInfo pro : products) {
				proMap.put(pro.getGoodsId(), pro);
			}
		}
		if(details != null && details.size() > 0) {
			for (OrderDetail order : details) {
				OrderDetailVo vo = new OrderDetailVo();
				BeanUtils.copyProperties(order, vo,new String[]{"createTime", "updateTime","billTime"});
				int goodsid = order.getGoodsId();
				vo.setGoodsThumb(proMap.get(goodsid).getGoodsThumb());
				vo.setCreateTime((DateUtil.format(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));
				vo.setUpdateTime((DateUtil.format(order.getUpdateTime(), "yyyy-MM-dd HH:mm:ss")));
				orderResp.add(vo);
			}
		}
		return orderResp;
	}
	
	/**
	 * 获取个人订单
	 * @param openid
	 * @return
	 */
	public List<OrderInfoVo> myOrders(String openid,String type){
		log.info("【我的订单传入参数】:{}", openid);
		List<OrderInfoVo> orderResp = new ArrayList<OrderInfoVo>();
		User user = userDao.findByOpenid(openid);
		if(user != null) {
			int uid = user.getId();
			List<OrderInfo> orderList = null;
			if(!StringUtil.isEmpty(type)) {
				orderList = orderInfoDao.findByUidAndType(uid,type);
			}else {
				orderList = orderInfoDao.findByUid(uid);
			}
			for (OrderInfo order : orderList) {
				OrderInfoVo vo = new OrderInfoVo();
				BeanUtils.copyProperties(order, vo,new String[]{"createTime", "updateTime","billTime"});
				vo.setCreateTime((DateUtil.format(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));
				vo.setUpdateTime((DateUtil.format(order.getUpdateTime(), "yyyy-MM-dd HH:mm:ss")));
				orderResp.add(vo);
			}
		}
		return orderResp;
	}
	
	/**
	 * 外卖评论
	 * @param form
	 * @return
	 */
	@Transactional
	public OrderResp comment(OrderInfoForm form) {
		log.info("【外卖评价传入参数】:{}",JSON.toJSONString(form));
		OrderResp resp = new OrderResp();
		try {
			String orderid = form.getOrderid();
			OrderInfo order = orderInfoDao.findByOrderId(orderid);
			int commentstatus = form.getCommentstatus();
			int commentlevel = form.getCommentlevel();
			String comment = form.getComment();
			if(order != null) {
				order.setCommentstatus(commentstatus);
				order.setCommentlevel(commentlevel);
				order.setComment(comment);
				orderInfoDao.saveAndFlush(order);
			}
			resp.setReturnCode(Constant.SUCCESS);
			resp.setErrorMessage("评价成功!");
				
		} catch (Exception e) {
			log.error("系统异常,{}",e);
			throw AMPException.getException("评价异常!");
		}
		return resp;
	}
	
	
	/**
	 * 增加购买次数
	 * @param goods
	 * @param number
	 */
	public void addSuccTimes(ProductInfo goods) {
		int succTimes = goods.getSuccTimes();
		goods.setSuccTimes(succTimes + 1);
		productInfoDao.saveAndFlush(goods);
	}
	
	/**
	 * 验证参数
	 * @param user
	 * @param goods
	 * @param form
	 * @return
	 */
	public OrderResp checkParam(User user,ProductInfo goods,OrderInfoForm form) {
		OrderResp resp = new OrderResp();
		int goodsId = form.getGoodsId();
		String openid = form.getOpenid();
		if(user != null) {
			String phone = user.getMobile();
			String realName = user.getRealname();
			if(StringUtil.isEmpty(phone) && StringUtil.isEmpty(realName)) {
				resp.setReturnCode(Constant.USERINFO_OVER);
				resp.setErrorMessage("用户信息不完善!");
				log.error("【用户信息不完善】，openid={}",openid);
				return resp;
			}
		}else {
			resp.setReturnCode(Constant.USERNOTEXISTS);
			resp.setErrorMessage("用户不存在!");
			log.error("【用户不存在】，openid={}",openid);
			return resp;
		}
		if(goods == null) {
			resp.setReturnCode(Constant.PRODUCTNOTEXISTS);
			resp.setErrorMessage("商品不存在!");
			log.error("【商品不存在】，goodsId={}",goodsId);
			return resp;
		}
		resp.setReturnCode(Constant.SUCCESS);
		resp.setErrorMessage("验证通过！");
		return resp;
	}
	
}
