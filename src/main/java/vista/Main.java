package vista;

import exceptions.SchoolException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.SchoolDAOImpl;
import model.Student;

/**
 *
 * @author Maria del Mar
 */
public class Main {

	private static SchoolDAOImpl dao;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		dao = new SchoolDAOImpl();
		try {
			System.out.println("Estableciendo conexión con la base de datos...");
			dao.connect();
			System.out.println("Conectado.");
			
			// insert 1º student
			System.out.println("Insertando alumnos");
			Student s = new Student(11, "Bart", "Simpson", 10, "male");
			try {
				dao.insertStudent(s);
				System.out.println("Alumno registrado");
			} catch (SchoolException ex) {
				System.out.println(ex.getMessage());
			}
			
			// insert 2º student
			s = new Student(12, "Lisa", "Simpson", 8, "female");
			try {
				dao.insertStudent(s);
				System.out.println("Alumno registrado");
			} catch (SchoolException ex) {
				System.out.println(ex.getMessage());
			}
			
			// list all students
			System.out.println("Datos de los alumnos:");
			ArrayList<Student> students = dao.getAllStudents();
			for (Student student : students) {
				System.out.println(student);
			}
		} catch (SQLException ex) {
			System.out.println("ERROR con la BBDD: " + ex.getMessage());
		} finally {
			System.out.println("Desconectando....");
			try {
				dao.disconnect();
			} catch (SQLException ex) {
				System.out.println("Error al desconectar " + ex.getMessage());
			}
		}
		System.out.println("Hasta luego!");
	}

}
