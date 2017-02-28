package com.ftoul.common;

import java.util.List;

/**
 * 展示对象到前端要求对象
 * @author flick
 *
 */
public class ObjectToResult {

	/**
	 * 单个对象返回前端对象
	 * @param object
	 * @return
	 */
	public static Result getResult(Object object){
		Result result  = new Result();
		result.setResult(1);
		result.setMessage("success");
		result.setTotalNum(1);
		result.setIsPage(false);
		result.setObj(object);
		return result;
	}
	
	/**
	 * 单个对象返回前端对象
	 * @param msg
	 * @return
	 */
	public static Result getResult(String msg){
		Result result  = new Result();
		result.setResult(0);
		result.setMessage(msg);
		result.setTotalNum(1);
		result.setIsPage(false);
		return result;
	}
	
	/**
	 * 列表返回前端对象
	 * @param object
	 * @return
	 */
	public static Result getResult(List<?> list){
		Result result  = new Result();
		result.setResult(1);
		result.setMessage("success");
		result.setTotalNum(list.size());
		result.setIsPage(false);
		result.setObj(list);
		return result;
	}
	/**
	 * 分页对象返回前端对象
	 * @param object
	 * @return
	 */
	public static Result getResult(Page page){
		Result result  = new Result();
		result.setResult(1);
		result.setMessage("success");
		result.setTotalNum(page.getCount());
		result.setIsPage(true);
		result.setMaxPage(page.getMaxPage());
		result.setPageNum(page.getPageNum());
		result.setPageSize(page.getPageSize());
		result.setObj(page.getObjList());
		return result;
	}
	/**
	 * 分页对象返回前端对象(vo对象)
	 * @param object
	 * @return
	 */
	public static Result getVoResult(Page page){
		Result result  = new Result();
		result.setResult(1);
		result.setMessage("success");
		result.setTotalNum(page.getCount());
		result.setIsPage(true);
		result.setMaxPage(page.getMaxPage());
		result.setPageNum(page.getPageNum());
		result.setPageSize(page.getPageSize());
		result.setObj(page.getVoList());
		return result;
	}
	/**
	 * 分页对象返回前端对象
	 * @param object
	 * @return
	 */
	public static Result getResult(Exception ex){
		Result result  = new Result();
		result.setResult(0);
		result.setMessage(ex.getMessage());
		return result;
	}
}
