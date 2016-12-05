package com.ftoul.manage.business.action;

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

import com.ftoul.common.Common;
import com.ftoul.common.ExcelTools;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.business.service.BusinessServ;

/**
 * 
 * @author wenyujie
 * 商家
 *
 */
@Controller
@RequestMapping(value="/manage/business")
public class BusinessAction {
	@Autowired
	private BusinessServ businessServ;
	
	/**
	 * 保存/更新商家数据
	 * @param param 当前商家参数
	 * @throws Exception
	 */
	@RequestMapping(value="saveBusiness")
	public @ResponseBody Result saveBusiness(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.saveBusiness(parameter);
	}
	/**
	 * 获取商家资料数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessPage")
	public @ResponseBody Result getBusinessPage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessPage(parameter);
	}
	
	/**
	 * 获取商家店铺等级数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessStoreRank")
	public @ResponseBody Result getBusinessStoreRank(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessStoreRank(parameter);
	}
	
	/**
	 * 获取商家店铺分类数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessStoreClassify")
	public @ResponseBody Result getBusinessStoreClassify(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessStoreClassify(parameter);
	}
	/**
	 * 获取商家资料数据(分页)
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessPageList")
	public @ResponseBody Result getBusinessPageList(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessPageList(parameter);
	}
	
	/**
	 * 获取商家店铺等级数据(分页)
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessStoreRankList")
	public @ResponseBody Result getBusinessStoreRankList(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessStoreRankList(parameter);
	}
	
	/**
	 * 获取商家店铺分类数据(分页)
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessStoreClassifyList")
	public @ResponseBody Result getBusinessStoreClassifyList(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessStoreClassifyList(parameter);
	}
	/**
	 * 重置商家账号登录密码
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="updateBusinessStoreLogin")
	public @ResponseBody Result updateBusinessStoreLogin(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.updateBusinessStoreLogin(parameter);
	}
	
	/**
	 * 获取商家详情
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessLoginById")
	public @ResponseBody Result getBusinessLoginById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessLoginById(parameter);
	}
	
	/**
	 * 保存店铺分类信息
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="saveBusinessStoreClassify")
	public @ResponseBody Result saveBusinessStoreClassify(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.saveBusinessStoreClassify(parameter);
	}
	/**
	 * 根据ID获取店铺分类信息
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessStoreClassifyById")
	public @ResponseBody Result getBusinessStoreClassifyById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.getBusinessStoreClassifyById(parameter);
	}
	/**
	 * 根据ID获取店铺分类信息
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="updateBusinessStoreClassify")
	public @ResponseBody Result updateBusinessStoreClassify(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.updateBusinessStoreClassify(parameter);
	}
	/**
	 * 根据ID获取店铺分类信息
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="delBusinessStoreClassifybyId")
	public @ResponseBody Result delBusinessStoreClassifybyId(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessServ.delBusinessStoreClassifybyId(parameter);
	}
	/**
	 * 导出店铺分类信息
	 * 
	 * @param param
	 * @param response
	 * @throws IOException
	 */
		@RequestMapping(value = "exportBusinessStoreClassifyExcel", method = RequestMethod.GET)
		public ModelAndView exportBusinessStoreClassifyExcel(String param,
				HttpServletResponse response) throws Exception {
			Parameter parameter = Common.jsonToParam(param);
			Result result = businessServ.exportBusinessStoreClassifyExcel(parameter);
			List itemList = (List) result.getObj();
			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName =sd.format(new Date()) + ".xls";
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			response.setContentType("application/x-excel");
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + file.getName());// excel文件名

			try {
				String[] title = new String[9];
				title[0] = "ID";
				title[1] = "店铺种类";
				title[2] = "店铺类型";
				title[3] = "说明";
				title[4] = "修改人";
				title[5] = "修改日期";
				title[6] = "创建人";
				title[7] = "创建日期";
				title[8] = "状态";

				InputStream is = ExcelTools.getDownloadInputStream("店铺分类信息", title,
						itemList);
				FileCopyUtils.copy(is, response.getOutputStream());

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
		/**
		 * 导出商家入驻信息
		 * 
		 * @param param
		 * @param response
		 * @throws IOException
		 */
			@RequestMapping(value = "exportBusinessStoreExcel", method = RequestMethod.GET)
			public ModelAndView exportBusinessStoreExcel(String param,
					HttpServletResponse response) throws Exception {
				Parameter parameter = Common.jsonToParam(param);
				Result result = businessServ.exportBusinessStoreExcel(parameter);
				List itemList = (List) result.getObj();
				SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
				String fileName =sd.format(new Date()) + ".xls";
				File file = new File(fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
				response.setContentType("application/x-excel");
				response.setCharacterEncoding("UTF-8");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + file.getName());// excel文件名

				try {
					String[] title = new String[9];
					title[0] = "商家ID";
					title[1] = "公司名称";
					title[2] = "联系人姓名";
					title[3] = "联系人电话";
					title[4] = "店铺名称";
					title[5] = "店铺号";
					title[6] = "店铺账号";
					title[7] = "申请时间";
					title[8] = "状态";

					InputStream is = ExcelTools.getDownloadInputStream("商家店铺信息", title,
							itemList);
					FileCopyUtils.copy(is, response.getOutputStream());

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}
		/**
		 * 保存店铺等级信息
		 * @param param 参数
		 * @throws Exception
		 */
		@RequestMapping(value="saveBusinessStoreRank")
		public @ResponseBody Result saveBusinessStoreRank(String param)throws Exception{
			Parameter parameter = Common.jsonToParam(param);
			return businessServ.saveBusinessStoreRank(parameter);
		}
		/**
		 * 删除店铺等级信息
		 * @param param 参数
		 * @throws Exception
		 */
		@RequestMapping(value="delBusinessStoreRankbyId")
		public @ResponseBody Result delBusinessStoreRankbyId(String param)throws Exception{
			Parameter parameter = Common.jsonToParam(param);
			return businessServ.delBusinessStoreRankbyId(parameter);
		}
		/**
		 * 保存店铺经营类目
		 * @param param 参数
		 * @throws Exception
		 */
		@RequestMapping(value="saveBusinessStoreManageCategory")
		public @ResponseBody Result saveBusinessStoreManageCategory(String param)throws Exception{
			Parameter parameter = Common.jsonToParam(param);
			return businessServ.saveBusinessStoreManageCategory(parameter);
		}
		/**
		 * 获取店铺经营类目
		 * @param param 参数
		 * @throws Exception
		 */
		@RequestMapping(value="getBusinessStoreManageCategoryPage")
		public @ResponseBody Result getBusinessStoreManageCategoryPage(String param)throws Exception{
			Parameter parameter = Common.jsonToParam(param);
			return businessServ.getBusinessStoreManageCategoryPage(parameter);
		}
		/**
		 * 删除店铺经营类目
		 * @param param 参数
		 * @throws Exception
		 */
		@RequestMapping(value="delBusinessStoreManageCategoryPage")
		public @ResponseBody Result delBusinessStoreManageCategoryPage(String param)throws Exception{
			Parameter parameter = Common.jsonToParam(param);
			return businessServ.delBusinessStoreManageCategoryPage(parameter);
		}
}
