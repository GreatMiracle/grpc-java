package com.example.grpc_playground.sec06;

import com.example.grpc_playground.models.sec06.AccountBalance;
import com.example.grpc_playground.models.sec06.BalanceCheckRequest;
import io.grpc.stub.StreamObserver;


public class BankService extends com.example.grpc_playground.models.sec06.BankServiceGrpc.BankServiceImplBase{
    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        int accountNumber = request.getAccountNumber();

        // Xử lý logic lấy số dư
        int balance = 1000; // Ví dụ số dư là 1000

        // Tạo phản hồi
        AccountBalance response = AccountBalance.newBuilder()
            .setAccountNumber(accountNumber)
            .setBalance(balance)
            .build();

        // Gửi phản hồi
        responseObserver.onNext(response); // Gửi một phản hồi đến client.
        responseObserver.onCompleted(); //Thông báo rằng server đã hoàn thành việc xử lý yêu cầu.
    }
}
