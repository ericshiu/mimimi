package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO member);
    public void update(MemberVO member);
    public void delete(String mem_no);
    public MemberVO findByPrimaryKey(String mem_no);
    public MemberVO findByID(String mem_ID);
    public List<MemberVO> getAll();
    public void updatePoint(MemberVO memberVO, java.sql.Connection con);
    public void updatePsw(String mem_password,String mem_no);
}
