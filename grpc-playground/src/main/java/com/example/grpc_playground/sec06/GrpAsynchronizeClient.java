package com.example.grpc_playground.sec06;

import com.example.grpc_playground.models.sec06.AccountBalance;
import com.example.grpc_playground.models.sec06.BalanceCheckRequest;
import com.example.grpc_playground.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpAsynchronizeClient {

    public static void main(String[] args) {
        // Tạo channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext() // Sử dụng kết nối không bảo mật
            .build();

        // Tạo stub
        BankServiceGrpc.BankServiceStub asyncStub = BankServiceGrpc.newStub(channel);

        // Gửi yêu cầu và nhận phản hồi
        BalanceCheckRequest request = BalanceCheckRequest.newBuilder()
            .setAccountNumber(2) // Số tài khoản cần kiểm tra
            .build();

        // Gửi request và xử lý phản hồi bằng StreamObserver
        asyncStub.getAccountBalance(request, new StreamObserver<AccountBalance>() {

            @Override
            public void onNext(AccountBalance accountBalance) {
                System.out.println("Số dụ tài khoản: " + accountBalance.getBalance());
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("Error occurred: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Request completed.");
            }
        });

        // Giữ main thread hoạt động để chờ phản hồi
        try {
            Thread.sleep(5000); // Đợi phản hồi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Đóng channel
        channel.shutdown();
    }


}
