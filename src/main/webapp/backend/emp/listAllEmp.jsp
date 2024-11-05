<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
EmployeeService empSvc = new EmployeeService();
List<EmployeeVO> list = empSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

<style>

	div#table-1 {
		
	}
	
	div#table-1 ul{
		display:flex;	
		align-items: center;
		background-color: #CCCCFF;
		
	}
	
	div#table-1 ul li{
		list-style-type:none;
		margin:0 10px;
	}
</style>


<%@ include file="style.file"%>

</head>

<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>

	<div id="table-1">
		<ul>
			<li>
				<h3>所有員工資料 - listAllEmp.jsp</h3>
			</li>
			<li>
				<h4>
					<a href="select_page.jsp"><img
						src="<%=request.getContextPath()%>/backend/images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</li>
		</ul>
	</div>

	<table>
		<tr>

			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>職稱</th>
			<th>雇用日期</th>
			<th>狀態</th>
			<th colspan="2">修改刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${empVO.empId}</td>
				<td>${empVO.empName}</td>
				<td>${empVO.empAcct}</td>
				<td>${empVO.empPwd}</td>
				<td>${empVO.empJobTitle}</td>
				<td>${empVO.hireDate}</td>
				<td>${empVO.empStat}</td>
				<td colspan="2" id="update_td">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/backend/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden" name="empId" value="${empVO.empId}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
					
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/backend/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="empno" value="${empVO.empId}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			
			</tr>
		</c:forEach>
	</table>

	<div>
		<%@ include file="page2.file"%>
	</div>

</body>
</html>