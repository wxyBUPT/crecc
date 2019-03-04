package com.crecc.example.jdbbc;

import com.crecc.example.DataSourceConfig;
import com.crecc.example.jdbbc.domain.Employee;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by xiyuanbupt on 2019/2/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JTemplateConf.class})
public class JDBCTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testQuery(){
        assertNotNull(jdbcTemplate);
        String sql = "SELECT id, last_name lastName, email FROM employee WHERE id > ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(
                Employee.class
        );
        List<Employee> employees = jdbcTemplate.query(sql, rowMapper,0);
        System.out.println(employees);
    }
}
