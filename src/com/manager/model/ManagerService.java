package com.manager.model;

import java.util.List;

public class ManagerService {

	private ManagerDAO_interface dao;
	
	public ManagerService(){
		dao=new ManagerDAO();
	}
	public ManagerVO addMan(String man_id,String man_name,
			String man_password,String man_email){
		ManagerVO managerVO=new ManagerVO();

		managerVO.setMan_id(man_id);
		managerVO.setMan_name(man_name);
		managerVO.setMan_password(man_password);
		managerVO.setMan_email(man_email);
		dao.insert(managerVO);
		return managerVO;
	}
	
	public ManagerVO updateMan(String man_no,String man_id,String man_name,
			String man_password,String man_email){
		ManagerVO managerVO=new ManagerVO();
		managerVO.setMan_id(man_id);
		managerVO.setMan_name(man_name);
		managerVO.setMan_password(man_password);
		managerVO.setMan_email(man_email);
		managerVO.setMan_no(man_no);
		dao.update(managerVO);
		
		return managerVO;
	}
	
	public void deleteMan(String man_no){
		dao.delete(man_no);
	}
	public ManagerVO getOneMan(String man_no){
		return dao.findByPrimaryKey(man_no);
	}
	public ManagerVO getOneManId(String man_id){
		return dao.findById(man_id);
	}
	public List<ManagerVO>getAllMan(){
		return dao.getAll();
	}
	
}
