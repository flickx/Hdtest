package com.flickx.web.index.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author flick on 2017/4/1.
 * @version 1.0
 */
@Controller
public class LoginAction {

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        if(session.getAttribute("loginUser") == null)
            modelAndView.setViewName("login");
        else
            modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        if(userName != null){
            request.getSession().setAttribute("loginUser",userName);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
