package com.todobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/persons")
    public List<Person> getPersons() {

        List<Person> persons = new ArrayList<>();

        try {

            Connection connection = DataSourceUtils.getConnection(dataSource);

            String sql = "SELECT * FROM person";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Person person = new Person();

                person.setId(resultSet.getString("id"));
                person.setName(resultSet.getString("name"));
                person.setPassword(resultSet.getString("password"));

                persons.add(person);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }
}
