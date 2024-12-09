package com.example.grpc_playground.sec06;

import com.example.grpc_playground.models.sec06.AccountBalance;
import com.example.grpc_playground.models.sec06.BalanceCheckRequest;
import com.example.grpc_playground.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClient {

    public static void main(String[] args) {
        // Tạo channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext() // Sử dụng kết nối không bảo mật
            .build();

        // Tạo stub
        BankServiceGrpc.BankServiceBlockingStub stub = BankServiceGrpc.newBlockingStub(channel);

        // Gửi yêu cầu và nhận phản hồi
        BalanceCheckRequest request = BalanceCheckRequest.newBuilder()
            .setAccountNumber(2) // Số tài khoản cần kiểm tra
            .build();

        AccountBalance response = stub.getAccountBalance(request);
        System.out.println("Số dư tài khoản: " + response.getBalance());

        // Đóng channel
        channel.shutdown();
    }


}
