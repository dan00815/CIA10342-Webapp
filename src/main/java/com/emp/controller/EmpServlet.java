package com.emp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.emp.model.EmployeeService;
import com.emp.model.EmployeeVO;

public class EmpServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String addempForwardUrl = "/backend/emp/addEmp.jsp";
		String selectPageForwardUrl = "/backend/emp/select_page.jsp";
		String updateForwardUrl = "/backend/emp/update_emp_.jsp";
		String listAllUrl = "/backend/emp/listAllEmp.jsp";

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String empName = req.getParameter("empName");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (empName == null || empName.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!empName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String empAcct = req.getParameter("empAcct").trim();
			if (empAcct == null || empAcct.trim().length() == 0) {
				errorMsgs.add("員工帳號請勿空白");
			}

			String empPwd = req.getParameter("empPwd").trim();
			if (empPwd == null || empPwd.trim().length() == 0) {
				errorMsgs.add("員工密碼請勿空白");
			} else if (empPwd.trim().length() <= 3) {
				errorMsgs.add("密碼至少三個字");
			} else if (empPwd.trim().length() > 30) {
				errorMsgs.add("密碼不得超過30個字");
			}

			String empJobTitle111 = req.getParameter("empJobTitle").trim();
			if (empJobTitle111 == null || empJobTitle111.trim().length() == 0) {
				errorMsgs.add("職稱請勿空白");
			}

			java.sql.Date hiredate = null;
			try {
				hiredate = java.sql.Date.valueOf(req.getParameter("empHiredate").trim());
			} catch (IllegalArgumentException e) {
				hiredate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			Integer empStat = null;
			try {
				empStat = Integer.valueOf(req.getParameter("empStat").trim());
			} catch (NumberFormatException e) {
				empStat = 0;
				errorMsgs.add("狀態請填數字");
			}

			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setEmpName(empName);
			employeeVO.setEmpAcct(empAcct);
			employeeVO.setEmpPwd(empPwd);
			employeeVO.setEmpJobTitle(empJobTitle111);
			employeeVO.setHireDate(hiredate);
			employeeVO.setEmpStat(empStat);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(addempForwardUrl);
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO EmployeeVO = empSvc.addEmp(empName, empAcct, empPwd, empJobTitle111, hiredate, empStat);
			// 對於回傳的empVO可額外做加工(如秀出頁面告知成功新增了哪一筆資料)
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			System.out.println("重導回去");
			RequestDispatcher successView = req.getRequestDispatcher(listAllUrl); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String strId = req.getParameter("empId");

			if (strId == null || strId.trim().length() == 0) {
				errorMsgs.add("請輸入員工編號");
			}
			// Send the use back to the form, if there were errors
			errorHandle(errorMsgs, selectPageForwardUrl, req, res);

			Integer empId = null;
			try {
				empId = Integer.valueOf(strId);
			} catch (Exception e) {
				errorMsgs.add("格式輸入錯誤");
			}

			errorHandle(errorMsgs, selectPageForwardUrl, req, res);

			/*************************** 2.開始查詢資料 *****************************************/
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO empVO = empSvc.getOneEmp(empId);
			if (empVO == null) {
				errorMsgs.add("查無資料");
			}
			errorHandle(errorMsgs, selectPageForwardUrl, req, res);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
			String url = "/backend/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}

		if ("getOne_For_Update".equals(action)) {

			Integer empId = Integer.valueOf(req.getParameter("empId"));

//			找到要更新的那筆資料
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO empVO = empSvc.getOneEmp(empId);

			req.setAttribute("updateEmpVO", empVO);
			RequestDispatcher successView = req.getRequestDispatcher(updateForwardUrl);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer empId = null;
			try {
				empId = Integer.valueOf(req.getParameter("empId").trim());
			} catch (NumberFormatException e) {
				empId = 0;
				errorMsgs.add("狀態請填數字");
			}
			
//			empId = Integer.valueOf(req.getParameter("empId"));

			String empName = req.getParameter("empName");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (empName == null || empName.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!empName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String empAcct = req.getParameter("empAcct").trim();
			if (empAcct == null || empAcct.trim().length() == 0) {
				errorMsgs.add("員工帳號請勿空白");
			}

			String empPwd = req.getParameter("empPwd").trim();
			if (empPwd == null || empPwd.trim().length() == 0) {
				errorMsgs.add("員工密碼請勿空白");
			} else if (empPwd.trim().length() <= 3) {
				errorMsgs.add("密碼至少三個字");
			} else if (empPwd.trim().length() > 30) {
				errorMsgs.add("密碼不得超過30個字");
			}

			String empJobTitle111 = req.getParameter("empJobTitle").trim();
			if (empJobTitle111 == null || empJobTitle111.trim().length() == 0) {
				errorMsgs.add("職稱請勿空白");
			}

			java.sql.Date hiredate = null;
			try {
				hiredate = java.sql.Date.valueOf(req.getParameter("empHiredate").trim());
			} catch (IllegalArgumentException e) {
				hiredate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			Integer empStat = null;
			try {
				empStat = Integer.valueOf(req.getParameter("empStat").trim());
			} catch (NumberFormatException e) {
				empStat = 0;
				errorMsgs.add("狀態請填數字");
			}

			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setEmpName(empName);
			employeeVO.setEmpAcct(empAcct);
			employeeVO.setEmpPwd(empPwd);
			employeeVO.setEmpJobTitle(empJobTitle111);
			employeeVO.setHireDate(hiredate);
			employeeVO.setEmpStat(empStat);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("updateEmpVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(updateForwardUrl);
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.更新資料 ***************************************/
			EmployeeService empSvc = new EmployeeService();
//			EmployeeVO empVO = (EmployeeVO) req.getAttribute("updateEmpVO");

			EmployeeVO EmployeeVO = empSvc.updateEmp(empId, empName, empAcct, empPwd, empJobTitle111, hiredate,
					empStat);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			System.out.println("重導回去");
			RequestDispatcher successView = req.getRequestDispatcher(listAllUrl); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

	}

	static void errorHandle(List list, String url, HttpServletRequest req, HttpServletResponse res) {
		if (!list.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			try {
				failureView.forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
}
