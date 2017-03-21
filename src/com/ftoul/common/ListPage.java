package com.ftoul.common;

import java.util.List;

import com.ftoul.common.Page;
/**
 * Page对象包装类
 * @author Administrator
 *
 */
public class ListPage {

	/**
	 * 通过list对象及分页要求，返回封装的Page对象
	 * @param list
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	public static Page ListPage(List<Object> list, Integer pageNum,
			Integer pagesize) {
		int totalcount = list.size();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (m == 0) {
			list = list.subList((pageNum - 1) * pagesize, pagesize * (pageNum));
		} else {
			if (pageNum == pagecount) {
				list = list.subList((pageNum - 1) * pagesize, totalcount);
			} else {
				list = list.subList((pageNum - 1) * pagesize, pagesize
						* (pageNum));
			}
		}
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageSize(pagesize);
		page.setCount((long)totalcount);
		page.setMaxPage(pagecount);
		page.setObjList(list);
		return page;
	}
}
