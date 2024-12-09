package com.example.grpc_playground;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ImportingModules {

	public static void main(String[] args) throws IOException {

// Tạo Address
		com.example.grpc_playground.models.sec04.common.Address address = com.example.grpc_playground.models.sec04.common.Address.newBuilder()
			.setStreet("123 Main St")
			.setCity("Hometown")
			.setZip("12345")
			.build();

		// Tạo Car
		com.example.grpc_playground.models.sec04.common.Car car = com.example.grpc_playground.models.sec04.common.Car.newBuilder()
			.setBodyStyle("Coupe")
			.setModel("Tesla Model S")
			.build();

		// Tạo Person
		com.example.grpc_playground.models.sec04.Person person = com.example.grpc_playground.models.sec04.Person.newBuilder()
			.setName("Sam")
			.setAge(25)
			.setAddress(address)
			.setCar(car)
			.build();

		// In ra thông tin
		System.out.println(person);
	}

}
