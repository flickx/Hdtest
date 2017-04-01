package com.ftoul.manage.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.UserScoreServ;
import com.ftoul.manage.user.vo.ScoreVo;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("UserScoreServImpl")
public class UserScoreServImpl implements UserScoreServ {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result getScorePage(Parameter param) throws Exception {
		String queryStr = param.getWhereStr();
		String sql = "select u.username,us.score,us.total_score,us.description,us.create_time "
					+ "from user u join user_sroce us on u.id = us.user_id and u.state = 1 " + queryStr+" order by us.create_time desc";
		Page page = hibernateUtil.sqlPage(null,sql, param.getPageNum(), param.getPageSize());
		List<ScoreVo> list = new ArrayList<>();
		for (int i = 0; i < page.getObjList().size(); i++) {
			ScoreVo scoreVo = new ScoreVo();
			Object[] obj = (Object[])page.getObjList().get(i);
			scoreVo.setUsername(obj[0].toString());
			scoreVo.setScore(obj[1].toString());
			scoreVo.setTotalScore(obj[2].toString());
			scoreVo.setDescription(obj[3].toString());
			scoreVo.setCreateTime(obj[4].toString());
			list.add(scoreVo);
		}
		page.setObjList(list);
		return ObjectToResult.getResult(page);
	}

}
