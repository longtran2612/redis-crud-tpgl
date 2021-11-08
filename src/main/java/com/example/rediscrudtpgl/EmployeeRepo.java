package com.example.rediscrudtpgl;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class EmployeeRepo {
    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public EmployeeRepo(RedisTemplate redisTemplate) {

        this.hashOperations = redisTemplate.opsForHash();

    }

    public void saveEmployee(Employee employee) {

        hashOperations.put("EMPLOYEE", employee.getId(), employee);

    }

    public List<Employee> findAll() {

        return hashOperations.values("EMPLOYEE");

    }

    public Employee findById(Integer id) {
        return (Employee) hashOperations.get("EMPLOYEE", id);

    }

    public void update(Employee employee) {
        saveEmployee(employee);
    }

    public void delete(Integer id) {
        hashOperations.delete("EMPLOYEE", id);

    }
}
