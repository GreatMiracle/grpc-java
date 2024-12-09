package com.example.grpc_playground;


import com.example.grpc_playground.models.sec03.Credentials;
import com.example.grpc_playground.models.sec03.Email;
import com.example.grpc_playground.models.sec03.Phone;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class OneOfExample {

	public static void main(String[] args) throws IOException {

		// Tạo object Email
		Email email = Email.newBuilder()
			.setAddress("user@example.com")
			.setPassword("password123")
			.build();

		// Tạo object Phone
		Phone phone = Phone.newBuilder()
			.setNumber("0123456789")
			.setCode("123456")
			.build();

		// Tạo Credential sử dụng Email
		Credentials credentialWithEmail = Credentials.newBuilder()
			.setEmail(email)
			.build();

		// Tạo Credential sử dụng Phone
		Credentials credentialWithPhone = Credentials.newBuilder()
			.setPhone(phone)
			.build();

		// Gọi hàm xử lý login
		login(credentialWithEmail);
		login(credentialWithPhone);
	}

	private static void login(Credentials credential) {
		// Kiểm tra loại login_method
		switch (credential.getLoginTypeCase()) {
			case EMAIL:
				System.out.println("Đăng nhập bằng Email: " + credential.getEmail().getAddress());
				break;
			case PHONE:
				System.out.println("Đăng nhập bằng Phone: " + credential.getPhone().getNumber());
				break;
			default:
				System.out.println("Phương thức đăng nhập không được thiết lập!");
				break;
		}
	}

}
