package com.ftoul.app.vo;

/**
 * 商品收藏vo
 * @author yzw
 *
 */
public class CollectionAppVo {
	private String id;//收藏ID
	private String goodsId;//商品Id
	private String title;//商品标题
	private String goodsPic;//商品图片
	private Double price;//商品价格
	private String createTime;//创建时间
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGoodsPic() {
		return goodsPic;
	}
	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
