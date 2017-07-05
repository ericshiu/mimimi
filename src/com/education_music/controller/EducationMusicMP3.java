package com.education_music.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.education_music.model.EducationMusicService;
import com.education_music.model.EducationMusicVO;

public class EducationMusicMP3  extends HttpServlet{
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ST");
		res.setContentType("audio/mpeg");
		ServletOutputStream out = res.getOutputStream();
		

		try {
			String em_no=req.getParameter("em_no");
			EducationMusicService EMSvc=new EducationMusicService();
			System.out.println("111");
			EducationMusicVO educationMusicVO=EMSvc.getOneEm_no(new String(em_no));
			byte[]buf=educationMusicVO.getEm_content();
			out.write(buf);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/musuc/mu1.mp3");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}
	
	
	

}
