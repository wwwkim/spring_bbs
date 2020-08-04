package com.spring.bbs.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bbs.dto.BbsDTO;
import com.spring.bbs.service.BbsService;
@Controller
public class BbsController {
	@Autowired
	BbsService bbsService;
	

	@RequestMapping("/bbs")
	public String bbs(HttpSession session) {
		
		return "bbs";
	}
	@RequestMapping("/write")

	public String write(HttpSession session) {
		String user=null;
	
		if(session.getAttribute("userID")!=null) {
			user=(String)session.getAttribute("userID");
	return "write";
		}
		if(user==null) {
		
		}
		return"login";
	}
	@RequestMapping("/writeAction")
	public ModelAndView writeAction(HttpSession session,BbsDTO bbs) {
		ModelAndView mv = new ModelAndView();
		String user=null;
		if(session.getAttribute("userID")!=null) {
			user=(String)session.getAttribute("userID");
			bbs.setUserID(user);
		}
		if(user==null) {
			String msg="<script>alert('Please log in') </script>";
			mv.addObject("msg", msg);
			mv.setViewName("login");
		}else if(bbs.getBbsTitle().isEmpty()||bbs.getBbsContent().isEmpty()) {
			String msg="<script>alert('All the fields are required') </script>";
			mv.addObject("msg", msg);
			mv.setViewName("write");
		}else {
			mv=bbsService.wirte(bbs);
		}
		return mv;
	}
}