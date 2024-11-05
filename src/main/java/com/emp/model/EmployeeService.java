package com.emp.model;

import java.util.List;

public class EmployeeService {

//	多型，本來應該是private EmpService dao;
	private EmpDAO dao;

//	無參數建構子new 一個EmpDAO物件
	public EmployeeService() {
		dao = new EmpDAOImpl();
	}

	public EmployeeVO addEmp(String empName, String empAcct, String empPwd, String empJobTitle, java.sql.Date hiredate,
			Integer empStat) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmpName(empName);
		employeeVO.setEmpAcct(empAcct);
		employeeVO.setEmpPwd(empPwd);
		employeeVO.setEmpJobTitle(empJobTitle);
		employeeVO.setHireDate(hiredate);
		employeeVO.setEmpStat(empStat);
		dao.insert(employeeVO);

		return employeeVO;
	}

	public EmployeeVO updateEmp(Integer empId, String empName, String empAcct, String empPwd, String empJobTitle,
			java.sql.Date hiredate, Integer empStat) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmpId(empId);
		employeeVO.setEmpName(empName);
		employeeVO.setEmpAcct(empAcct);
		employeeVO.setEmpPwd(empPwd);
		employeeVO.setEmpJobTitle(empJobTitle);
		employeeVO.setHireDate(hiredate);
		employeeVO.setEmpStat(empStat);

		dao.update(employeeVO);

		return employeeVO;
	}
//
//	public void deleteEmp(Integer empno) {
//		dao.delete(empno);
//	}
//

	public EmployeeVO getOneEmp(Integer empId) {
		return dao.findByPrimaryKey(empId);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
}
