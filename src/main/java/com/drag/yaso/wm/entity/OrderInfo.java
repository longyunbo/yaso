package com.drag.yaso.wm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商品信息表
 * @author longyunbo
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_order_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderInfo implements Serializable {

	private static final long serialVersionUID = 8976480717545183113L;
	
	// 订单状态0-未付款，1-已付款，2-已退款
	public static final int ORDERSTATUS_UNPAY = 0;
	public static final int ORDERSTATUS_SUCCESS = 1;
	public static final int ORDERSTATUS_RETURN = 2;
	
	//送餐状态，0：派单中，3：已转单，5：取餐中，10：已到店，15：配送中，98：异常，99：已取消，100：已到达
	public static final int STATUS_PD = 0;
	public static final int STATUS_ZD = 3;
	public static final int STATUS_QC = 5;
	public static final int STATUS_DD = 10;
	public static final int STATUS_PS = 15;
	public static final int STATUS_YC = 98;
	public static final int STATUS_QX = 99;
	public static final int STATUS_OK = 100;
	
	public static final int ISBILLING_NO = 0;
	public static final int ISBILLING_YES = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * 商品编号(有机食品不需要传，其他的类型需传)
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
	 * 消耗金额
	 */
	private BigDecimal price;
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
	 * 确认收货时间
	 */
	private Date confirmReceiptTime;
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
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
