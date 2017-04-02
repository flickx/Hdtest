package com.flickx.web.index.action;

import com.flickx.web.index.service.IndexServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author flick on 2017/4/1.
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/web/index")
public class IndexAction {
    public final  String jspPath = "/web/index/";
    @Autowired
    IndexServ indexServ;

    /**
     * 根据ID获取
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="{id}",method = RequestMethod.GET)
    public ModelAndView get(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        String result = indexServ.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(jspPath + "index");
        System.out.println(result);
        return modelAndView;
    }
    /**
     * 获取列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        List<?> result = indexServ.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(jspPath + "list");
        System.out.println(result);
        return modelAndView;
    }
 }
