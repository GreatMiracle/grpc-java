package com.example.grpc_playground.sec06;

import com.example.grpc_playground.models.sec06.AccountBalance;
import com.example.grpc_playground.models.sec06.BalanceCheckRequest;
import com.example.grpc_playground.models.sec06.BankServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.stub.StreamObserver;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class GrpcFutureStubClient {

    public static void main(String[] args) {
        // Tạo channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext() // Sử dụng kết nối không bảo mật
            .build();

        // Tạo stub
        BankServiceGrpc.BankServiceFutureStub futureStub = BankServiceGrpc.newFutureStub(channel);

        // Gửi yêu cầu và nhận phản hồi
        BalanceCheckRequest request = BalanceCheckRequest.newBuilder()
            .setAccountNumber(2) // Số tài khoản cần kiểm tra
            .build();

        // Gửi request và nhận về Future
        ListenableFuture<AccountBalance> futureResponse = futureStub.getAccountBalance(request);

        try {
            // Chờ kết quả và in ra
            AccountBalance response = futureResponse.get();
            System.out.println("Số dư tài khoản: " + response.getBalance());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Lỗi khi lấy kết quả: " + e.getMessage());
        } finally {
            // Đóng kết nối
            channel.shutdown();
        }
    }


}
