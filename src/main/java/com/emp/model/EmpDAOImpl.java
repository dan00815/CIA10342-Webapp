package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAOImpl implements EmpDAO {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/hihiDatabase?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PWD = "Dan870815dan";
	public static final String INSERT_STMT = "insert into Employee (empName,empAcct,empPwd,empJobTitle,hireDate,empStat) values (?, ?, ?, ?, ?, ?)";
	public static final String FINEONE_BY_EMPID = "SELECT * FROM Employee where empId = ?";
	public static final String GET_ALL = "select * from employee";
	public static final String UPDATEONE_BY_EMPID = "update Employee set empName=?, empAcct=?,empPwd=?, empJobTitle=?, hireDate=?, empStat=? WHERE empId=?  ";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> allEmpList = new ArrayList<EmployeeVO>();
		EmployeeVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmployeeVO();
				empVO.setEmpId(rs.getInt("empId"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpAcct(rs.getString("empAcct"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpJobTitle(rs.getString("empJobTitle"));
				empVO.setHireDate(rs.getDate("hireDate"));
				empVO.setEmpStat(rs.getInt("empStat"));
				allEmpList.add(empVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}

		return allEmpList;
	}

	public void insert(EmployeeVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpAcct());
			pstmt.setString(3, empVO.getEmpPwd());
			pstmt.setString(4, empVO.getEmpJobTitle());
			pstmt.setDate(5, empVO.getHireDate());
			pstmt.setInt(6, empVO.getEmpStat());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	};

	public EmployeeVO findByPrimaryKey(Integer empId) {
		EmployeeVO EmployeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(FINEONE_BY_EMPID);
			pstmt.setInt(1, empId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmployeeVO = new EmployeeVO();
				EmployeeVO.setEmpId(rs.getInt(1));
				EmployeeVO.setEmpName(rs.getString(2));
				EmployeeVO.setEmpAcct(rs.getString(3));
				EmployeeVO.setEmpPwd(rs.getString(4));
				EmployeeVO.setEmpJobTitle(rs.getString(5));
				EmployeeVO.setHireDate(rs.getDate(6));
				EmployeeVO.setEmpStat(rs.getInt(7));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}

		return EmployeeVO;
	}

	public void update(EmployeeVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt = con.prepareStatement(UPDATEONE_BY_EMPID);


			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpAcct());
			pstmt.setString(3, empVO.getEmpPwd());
			pstmt.setString(4, empVO.getEmpJobTitle());
			pstmt.setDate(5, empVO.getHireDate());
			pstmt.setInt(6, empVO.getEmpStat());
			pstmt.setInt(7, empVO.getEmpId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	public void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
