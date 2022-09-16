package ex1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ex1.entity.Employee;
import ex1.utils.DecodeJson;
import ex1.utils.EncodeJson;
import ex1.utils.FileHelper;

public class Main {
	public static void main(String[] args) {
//		Employee employee = new Employee(1001L, "Đinh Mạnh Ninh", 50000.0);
//		String json = EncodeJson.toJSon(employee);
//		FileHelper.export("json/employee.json", json);
//		
//		Employee em = DecodeJson.toObjectFromFile("json/employee.json");
//		System.out.println(em);
		
		List<Employee> employees = Arrays.asList(new Employee(1001L, "Đinh Mạnh Ninh", 50000.0), 
												new Employee(1002L, "Lê Thanh Tùng", 30000.0),
												new Employee(1003L, "Đinh Mạnh Tú", 50000.0),
												new Employee(1004L, "Phan Mạnh Vũ", 50000.0));
		String json = EncodeJson.toJson(employees);
		FileHelper.export("json/employees.json", json);
		
		List<Employee> list = DecodeJson.toListByStreamAPI("json/employees.json");
		list.forEach(item -> System.out.println(item));
	}
}
