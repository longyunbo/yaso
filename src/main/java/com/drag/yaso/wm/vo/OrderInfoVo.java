package com.drag.yaso.wm.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品信息
 * @author longyunbo
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OrderInfoVo implements Serializable {
	
	private static final long serialVersionUID = 4674677983620486166L;
	private int id;
	/**
	 * 订单编号
	 */
	private String orderid;
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
	 * 商品图片
	 */
	private String goodsImg;
	/**
	 * 用户编号
	 */
	private int uid;
	/**
	 * 买家姓名
	 */
	private String buyName;
	/**
	 * 买家电话
	 */
	private String phone;
	/**
	 * 商品总数量
	 */
	private int number;
	/**
	 * 消耗积分
	 */
	private int score;
	/**
	 * 购买规格
	 */
	private String norms;
	/**
	 * 订单状态，0:已付款,  1:已退款
	 */
	private int orderstatus;
	/**
	 * 送餐状态，0：派单中，3：已转单，5：取餐中，10：已到店，15：配送中，98：异常，99：已取消，100：已到达
	 */
	private int deliverystatus;
	/**
	 * 收货人姓名
	 */
	private String receiptName;
	/**
	 * 收货人电话
	 */
	private String receiptTel;
	/**
	 * 收货区域信息
	 */
	private String region;
	/**
	 * 邮政编码
	 */
	private String postalcode;
	/**
	 * 收货信息地址
	 */
	private String receiptAddress;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否开票，0-否，1-是
	 */
	private int isBilling;
	/**
	 * 开票类型
	 */
	private String billingType;
	/**
	 * 发票抬头
	 */
	private String invPayee;
	/**
	 * 发票内容
	 */
	private String invContent;
	/**
	 * 发票电话
	 */
	private String invTel;
	/**
	 * 发票人姓名
	 */
	private String invName;
	/**
	 * 发票邮箱
	 */
	private String invEmail;
	/**
	 * 开票时间
	 */
	private String billTime;
	/**
	 * 确认收货时间
	 */
	private String confirmReceiptTime;
	/**
	 * 退款单号
	 */
	private String refundcode;
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
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 修改时间
	 */
	private String updateTime;

}
