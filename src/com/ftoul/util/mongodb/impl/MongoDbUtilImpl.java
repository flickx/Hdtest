package com.ftoul.util.mongodb.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.Filters;
import com.ftoul.common.Page;
import com.ftoul.common.Rule;
import com.ftoul.common.StrUtil;
import com.ftoul.util.mongodb.MongoDbUtil;
@Service("MongoDbUtilImpl")
public class MongoDbUtilImpl implements MongoDbUtil {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void insert(Object object) {
		String collectionName = StrUtil.firstToLower(object.getClass().getSimpleName());
		mongoTemplate.insert(object, collectionName);  
	}

	@Override
	public Object find(Object id,Class<?> c) {
		String collectionName = StrUtil.firstToLower(c.getSimpleName());
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), c,collectionName);
	}

	@Override
	public List<?> getList(Map<String, Object> params,Class<?> c) {
		String collectionName = StrUtil.firstToLower(c.getSimpleName());
		return mongoTemplate.findAll(c, collectionName);
	}
	
	@Override
	public Page getListPage(Class<?> c,Integer pageNum, Integer pageSize,String sidx,String sord,Filters... filters) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if(filters !=null){
//			Criteria[] criterias = new Criteria[0];
			List<Criteria> criteriaListAnd = new ArrayList<Criteria>();
			List<Criteria> criteriaListOr = new ArrayList<Criteria>();
			for (Filters filter : filters) {
				if(filter!=null){
					String op = filter.getGroupOp();
					List<Rule> rules = filter.getRules();
					if(rules!=null && rules.size()>0){
//						int criteriasLength = criterias.length;
//						Criteria[] criterias2 = new Criteria[criteriasLength + rules.size()];
//						System.arraycopy(criterias, 0, criterias2, 0, criterias.length);
						for (int i=0;i<rules.size();i++) {
							Rule rule = rules.get(i);
//							criterias2[criteriasLength+i] = opToMongo(rule);
							if("AND".equals(op.toUpperCase())){
//								criteria.andOperator(criterias2);
								criteriaListAnd.add(opToMongo(rule));
							}else{
								criteriaListOr.add(opToMongo(rule));
//								criteria.orOperator(criterias2);
							}
						}
//						if("AND".equals(op.toUpperCase())){
//							criteria.andOperator(criterias2);
//						}else{
//							criteria.orOperator(criterias2);
//						}
//						criterias = criterias2;
					}
				}
			}
			if(criteriaListAnd.size()>0){
				Criteria[] criterAnds = new Criteria[criteriaListAnd.size()];
				criteria.andOperator(criteriaListAnd.toArray(criterAnds));
			}
			if(criteriaListOr.size()>0)
				criteria.orOperator((Criteria[]) criteriaListOr.toArray());
			query.addCriteria(criteria);
		}
//		criteria.
		query.skip(pageSize*(pageNum-1)).limit(pageSize);
		String collectionName = StrUtil.firstToLower(c.getSimpleName());
		Long count = mongoTemplate.count(query,c, collectionName);
		if(sidx!=null){
			if(Common.notNull(sord)&&"desc".equals(sord.toLowerCase()))
				query.with(new Sort(Direction.DESC,sidx));
			else
				query.with(new Sort(Direction.ASC,sidx));
		}
		List<?> list = mongoTemplate.find(query,c, collectionName);
		Integer maxPage = (int)(Math.ceil(((double)count)/pageSize));
		Page page = new Page();
		page.setCount(count);
		page.setMaxPage(maxPage);
		page.setObjList(list);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		return page;
	}

	@Override
	public void update(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCollection(String collectionName) {
		mongoTemplate.createCollection(collectionName);
		
	}

	@Override
	public void del(Object id,Class<?> c) {
		String collectionName = StrUtil.firstToLower(c.getSimpleName());
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)),c,collectionName);
	}
	
	
	/**
	 * 通过前端操作转换为sql操作符
	 * @param op
	 * @return
	 */
	public Criteria opToMongo(Rule rule){
		String op = rule.getOp();
		String filed = rule.getField();
		String data = rule.getData();
		Criteria criteria = new Criteria();
		switch (op) {
		case "eq":
//			sqlop = "=";
			criteria = Criteria.where(filed).is(escapeExprSpecialWord(data));
			break;
		case "ne":
			criteria = Criteria.where(filed).ne(data);
//			sqlop = "<>";
			break;
		case "lt":
			criteria = Criteria.where(filed).lt(data);
//			sqlop = "<";
			break;
		case "le":
			criteria = Criteria.where(filed).lte(data);
//			sqlop = "<=";
			break;
		case "gt":
			criteria = Criteria.where(filed).gt(data);
//			sqlop = ">";
			break;
		case "ge":
			criteria = Criteria.where(filed).gte(data);
//			sqlop = ">=";
			break;
		case "bw":
			criteria = Criteria.where(filed).gte(data);
//			sqlop = ">=";
			break;
		case "bn":
			criteria = Criteria.where(filed).lt(data);
//			sqlop = "<";
			break;
		case "in":
//			sqlop = "in";
			criteria = Criteria.where(filed).in(data);
			break;
		case "ni":
			criteria = Criteria.where(filed).nin(data);
//			sqlop = "not in";
			break;	
		case "ew":
			criteria = Criteria.where(filed).lte(data);
//			sqlop = "<=";
			break;
		case "en":
			criteria = Criteria.where(filed).gt(data);
//			sqlop = ">";
			break;
		case "cn":
			criteria = Criteria.where(filed).regex(data);
//			sqlop = "like";
			break;
		case "nc":
			criteria = Criteria.where(filed).regex(data).not();
//			sqlop = "not like";
			break;
		default:
			criteria = Criteria.where(filed).is(escapeExprSpecialWord(data));
//			sqlop = "=";
			break;
		}
		return criteria;
	}
	
	/** 
	 * 转义正则特殊字符 （$()*+.[]?\^{},|） 
	 *  
	 * @param keyword 
	 * @return 
	 */  
	public String escapeExprSpecialWord(String keyword) {  
		String[] fbsArr = { "\\", "$","'","%", "*", "+", "[", "]", "?", "^", "{", "}", "|" };  
		for (String key : fbsArr) {
			if (keyword.contains(key)) {
				keyword = keyword.replace(key, "\\\\" + key);
			}
		}  
	    return keyword;  
	}  

}
