package com.example.grpc_playground;

import com.example.grpc_playground.sec06.BankService;
import com.example.grpc_playground.sec06.BankService01;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class BankServeExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Tạo server
//        Server server = ServerBuilder.forPort(6565) // Cổng kết nối
//            .addService(new BankService()) // Thêm dịch vụ
//            .build();


        Server server = ServerBuilder.forPort(6565) // Cổng kết nối
            .addService(new BankService01()) // Thêm dịch vụ
            .build();

        // Khởi động server
        server.start();
        System.out.println("Server đang chạy...");

        // Duy trì server
        server.awaitTermination();// Giữ server hoạt động
    }

}
