package com.ftoul.manage.goods.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.goods.service.GoodsUploadpicServ;
import com.ftoul.util.hibernate.HibernateUtil;
@Service("GoodsUploadpicServImpl")
public class GoodsUploadpicServImpl implements GoodsUploadpicServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 
	 * 删除商品图片
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result delGoodsUploadpic(Parameter param, HttpServletRequest request) throws Exception {
		Integer num = hibernateUtil.execHql("update GoodsUploadpic set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
//			String path = request.getParameter("picSrc");
//			path =  request.getSession().getServletContext().getRealPath(path);
//		    File file = new File(path);  
//		    boolean a =false;
//		    // 路径为文件且不为空则进行删除  
//		    if (file.isFile() && file.exists()) {  
//		     a   = file.delete();  
//		    }  
		return ObjectToResult.getResult(num);
	}
	/**
	 * 通过商品id和图片类型得到商品图片集合
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getGoodsUploadpicList(Parameter param) throws Exception {
		String hql = "from GoodsUploadpic where state='1' and  goods.id='" +param.getId() +"'"+ param.getWhereStr() + param.getOrderBy();
		List<Object> page = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(page);
	}
//	/**
//	 * 
//	 * 保存商品图片
//	 * @param   param Parameter对象
//	 * @return  返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result saveGoodsUploadpic(Parameter param) throws Exception {
//		GoodsUploadpic goodsUploadpic =  (GoodsUploadpic) JSONObject.toBean((JSONObject) param.getObj(),GoodsUploadpic.class);
//		goodsUploadpic.setCreateTime(new DateStr().toString());
//		goodsUploadpic.setCreatePerson(param.getUserToken().);
//		Object res = hibernateUtil.save(goodsUploadpic);
//		return ObjectToResult.getResult(res);
//	}

}
