package com.emp.model;

import java.util.*;

public interface EmpDAO {
	
	  public List<EmployeeVO> getAll();
	  
      public void insert(EmployeeVO empVO);
          
      public void update(EmployeeVO empVO);
    
//     public void delete(Integer empno);
    
      public EmployeeVO findByPrimaryKey(Integer empId);
        
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
