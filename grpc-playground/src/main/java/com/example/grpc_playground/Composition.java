package com.example.grpc_playground;


import com.example.grpc_playground.models.sec03.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Composition {

	public static void main(String[] args) throws IOException {
		
//		SpringApplication.run(GrpcPlaygroundApplication1.class, args);
		// Tạo Address
		var address = Address.newBuilder()
			.setStreet("123 Main Street")
			.setCity("Atlanta")
			.setState("Georgia")
			.build();

		// Tạo Student
		var student = Student.newBuilder()
			.setName("Sam")
			.setAddress(address)
			.build();

		// Tạo School
		var school = School.newBuilder()
			.setId(1)
			.setName("High School")
			.setAddress(
				Address.newBuilder()
					.setStreet("234 Main Street")
					.setCity("Atlanta")
					.setState("Georgia")
					.build()
			)
			.build();

		// In thông tin
		System.out.println("Student Information:");
		System.out.println(student);

		System.out.println("\nSchool Information:");
		System.out.println(school);
	}

}
