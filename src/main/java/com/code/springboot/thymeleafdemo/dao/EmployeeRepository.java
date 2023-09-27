package com.code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   /* EmployeeRepository arayüzü, JpaRepository arayüzünü genişletir ve varsa özel sorgularımızı
     belirteceğimiz metodları barındırır. JpaRepository arayüzünden kalıtım ile aldığı 20+ metod sayesinde,
     bizi birçok sorgulama işlemi için sorgular yazmaktan kurtarır. İşin daha da güzel bir yanı ise;
     yazdığımız bu EmployeeRepository arayüzünü bir sınıf ile gerçeklemek zorunda kalmamamızdır.
     Spring Data, repository arayüzlerini uygulama başlatılırken tanır ve kendisi gerçekler.  */


   @Query(value = "select * from employee_directory.employee s where s.first_name like %:keyword% or s.last_name like %:keyword%", nativeQuery = true)
   List<Employee> findByKeyword(@Param("keyword") String keyword);
}
