package com.drag.yaso.wm.form;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 订单form
 * @author longyunbo
 *
 */
@Data
public class OrderInfoForm {
	
	/**
	 * 订单号
	 */
	private String orderid;
	/**
	 * 用户id
	 */
	private String openid;
	/**
	 * 商品类型,rx-热销,zk-折扣,xy-鲜鸭,ms-美素
	 */
	private String type;
	/**
	 * 商品编号
	 */
	private int goodsId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 总数量
	 */
	private int number;
	/**
	 * 总金额
	 */
	private BigDecimal price;
	/**
	 * 规格
	 */
	private String norms;
	/**
	 * 买家姓名
	 */
	private String buyName;
	/**
	 * 买家手机号
	 */
	private String phone;
	/**
	 * 收货人
	 */
	private String receiptName;
	/**
	 * 收货人联系方式
	 */
	private String receiptTel;
	/**
	 * 所在区域
	 */
	private String region;
	/**
	 * 邮政编码
	 */
	private String postalcode;
	/**
	 * 市级
	 */
	private String cityName;
	/**
	 * 地址
	 */
	private String receiptAddress;
	/**
	 * formId
	 */
	private String formId;
	/**
	 * 好评状态,0:好评,1:中评,2:差评
	 */
	private int commentstatus;
	/**
	 * 好评星数
	 */
	private int commentlevel;
	/**
	 * 商品评论
	 */
	private String comment;
	/**
	 * 订单详情
	 */
	List<OrderDetailForm> orderDetail;
}
