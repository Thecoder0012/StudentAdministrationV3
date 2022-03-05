package com.thecoder.repository;

import com.thecoder.db.DBManager;
import com.thecoder.models.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    Connection connection = DBManager.getConnection();
    Student student;


    public Student addStudent(Student student) {
        String insert = "INSERT INTO students(firstname,lastname,age,email) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    public Student updateStudent(Student student) {
        String sql = "UPDATE students SET firstname = ?,lastname = ?, age = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setLong(5, student.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    public Student getStudentById(Long id) {
        try {
            String sql = "SELECT * FROM students WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    public int deleteStudent(Long id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    }

    public List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> list = new ArrayList<>();
        while (resultSet.next()) {
            student = new Student(
                    resultSet.getLong("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getInt("age"),
                    resultSet.getString("email"));
            list.add(student);
        }
        resultSet.close();
        return list;
    }
}