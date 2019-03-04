package com.crecc.example;

import com.crecc.example.jdbbc.domain.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xiyuanbupt on 2019/2/26.
 */
public class TemplateEx {
    private JdbcTemplate jdbcTemplate;
    public Employee getEmployeeById(long id){
        return jdbcTemplate.queryForObject(
                "select id, firstname, lastname, salary" +
                        "from employee where id=?",
                new RowMapper<Employee>() {
                    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                        Employee employee = new Employee();
                        employee.setId(resultSet.getLong("id"));
                        employee.setFirstName(resultSet.getString("firstname"));
                        employee.setLastName(resultSet.getString("lastname"));
                        return employee;
                    }
                },id
        );
    }
}
