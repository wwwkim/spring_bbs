package com.spring.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bbs.dao.BbsDAO;
import com.spring.bbs.dto.BbsDTO;

@Service
public class BbsServiceImple implements BbsService {
@Autowired
BbsDAO bbsDao;
	@Override
	public ModelAndView wirte(BbsDTO bbs) {
		ModelAndView mv=new ModelAndView();
	
		int result=bbsDao.write(bbs);
		if (result>0) {
			mv.setViewName("redirect:/bbs");
		}else {
			mv.addObject("msg","<script>alert('error') </script>");
			mv.setViewName("redirect:/write");
		}
		return mv;
		
	}
	

}