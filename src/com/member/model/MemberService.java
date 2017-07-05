package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMem(String mem_id, String mem_password, String mem_name, String mem_nike, String mem_sex,
			Date mem_birthday, String mem_phone, String mem_email, String mem_address, Integer mem_point,
			Integer mem_actual_point, byte[] mem_picture, String mem_authority, String mem_facebook,
			String mem_google) {

		MemberVO memberVO = new MemberVO();

		
		memberVO.setMem_id(mem_id);
		memberVO.setMem_password(mem_password);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nike(mem_nike);
		memberVO.setMem_sex(mem_sex);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_point(mem_point);
		memberVO.setMem_actual_point(mem_actual_point);
		memberVO.setMem_picture(mem_picture);
		memberVO.setMem_authority(mem_authority);
		memberVO.setMem_facebook(mem_facebook);
		memberVO.setMem_google(mem_google);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMem(String mem_no,String mem_id, String mem_password, String mem_name, String mem_nike, String mem_sex,
			Date mem_birthday, String mem_phone, String mem_email, String mem_address, Integer mem_point,
			Integer mem_actual_point, byte[] mem_picture, String mem_authority, String mem_facebook,
			String mem_google) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setMem_id(mem_id);
		memberVO.setMem_password(mem_password);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nike(mem_nike);
		memberVO.setMem_sex(mem_sex);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_point(mem_point);
		memberVO.setMem_actual_point(mem_actual_point);
		memberVO.setMem_picture(mem_picture);
		memberVO.setMem_authority(mem_authority);
		memberVO.setMem_facebook(mem_facebook);
		memberVO.setMem_google(mem_google);
		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMem(String mem_no) {
		dao.delete(mem_no);
	}

	public MemberVO getOneMem(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public MemberVO getOneID(String mem_id) {
		return dao.findByID(mem_id);
	}
	

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	
	public void updatePsw(String mem_password,String mem_no){
		dao.updatePsw(mem_password,mem_no);
	}
	
}
