package com.ele.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called staffRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
