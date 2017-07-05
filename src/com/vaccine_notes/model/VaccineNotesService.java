package com.vaccine_notes.model;

import java.sql.Date;
import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class VaccineNotesService {

	private VaccineNotesDAO_interface dao;

	public VaccineNotesService() {
		dao = new VaccineNotesDAO();
	}

	public VaccineNotesVO addVN(String bd_no, String vc_no, Date vn_date, PointRecordVO pointRecordVO, MemberVO memberVO) {

		VaccineNotesVO vaccineNotesVO = new VaccineNotesVO();
		// 新增
		vaccineNotesVO.setBd_no(bd_no);
		vaccineNotesVO.setVc_no(vc_no);
		vaccineNotesVO.setVn_date(vn_date);
		dao.insert(vaccineNotesVO,pointRecordVO,memberVO);
		return vaccineNotesVO;

	}

	public VaccineNotesVO updateVN(String vn_no, String bd_no, String vc_no, Date vn_date) {

		VaccineNotesVO vaccineNotesVO = new VaccineNotesVO();
		// 修改
		vaccineNotesVO.setVn_no(vn_no);
		vaccineNotesVO.setBd_no(bd_no);
		vaccineNotesVO.setVc_no(vc_no);
		vaccineNotesVO.setVn_date(vn_date);

		dao.update(vaccineNotesVO);
		return vaccineNotesVO;

	}

	public void deleteVN(String vn_no) {
		dao.delete(vn_no);
	}

	public VaccineNotesVO getOneVN(String vn_no) {
		return dao.findByPrimaryKey(vn_no);
	}

	public List<VaccineNotesVO> getOneBD(String bd_no) {
		return dao.getOneBD(bd_no);
	}

	public List<VaccineNotesVO> getAll() {
		return dao.getAll();
	}

}
