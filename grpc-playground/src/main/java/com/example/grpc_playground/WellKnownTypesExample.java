package com.example.grpc_playground;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.Instant;

@SpringBootApplication
public class WellKnownTypesExample {

	public static void main(String[] args) throws IOException {

		// Tạo một đối tượng User
		com.example.grpc_playground.models.sec04.User.Builder userBuilder = com.example.grpc_playground.models.sec04.User
			.newBuilder()
			.setAge(Int32Value.of(25)); // Tuổi: 25

		// Sử dụng Timestamp cho thời gian đăng nhập
		Instant now = Instant.now(); // Lấy thời gian hiện tại
		Timestamp loginTime = Timestamp.newBuilder()
			.setSeconds(now.getEpochSecond())
			.setNanos(now.getNano())
			.build();
		userBuilder.setLoginTime(loginTime);

		// Xây dựng User
		com.example.grpc_playground.models.sec04.User user = userBuilder.build();


		// Hiển thị thông tin User
		System.out.println("Age: " + user.getAge().getValue()); // Lấy giá trị từ Wrapper Type
		System.out.println("Login Time: " + Instant.ofEpochSecond(user.getLoginTime().getSeconds(), user.getLoginTime().getNanos()));

	}

}
