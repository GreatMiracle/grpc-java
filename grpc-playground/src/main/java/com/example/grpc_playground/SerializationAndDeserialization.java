package com.example.grpc_playground;


import com.example.grpc_playground.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class SerializationAndDeserialization {

	public static void main(String[] args) throws IOException {
		
//		SpringApplication.run(GrpcPlaygroundApplication1.class, args);
		// Tạo đối tượng Person
		Person person = Person.newBuilder()
			.setName("John Doe")
			.setAge(30)
			.setBalance(1000.50)
			.build();

		// Serialize đối tượng Person
		serialize();

		// Deserialize đối tượng Person
		Person deserializedPerson = deserialize();

		// Kiểm tra sự giống nhau giữa đối tượng ban đầu và đối tượng đã deserialize
		System.out.println("Objects are the same: " + person.equals(deserializedPerson));
    }

	public static void serialize() throws IOException {
		Person person = Person.newBuilder()
			.setName("John Doe")
			.setAge(30)
			.setBalance(1000.50)
			.build();

		// Tạo đường dẫn tạm thời
		Path path = Path.of("person.out");  // Đường dẫn tới tệp
		try (OutputStream output = Files.newOutputStream(path)) {
			person.writeTo(output);  // Ghi đối tượng vào tệp
		}
	}

	public static Person deserialize() throws IOException {
		Path path = Path.of("person.out");
		try (InputStream input = Files.newInputStream(path)) {
			return Person.parseFrom(input); // Đọc đối tượng từ tệp
		}
	}

}
