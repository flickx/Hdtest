package com.ftoul.aop;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.Filters;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.Rule;
import com.ftoul.manage.user.vo.ManageTokenVo;
import com.ftoul.po.BaseResource;
import com.ftoul.po.LoginUserLog;
import com.ftoul.po.UserToken;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.token.TokenUtil;


/**
 * 作用：每一个访问的页面都会进入这个方法
 * 功能：查询出来，然后过滤
 * 切面
 * @author flick
 *
 */
@SuppressWarnings("unchecked")
@Component
@Aspect
public class MyInterceptor {
	
	@Pointcut("execution(* com.ftoul..service.*Serv.*(..))")
	private void anyMethod(){}//定义一个切入点
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired  
	private  HttpServletRequest req;
	@Autowired
	private HttpServletResponse res;
	@Autowired
	private TokenUtil tokenUtil;
	private static final Integer PageSize = 5 ;
	private static List<Element> nameSpaceList;
	private static List<Element> methodNameList;
	private Parameter parameters;
	
	/**
	 * 初始化类的时候加载openAjax.xml文件
	 * 获取nameSpaceList，methodNameList对象
	 * 用于做过滤条件
	 * 加入static代码块确保配置文件在spring加载时就读取且只读取一次
	 * 相应的，改动配置文件生效需要重启tomcat服务
	 */
	static  {
		try {
			String realPath = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath() 
					+ "/config/openAjax.xml";
//			realPath = realPath.substring(1);
//			System.out.println(realPath);
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(realPath));
			Element nameSpaceElm=document.getRootElement().element("nameSpace");
			MyInterceptor.nameSpaceList = nameSpaceElm.elements("list");
			Element methodNameElm=document.getRootElement().element("methodName");
			MyInterceptor.methodNameList = methodNameElm.elements("list");
			
//			System.out.println("realPath==============================:" + realPath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	/**
	 * 获取页面传递参数，封装到Parameter对象中
	 * @param param
	 * @throws UnsupportedEncodingException 
	 */
	@Before("anyMethod() && args(param)")
	public void questToParam(Parameter param) throws Exception{
//		req.setCharacterEncoding("utf-8");
		String page = req.getParameter("page");
		//删除的时候需要传id
		String id = req.getParameter("id");
		String parentId = req.getParameter("parentId");
		String rows = req.getParameter("rows");
		String sord = req.getParameter("sord");
		String sidx = req.getParameter("sidx");
		String filters = req.getParameter("filters");
		String obj = req.getParameter("obj");
		String oper= req.getParameter("oper");
//		for (Element element : MyInterceptor.nameSpaceList) {
//			System.out.println(element.getData());
//		}
		if(Common.notNull(page))
			param.setPageNum(Integer.parseInt(page));
		else if(param.getPageNum() == null)
			param.setPageNum(1);
		if(Common.notNull(id))
			param.setId(id);
		if(Common.notNull(parentId))
			param.setParentId(parentId);;
		if(Common.notNull(rows))
			param.setPageSize(Integer.parseInt(rows));
		else if(param.getPageSize() == null)
			param.setPageSize(PageSize);
		if(Common.notNull(sord))
			param.setSord(sord);
		else if(param.getSord() == null)
			param.setSord("desc");
		if(Common.notNull(sidx))
			param.setSidx(sidx);
		else if(param.getSidx() == null)
			param.setSidx("id");
		if(Common.notNull(obj)){
			JSONObject jsonObject = JSONObject.fromObject(obj);
			param.setObj(jsonObject);
		}
		if(Common.notNull(oper)){
			Enumeration<?> paramNames = req.getParameterNames();
			JSONObject jsonObj = new JSONObject();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String paramValue = req.getParameterValues(paramName)[0]; 
				if("_empty".equals(paramValue))
					paramValue = null;
//				System.out.println(paramName + ":" +paramValue);
				jsonObj.put(paramName, paramValue);
			}
			param.setObj(jsonObj);
		}
		
		param.setOrderBy(" order by " + param.getSidx() + " " + param.getSord());
		//封装查询对象
		if(Common.notNull(filters)){
			try {
				ObjectMapper mapper = new ObjectMapper();
				Filters filtersObj = mapper.readValue(filters, Filters.class);
				param.setFilters(filtersObj);
				
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}
		Filters filtersToParam = param.getFilters();
		if(filtersToParam!=null){
			List<Rule> rules = filtersToParam.getRules();
			String whereStr = "";
			if(rules != null && rules.size()>0){
				for (Rule rule : rules) {
					if(!"".equals(whereStr))
						whereStr += filtersToParam.getGroupOp();
					String op = opToSql(rule.getOp());
					String data = "";
					if("like".equals(op) || "not like".equals(op))
						data = " '%" + escapeExprSpecialWord(rule.getData()) + "%' ";
					else if(rule.getFun()!=null && rule.getFun())
						data = " " + escapeExprSpecialWord(rule.getData()) + " ";
					else
						data = " '" + escapeExprSpecialWord(rule.getData()) + "' ";
					whereStr += " " + rule.getField() + " " + op + data ;
				}
				whereStr = " and (" + whereStr + ")";
				param.setWhereStr(whereStr);
			}
		}else{
			if(param.getWhereStr() == null)
				param.setWhereStr("");
		}
		this.parameters = param;
	}
	/** 
	 * 转义正则特殊字符 （$()*+.[]?\^{},|） 
	 *  
	 * @param keyword 
	 * @return 
	 */  
	public String escapeExprSpecialWord(String keyword) {  
		String[] fbsArr = { "\\", "$","'","%", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };  
		for (String key : fbsArr) {
			if (keyword.contains(key)) {
				keyword = keyword.replace(key, "\\\\" + key);
			}
		}  
	    return keyword;  
	}  
//	@AfterReturning("anyMethod()")
//	public void doAfter(){
//		System.out.println("后置通知");
//	}
//	
//	@After("anyMethod()")
//	public void after(){
//		System.out.println("最终通知");
//	}
//	
//	@AfterThrowing("anyMethod()")
//	public void doAfterThrow(){
//		System.out.println("例外通知");
//	}

    /**
     * 登陆的时候不进入，登陆成功之后获取用户的所有权限，然后进行对比
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
		Object object = new Object();
		String methodName = ((MethodSignature)pjp.getSignature()).getMethod().getName();
		//访问目标方法的参数：
//        Object[] args = pjp.getArgs();
        //是否为超级管理员
		boolean isAdmin = false;
		//是否验证成功
		boolean isTrue = false;
		//管理权限资源表控制(暂为空)
		List<BaseResource> baseResourceList = new ArrayList<BaseResource>();
		 // 获取服务器下web项目的相对路径(例如：/FtShop/projManage/getProjContract.action)
        String relative = req.getRequestURI();
        // 获取服务器下web项目的名字(例如：/FtShop)
        String absolute = req.getContextPath();
        //得到所执行的方法(例如：/projManage/getProjContract.action)
        String func = relative.substring(absolute.length(), relative.length());
        //得到所执行的命名空间(例如：/projManage)
        String nameSpace = func.substring(0, func.lastIndexOf("/")); 
        //得到项目的物理地址(例如：D:\apache-tomcat-8.0.30\webapps\projManage\)
        @SuppressWarnings("deprecation")
		String path = req.getRealPath("");
        //访问目标方法的参数：
		Object[] args = pjp.getArgs();
//        if("getGoodsDetail".equals(methodName)){
//        	if(args != null &&args[0] instanceof Parameter){
//				Parameter parameter =  (Parameter) args[0];
//				String goodid = parameter.getId()+"";
//				Goods goods = (Goods) hibernateUtil.find(Goods.class, goodid);
//				UserBrowse userBrowse = new UserBrowse();
//				if(parameter.getUserToken()!=null){
//					userBrowse.setUser(parameter.getUserToken().getUser());
//				}
//				userBrowse.setIpAddress(req.getRemoteAddr());
//				userBrowse.setBrowseTime(new DateStr().toString());
//				
//				userBrowse.setGoodsId(goods);
//        	}
//        }
        
		if(!expMethod(methodName,nameSpace,path)){
			//System.out.println("==============methodName:"+methodName);
			
			//是否拥有权限的标记
	//		hibernateUtil.hql("");
			Result result = new Result();
			//String m = ((MethodSignature)pjp.getSignature()).getMethod().toString();
	       
	        //通过权限表判断用户权限,如果为超级管理员，默认拥有所有访问权限
//		    String roleSql ="SELECT t2.is_admin,t2.name from p_person_role t1 "
//		    		+ "JOIN base_role t2 on t1.state='1' and  "
//		    		+ "t2.state = '1' and t1.rid = t2.id and"
//		    		+ " t1.pid = '" + userId +"'";
//		    List<Object[]> rlist = hibernateUtil.sql(roleSql);
//		    
//		    for(Object[] objs : rlist){
//		    	BaseRole r = new BaseRole();
//		    	r.setIsAdmin(objs[0]+"");
//		    	if ("1".equals(r.getIsAdmin())) {
//		    		isTrue = true;
//		    		isAdmin = true;
////		    		System.out.println(isTrue);
//		    		break;
//				}
//		    }
	        
//		    String resourceSql = "SELECT t2.id, t2. NAME, t2.type, t2.content FROM"
//		    		+ " base_author_resource t1 "
//		    		+ "INNER JOIN base_resource t2 ON t1.state = '1' AND t2.state = '1' "
//		    		+ "AND t1.rid = t2.id AND t1.aid IN "
//		    		+ "( SELECT aid FROM base_role_author "
//		    		+ "WHERE state = '1' AND rid IN "
//		    		+ "( SELECT rid FROM p_person_role WHERE pid = '" + userId
//		    		+ "' AND state = '1' ))";
//		    List<Object[]> brlist = hibernateUtil.sql(resourceSql);
//		    for(Object[] objs : brlist){
//		    	BaseResource br =new BaseResource();
//		    	br.setId(objs[0]+"");
//		    	br.setName(objs[1]+"");
//		    	br.setType(objs[2]+"");
//		    	br.setContent(objs[3]+"");
//		    	
//				// 此处做用户名判断以及资源表权限是否存在
////				 System.out.println(br.getName());
//		    	if("data".equals(br.getType()))
//		    			baseResourceList.add(br);
//				if(str.equals(br.getName())){
//					isTrue = true;
////					break;
//				}
//	//			System.out.println("退出方法");
//		    }
			if(args != null &&args[0] instanceof Parameter){
				Parameter parameter =  (Parameter) args[0];
				if(nameSpace.indexOf("/web/") == 0){
					UserToken userToken = parameter.getUserToken();
					result = tokenUtil.checkMobilToken(userToken);
					if(result.getResult() != null && result.getResult() == 1){
						object = invock(pjp,isAdmin,baseResourceList);
					}else{
						object = result;
					}
				}else if(nameSpace.indexOf("/manage/") == 0){
					LoginUserLog loginUserLog = new LoginUserLog();
					loginUserLog.setOperationTime(new DateStr().toString());
					loginUserLog.setIpAddress(req.getRemoteAddr());
					loginUserLog.setMethodPackage(nameSpace);
					loginUserLog.setMethodName(methodName);
					ManageTokenVo manageToken = parameter.getManageToken();
					result = tokenUtil.checkManageToken(manageToken);
					if(result.getResult() != null && result.getResult() == 1){
						
						loginUserLog.setLoginUser(manageToken.getLoginUser());
						
						String operation = "";
						JSONObject parameterJson = parameters.toJson();
						if(methodName.indexOf("get") >= 0 && methodName.indexOf("List") >= 0)
							operation = "get";
						else if(methodName.indexOf("get") >= 0 && methodName.indexOf("List") < 0)
							operation = "find";
						else if(methodName.indexOf("save") >= 0){
							Object obj = parameterJson.get("obj");
							if(obj != null){
								JSONObject paramObj = JSONObject.fromObject(obj);
								if(paramObj.containsKey("id")){
									String id = paramObj.getString("id");
									if(paramObj.containsKey("id") || id == null || "".equals(id) || "null".equals(id))
										operation = "save";
									else
										operation = "upload";
								}else{
									operation = "save";
								}
							}
						}else if(methodName.indexOf("del") >= 0)
							operation = "del";
						else
							operation = "other";
						loginUserLog.setOperation(operation);
						parameterJson.remove("manageToken");
						loginUserLog.setPrams(parameterJson.toString());
						
						object = invock(pjp,isAdmin,baseResourceList);
						loginUserLog.setResStatic(res.getStatus()+"");
//						ServletOutputStream servletOutputStream  = res.getOutputStream();
						loginUserLog.setResText(((Result)object).getMessage());
					}else{
						loginUserLog.setResStatic(res.getStatus()+"");
						object = result;
						loginUserLog.setResText(((Result)object).getMessage());
					}
					hibernateUtil.save(loginUserLog);
				}else if(nameSpace.indexOf("/businessManage/") == 0){
					object = invock(pjp,isAdmin,baseResourceList);
				}else{
					result.setResult(0);
					result.setMessage("权限不足");
					object = result;
				}
			}else{
		    	result.setResult(0);
				result.setMessage("权限不足");
				object = result;
		    }
		}else{
			//判断是否为超级管理员用户
//			String loginName = req.getParameter("loginName");
//			String roleSql ="SELECT t2.is_admin,t2.name from p_person_role t1 "
//		    		+ "JOIN base_role t2 on t1.state='1' and  "
//		    		+ "t2.state = '1' and t1.rid = t2.id JOIN "
//		    		+ "p_person t3 ON t3.state = '1' AND t1.state = '1' "
//		    		+ "AND t3.id = t1.pid AND t3.login_name = '" + loginName + "'";
//		    List<Object[]> rlist = hibernateUtil.sql(roleSql);
//		    
//		    for(Object[] objs : rlist){
//		    	BaseRole r = new BaseRole();
//		    	r.setIsAdmin(objs[0]+"");
//		    	if ("1".equals(r.getIsAdmin())) {
//		    		isAdmin = true;
////		    		System.out.println(isTrue);
//		    		break;
//				}
//		    }
			object = invock(pjp,isAdmin,baseResourceList);
		}
		return object;
	}
	/**
	 * 执行方法
	 * @param pjp
	 * @throws Throwable 
	 */
	public Object invock(ProceedingJoinPoint pjp,boolean isAdmin,List<BaseResource> baseResourceList) throws Throwable{
		Object[] args = pjp.getArgs();
		if(args != null &&args[0] instanceof Parameter){
			Parameter parameter =  (Parameter) args[0];
			//设置用户访问资源
			parameter.setBaseResourceList(baseResourceList);
			parameter.setAdmin(isAdmin);
		}
		//设置用户所属部门
//		String hql = "select person.baseDept from PPerson person where person.state = '1' and person.id = '" + userId +"'";
//		List<Object> baseDeptList = hibernateUtil.hql(hql);
// 		if(baseDeptList!=null&&baseDeptList.size()>0){
//			BaseDept baseDept = (BaseDept) baseDeptList.get(0);
//			action.setDeptId(baseDept.getId());
//			action.setDeptComId(baseDept.getBaseCompany().getId());
//		}
		return pjp.proceed();
	}
	/**
	 * 过滤方法
	 * @return
	 * @throws DocumentException 
	 */
	public boolean expMethod(String methodName,String nameSpace,String path) throws DocumentException{
		boolean res = false;
//		String realPath = path + "\\WEB-INF\\classes\\config\\openAjax.xml";
//		SAXReader reader = new SAXReader();
//		Document document = reader.read(new File(realPath));
//		Element nameSpaceElm=document.getRootElement().element("nameSpace");
//		@SuppressWarnings("unchecked")
//		List<Element> nameSpaceList = nameSpaceElm.elements("list");
		for (Element element : nameSpaceList) {
//			System.out.println(element.getData());
			if(nameSpace.indexOf(element.getData()+"") == 0){
				res = true;
				break;
			}
			
		}
//		Element methodNameElm=document.getRootElement().element("methodName");
//		@SuppressWarnings("unchecked")
//		List<Element> methodNameList = methodNameElm.elements("list");
		for (Element element : methodNameList) {
			if(methodName.equals(element.getData())){
				res = true;
				break;
			}
			
		}
		
		String[] methods = {"loginPerson","getKDInfoList"}; 
		for(String method:methods){
			if(method.equals(methodName)){
				res = true;
				break;
			}
		}
		return res;
	}
	/**
	 * 通过前端操作转换为sql操作符
	 * @param op
	 * @return
	 */
	public String opToSql(String op){
		String sqlop = "";
		switch (op) {
		case "eq":
			sqlop = "=";
			break;
		case "ne":
			sqlop = "<>";
			break;
		case "lt":
			sqlop = "<";
			break;
		case "le":
			sqlop = "<=";
			break;
		case "gt":
			sqlop = ">";
			break;
		case "ge":
			sqlop = ">=";
			break;
		case "bw":
			sqlop = ">=";
			break;
		case "bn":
			sqlop = "<";
			break;
		case "in":
			sqlop = "in";
			break;
		case "ni":
			sqlop = "not in";
			break;	
		case "ew":
			sqlop = "<=";
			break;
		case "en":
			sqlop = ">";
			break;
		case "cn":
			sqlop = "like";
			break;
		case "nc":
			sqlop = "not like";
			break;
		default:
			sqlop = "=";
			break;
		}
		return sqlop;
	}
	
//	@After("anyMethod()")
//	public void crossDomain(){
//		Collection<String> domain = res.getHeaders("Access-Control-Allow-Origin");
////		System.out.println(domain.size());
//		if(domain.size() == 0)
//			res.addHeader("Access-Control-Allow-Origin", "*");
//	}
}
