package com.example.rediscrudtpgl;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class EmployeeRepo {
    private HashOperations hashOperations;
    private ListOperations listOperations;
    private SetOperations setOperations;


    private RedisTemplate redisTemplate;

    public EmployeeRepo(RedisTemplate redisTemplate) {

        //this.hashOperations = redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
        this.setOperations = redisTemplate.opsForSet();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee) {

       // hashOperations.put("EMPLOYEE", employee.getId(), employee);
	//	listOperations.rightPush("EMPLOYEE", employee);
        setOperations.add("EMPLOYEE", employee);
    }

    public Set<Employee> findAll() {

        //return hashOperations.values("EMPLOYEE");
       // return listOperations.range("EMPLOYEE", 0, listOperations.size("EMPLOYEE"));
        return setOperations.members("EMPLOYEE");
    }

    public Employee findById(Integer id) {
       // return (Employee) hashOperations.get("EMPLOYEE", id);
//        List<Employee> employees = listOperations.range("EMPLOYEE", 0, listOperations.size("EMPLOYEE"));
//        for (Employee employee : employees) {
//            if(employee.getId() == id)
//                return employee;
//        }
//        return null;
        return (Employee) setOperations.intersect("EMPLOYEE", id);
    }

    public void update(Employee employee) {
        saveEmployee(employee);
    }

    public void delete(Integer id) {
        //hashOperations.delete("EMPLOYEE", id);
       // listOperations.rightPopAndLeftPush("EMPLOYEE", id);
        setOperations.remove("EMPLOYEE", 0, id);
    }
}
