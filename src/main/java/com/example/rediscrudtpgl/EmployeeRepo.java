package com.example.rediscrudtpgl;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class EmployeeRepo {
    private HashOperations hashOperations;
    private ListOperations listOperations;


    private RedisTemplate redisTemplate;

    public EmployeeRepo(RedisTemplate redisTemplate) {

        //this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee) {

       // hashOperations.put("EMPLOYEE", employee.getId(), employee);
		listOperations.rightPush("EMPLOYEE", employee);
    }

    public List<Employee> findAll() {

        //return hashOperations.values("EMPLOYEE");
        return listOperations.range("EMPLOYEE", 0, listOperations.size("EMPLOYEE"));

    }

    public Employee findById(Integer id) {
       // return (Employee) hashOperations.get("EMPLOYEE", id);
        List<Employee> employees = listOperations.range("EMPLOYEE", 0, listOperations.size("EMPLOYEE"));
        for (Employee employee : employees) {
            if(employee.getId() == id)
                return employee;
        }
        return null;
    }

    public void update(Employee employee) {
        saveEmployee(employee);
    }

    public void delete(Integer id) {
        //hashOperations.delete("EMPLOYEE", id);
        listOperations.rightPopAndLeftPush("EMPLOYEE", id);
    }
}
