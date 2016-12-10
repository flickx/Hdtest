package com.ftoul.businessManage.shopOrders.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ftoul.businessManage.shopOrders.service.ShopOrdersServ;
import com.ftoul.common.Common;
import com.ftoul.common.ExcelTools;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.orders.service.OrdersServ;

/**
 * 订单管理
 * @author HuDong
 *
 */
@Controller
@RequestMapping(value = "/businessManage/shopOrders")
public class ShopOrdersAction {

	@Autowired
	private ShopOrdersServ ordersServ;
	
	/**
	 * 删除订单
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteOrders")  
	public @ResponseBody Result deleteOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.deleteOrders(parameter);
	}
	
	/**
	 * 通过各状态的订单列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersList")  
	public @ResponseBody Result getOrdersList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersList(parameter);
	}
	
	/**
	 * 获取订单详情
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersByOrdersId")  
	public @ResponseBody Result getOrdersByOrdersId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersByOrdersId(parameter);
	}
	
	/**
	 * 获取订单列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrderListPage")  
	public @ResponseBody Result getOrderListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrderListPage(parameter);
	}
	
	/**
	 * 获取订单支付列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersPayListPage")  
	public @ResponseBody Result getOrdersPayListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersPayListPage(parameter);
	}
	
	/**
	 * 获取订单支付详细信息
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrderPayDetailByPayId")  
	public @ResponseBody Result getOrderPayDetailByPayId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrderPayDetailByPayId(parameter);
	}
	
	/**
	 * 后台查看订单详情
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrderDetailVoById")  
	public @ResponseBody Result getOrderDetailVoById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrderDetailVoById(parameter);
	}
	
	/**
	 * 设置清关状态
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "modifyClearCustom")  
	public @ResponseBody Result modifyClearCustom(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.modifyClearCustom(parameter);
	}
	
	/**
	 * 订单一键发货
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveOrderLogisticsCompany")  
	public @ResponseBody Result saveOrderLogisticsCompany(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.saveOrderLogisticsCompany(parameter);
	}
	
	/**
	 * 订单操作日志
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrderOpLog")  
	public @ResponseBody Result getOrderOpLog(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrderOpLog(parameter);
	}
	
	/**
	 * 修改订单状态
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveOrderStatic")  
	public @ResponseBody Result saveOrderStatic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.saveOrderStatic(parameter);
	}
	
	/**
	 * 保存优惠金额状态
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveBenefitPrice")  
	public @ResponseBody Result saveBenefitPrice(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.saveBenefitPrice(parameter);
	}
	
	/**
	 * 获取出库订单列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getdeliveryListPage")  
	public @ResponseBody Result getdeliveryListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getdeliveryListPage(parameter);
	}
	
	/**
	 * 获取订单设置信息
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersSet")  
	public @ResponseBody Result getOrdersSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersSet(parameter);
	}
	
	/**
	 * 保存或修改订单设置信息
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveOrdersSet")  
	public @ResponseBody Result saveOrdersSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.saveOrdersSet(parameter);
	}
	
	/**
	 * 通过主键获取订单
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersById")  
	public @ResponseBody Result getOrdersById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersById(parameter);
	}
	
	/**
	 * 导出订单信息
	 * @param param
	 * @param response
	 * @throws IOException 
	 */
	 @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
	public ModelAndView exportExcel(String param, HttpServletResponse response) throws Exception{
		System.out.println(param); 
		Parameter parameter = Common.jsonToParam(param);
		Result result = ordersServ.getOrdersDetailByOrdersId(parameter);
		List itemList = (List) result.getObj();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sd.format(new Date()) + ".xls";
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		response.setContentType("application/x-excel");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-Disposition",
				"attachment;filename=" + file.getName());// excel文件名

		try {
			String[] title = new String[26];
			title[0] = "订单编号";
			title[1] = "订单时间";
			title[2] = "订单状态";
			title[3] = "会员账号";
			title[4] = "收货人";
			title[5] = "联系电话";
			title[6] = "寄送地址";
			title[7] = "运费";
			title[8] = "商品名称";
			title[9] = "商品SKU";
			title[10] = "商品编码";
			title[11] = "规格型号";
			title[12] = "支付方式";
			title[13] = "数量";
			title[14] = "商品单价";
			title[15] = "应支付金额";
			title[16] = "实际支付金额";
			title[17] = "优惠金额";
			title[18] = "使用蜂币数量";
			title[19] = "蜂币抵扣金额";
			title[20] = "支付单单号";
			title[21] = "进货总价";
			title[22] = "进货渠道";
			title[23] = "结算方式";
			title[24] = "扣点率";
			title[25] = "下单备注";
			
			InputStream is = ExcelTools.getDownloadInputStream("订单信息", title,
					itemList);
			FileCopyUtils.copy(is, response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 导出收款单信息
	 * 
	 * @param param
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "exportRecepitExcel", method = RequestMethod.GET)
	public ModelAndView exportRecepitExcel(String param,
			HttpServletResponse response) throws Exception {
		System.out.println(param);
		Parameter parameter = Common.jsonToParam(param);
		Result result = ordersServ.getOrdersPayExportList(parameter);
		List itemList = (List) result.getObj();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sd.format(new Date()) + ".xls";
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		response.setContentType("application/x-excel");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-Disposition",
				"attachment;filename=" + file.getName());// excel文件名

		try {
			String[] title = new String[8];
			title[0] = "收款单号";
			title[1] = "支付完成时间";
			title[2] = "支付方式";
			title[3] = "支付金额";
			title[4] = "使用蜂币数量";
			title[5] = "兑换蜂币金额";
			title[6] = "支付状态";
			title[7] = "订单号";

			System.out.println("付款单信息："+itemList.size());
			InputStream is = ExcelTools.getDownloadInputStream("收款单信息", title,
					itemList);
			FileCopyUtils.copy(is, response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取订单列表（带分页）
	 * 
	 * @param param
	 *            页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "getSendGoodsListPage")  
	public @ResponseBody Result getSendGoodsListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getSendGoodsListPage(parameter);
	}
	
	/**
	 * 获取订单、售后单所有状态数量
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAllStaticSizeByShopId")  
	public @ResponseBody Result getAllStaticSizeByShopId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getAllStaticSizeByShopId(parameter);
	}
	
}
