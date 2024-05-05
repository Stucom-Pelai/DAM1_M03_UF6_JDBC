
package model;

import java.util.ArrayList;

/**
 *
 * @author Maria del Mar
 */
public class Student {
	private int code;
	private String name;
	private String surname;
	private int age;
	private String gender;
	private ArrayList<Project> projects;

	public Student(int code, String name, String surname, int age, String gender) {
		this.code = code;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
		this.projects = new ArrayList<>();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Student{" + "code=" + code + ", name=" + name + ", surname=" + surname + ", age=" + age + ", gender="
				+ gender + '}';
	}

}
