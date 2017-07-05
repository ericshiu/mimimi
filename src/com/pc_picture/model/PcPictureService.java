package com.pc_picture.model;

import java.util.List;

public class PcPictureService {
	
	private PcPictureDAO_interface dao;
	
	public PcPictureService(){
		dao = new PcPictureDAO();
	}

	public PcPictureVO addPCP(String pc_no, byte[] pcp_picture, String pcp_summary) {

		PcPictureVO pcPictureVO = new PcPictureVO();
		
		pcPictureVO.setPc_no(pc_no);
		pcPictureVO.setPcp_picture(pcp_picture);
		pcPictureVO.setPcp_summary(pcp_summary);

		dao.insert(pcPictureVO);
		
		return pcPictureVO;
	}

	public PcPictureVO updatePCP(String pcp_no, String pc_no, byte[] pcp_picture, String pcp_summary) {
		
		PcPictureVO pcPictureVO = new PcPictureVO();
		
		pcPictureVO.setPcp_no(pcp_no);
		pcPictureVO.setPc_no(pc_no);
		pcPictureVO.setPcp_picture(pcp_picture);
		pcPictureVO.setPcp_summary(pcp_summary);
		
		dao.update(pcPictureVO);
		
		return pcPictureVO;
	}
	
	public void deletePCP(String pcp_no) {
		dao.delete(pcp_no);
	}

	public PcPictureVO getOnePCP(String pcp_no) {
		return dao.findByPrimaryKey(pcp_no);
	}

	public List<PcPictureVO> getAll() {
		return dao.getAll();
	}	

}
