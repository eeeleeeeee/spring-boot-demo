package com.ele.demo.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "students")
public class Student {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String major;

    private Double gpa;

    /**
     * @Field is optional when the MongoDB field name matches the Java field name exactly.
     * In this case, both are department, so the annotation does nothing extra.
     * In practice you'd only need it when they differ
     * @Field("dept") // MongoDB stores it as "dept"
     * private String department;  // but Java calls it "department"
     */
    @Field("department")
    private String department;
}
