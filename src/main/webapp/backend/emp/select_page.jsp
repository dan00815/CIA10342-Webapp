<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Employee : Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: coral;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 10px ridge lightGray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	display: block;
	margin-bottom: 3px;
}

h4 {
	color: blue;
	display: inline;
}

ul li {
	list-style-type: none;
}
</style>





</head>

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>hihiDataBase Employee: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for hihiDataBase Employee: Home</p>



	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<h3>��Ƭd��:</h3>

	<ul>
		<li><a href='listAllEmp.jsp'>List</a> all Emps. <br> <br>
		</li>


		<li>
			<FORM METHOD="post" ACTION="emp.do">
				<b>��J���u�s�� (�p7001):</b> <input type="text" name="empId"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="empSvc" scope="page"
			class="com.emp.model.EmployeeService" />

		<li>
			<FORM METHOD="post" ACTION="emp.do">
				<b>��ܭ��u�s��:</b> <select size="1" name="empId">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.empId}">${empVO.empId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<!-- 		<li> -->
		<!-- 			<FORM METHOD="post" ACTION="emp.do"> -->
		<!-- 				<b>��ܭ��u�m�W:</b> <select size="1" name="empno"> -->
		<%-- 					<c:forEach var="empVO" items="${empSvc.all}"> --%>
		<%-- 						<option value="${empVO.empno}">${empVO.ename} --%>
		<%-- 					</c:forEach> --%>
		<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
		<!-- 				<input type="submit" value="�e�X"> -->
		<!-- 			</FORM> -->
		<!-- 		</li> -->
	</ul>


	<h3>���u�޲z</h3>

	<ul>
		<li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
	</ul>

</body>
</html>