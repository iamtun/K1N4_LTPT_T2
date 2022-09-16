package ex1.utils;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import ex1.entity.Employee;

public class EncodeJson {
	public static JsonObject toJSon(Employee employee) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", employee.getId());
		builder.add("name", employee.getName());
		builder.add("salary", employee.getSalary());
		JsonObject obj = builder.build();
		return obj;
	}
	
	public static String toJson(List<Employee> employees) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		employees.forEach(em -> builder.add(toJSon(em)));
		return builder.build().toString();
	}
}
