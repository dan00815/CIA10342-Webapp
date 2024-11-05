package com.emp.model;

import java.io.Serializable;
import java.sql.Date;

//empImg	員工照片	MEDIUMBLOB
public class EmployeeVO implements Serializable {
	private Integer empId;
	private String empName;
	private String empAcct;
	private String empPwd;
	private String empJobTitle;
	private Date hireDate;
	private Integer empStat;
	// private Byte[] empImg;

	public EmployeeVO() {
	};

	public EmployeeVO(Integer empId, String empName, String empAcct, String empPwd, String empJobTitle, Date hireDate,
			Integer empStat) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAcct = empAcct;
		this.empPwd = empPwd;
		this.empJobTitle = empJobTitle;
		this.hireDate = hireDate;
		this.empStat = empStat;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAcct() {
		return empAcct;
	}

	public void setEmpAcct(String empAcct) {
		this.empAcct = empAcct;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpJobTitle() {
		return empJobTitle;
	}

	public void setEmpJobTitle(String empJobTitle) {
		this.empJobTitle = empJobTitle;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Integer getEmpStat() {
		return empStat;
	}

	public void setEmpStat(Integer empStat) {
		this.empStat = empStat;
	}

	@Override
	public String toString() {
		return "EmployeeVo [empId=" + empId + ", empName=" + empName + ", empAcct=" + empAcct + ", empPwd=" + empPwd
				+ ", empJobTitle=" + empJobTitle + ", hireDate=" + hireDate + ", empStat=" + empStat + "]";
	}

}
