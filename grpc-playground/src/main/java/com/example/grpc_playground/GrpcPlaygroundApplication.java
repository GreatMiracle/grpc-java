package com.example.grpc_playground;

//import com.example.grpc_playground.models.PersonOuterClass.Person;
import com.example.grpc_playground.models.sec01.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GrpcPlaygroundApplication {

	private static final Logger log = LoggerFactory.getLogger(GrpcPlaygroundApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(GrpcPlaygroundApplication.class, args);

//		System.out.println("kien");
//
//		Person person = Person.newBuilder()
//			.setName("miracle")
//			.setAge(26)
//			.build();
//
//		System.out.println("Person Created: "
//			+ person.getName() + ", Age: "
//			+ person.getAge());



		// Tạo hai đối tượng Person khác nhau
		Person person1 = Person.newBuilder()
			.setName("Sam")
			.setAge(12)
			.build();

		Person person2 = Person.newBuilder()
			.setName("Sam")
			.setAge(12)
			.build();

		// So sánh bằng phương thức equals
		System.out.println("person1 equals person2: " + person1.equals(person2)); // true

		// So sánh bằng toán tử ==
		System.out.println("person1 == person2: " + (person1 == person2)); // false

		// Thay đổi tên từ "Sam" thành "Mike" mà không thay đổi các thông tin khác
		Person person3 = person1.toBuilder()
			.setName("Mike")
			.build();

		System.out.println("person1 equals person3: " + person1.equals(person3)); // false
		System.out.println("person1 == person3: " + (person1 == person3)); // false


	}

}
