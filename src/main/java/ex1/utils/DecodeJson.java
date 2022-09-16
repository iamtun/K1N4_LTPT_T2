package ex1.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import ex1.entity.Employee;

public class DecodeJson {
	public static Employee toObjectFromFile(String file) {
		try {
			FileInputStream in = new FileInputStream(file);
			JsonReader reader = Json.createReader(in);

			JsonObject object = reader.readObject();
			JsonNumber id = object.getJsonNumber("id");
			String name = object.getString("name");
			JsonNumber salary = object.getJsonNumber("salary");

			return new Employee(id.longValue(), name, salary.doubleValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Employee toObject(JsonObject json) {
		JsonNumber id = json.getJsonNumber("id");
		String name = json.getString("name");
		JsonNumber salary = json.getJsonNumber("salary");

		return new Employee(id.longValue(), name, salary.doubleValue());
	}

	public static List<Employee> toList(String filePath) {
		List<Employee> employees = null;
		try {
			JsonReader reader = Json.createReader(new FileReader(new File(filePath)));

			JsonArray array = reader.readArray();
			if (array != null) {
				employees = new ArrayList<>();
				for (JsonValue value : array) {
					JsonObject json = value.asJsonObject();
					Employee em = toObject(json);
					employees.add(em);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return employees;
	}

	public static List<Employee> toListByStreamAPI(String fileName) {
		List<Employee> employees = null;
		try {
			JsonParser parser = Json.createParser(new FileReader(new File(fileName)));
			String keyName = "";
			Employee employee = null;

			while (parser.hasNext()) {
				Event event = parser.next();
				switch (event) {
				case START_ARRAY:
					employees = new ArrayList<>();
					break;
				case START_OBJECT:
					employee = new Employee();
					break;
				case KEY_NAME:
					keyName = parser.getString();
					break;
				case VALUE_NUMBER:
					JsonNumber value = (JsonNumber) parser.getValue();
					if (keyName.equals("id"))
						employee.setId(value.longValue());
					else if (keyName.equals("salary"))
						employee.setSalary(value.doubleValue());
					break;
				case VALUE_STRING:
					if (keyName.equals("name"))
						employee.setName(parser.getValue().toString());
					break;
				case END_OBJECT:
					employees.add(employee);
					break;
				default:
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
	}
	
	
}
