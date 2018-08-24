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
	 * 优惠金额
	 */
	private BigDecimal dicprice;
	/**
	 * 订单总金额
	 */
	private BigDecimal tolprice;
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
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
	 * 评论图片
	 */
	private String commentimg;
	
	/**
	 * -------点我达所需参数------------
	 */
	/**
	 * 订单备注
	 */
	private String order_remark;
	/**
	 * 行政区划代码 
	 */
	private String city_code;
	/**
	 * 商家编号
	 */
	private String seller_id;
	/**
	 * 商家店铺名称
	 */
	private String seller_name;
	/**
	 * 商家联系方式
	 */
	private String seller_mobile;
	/**
	 * 商家文字地址
	 */
	private String seller_address;
	/**
	 * 商家纬度坐标.(坐标系为高德地图坐标系，又称火星坐标). （单位：度）
	 */
	private Double seller_lat;
	/**
	 * 商家经度坐标.(坐标系为高德地图坐标系，又称火星坐标) （单位：度）
	 */
	private Double seller_lng;
	/**
	 * 收货人纬度坐标.(坐标系为高德地图坐标系，又称火星坐标)
	 */
	private Double consignee_lat;
	/**
	 * 收货人经度坐标.(坐标系为高德地图坐标系，又称火星坐标)
	 */
	private Double consignee_lng;
	
	
	
	
	
	
	/**
	 * 订单详情
	 */
	List<OrderDetailForm> orderDetail;
}
