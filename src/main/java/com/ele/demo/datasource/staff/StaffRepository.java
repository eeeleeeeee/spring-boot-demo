package com.ele.demo.datasource.staff;

import com.ele.demo.datasource.staff.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called staffRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
