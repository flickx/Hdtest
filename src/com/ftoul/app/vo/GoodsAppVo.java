package com.ftoul.app.vo;

import java.util.List;

import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.manage.goods.vo.GoodsParamVo;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsUploadpic;

public class GoodsAppVo {
	private String id;
	private String title;
	private String price;
	private String picSrc;
	private String goodsType3;
	private String goodsPropTypeId;
	private String goodsBrandId;
	
	private Integer stock;//库存
	
	private String typeName;//活动类型
	private String homeChannel;
	
	private String shopId;
	
	private String businessClassifyId;//店铺分类Id
	
	private String goodsLabel;//商品标签
	
	private String subTitle;//副标题
	
	private String sumStock;//总库存
	
	private double freight;//运费
}
