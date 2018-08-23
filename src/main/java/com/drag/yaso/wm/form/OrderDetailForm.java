package com.drag.yaso.wm.form;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 订单详情form
 * @author longyunbo
 *
 */
@Data
public class OrderDetailForm {
	/**
	 * 商品编号
	 */
	private int goodsId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 用户编号
	 */
//	private String openid;
	/**
	 * 购买规格
	 */
	private String norms;
	/**
	 * 商品数量
	 */
	private int number;
	/**
	 * 消耗金额
	 */
	private BigDecimal price;
//	/**
//	 * 支付积分(数量*消耗积分)
//	 */
//	private BigDecimal payScore;

}
