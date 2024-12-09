package com.example.grpc_playground.sec06;

import com.example.grpc_playground.models.sec06.*;
import com.example.grpc_playground.sec06.repository.AccountRepository;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class BankService01 extends com.example.grpc_playground.models.sec06.BankServiceGrpc.BankServiceImplBase{
    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        Integer balance = AccountRepository.getBalance(accountNumber);

        // Tạo phản hồi
        AccountBalance response = AccountBalance.newBuilder()
            .setAccountNumber(accountNumber)
            .setBalance(balance)
            .build();

        // Gửi phản hồi
        responseObserver.onNext(response); // Gửi một phản hồi đến client.
        responseObserver.onCompleted(); //Thông báo rằng server đã hoàn thành việc xử lý yêu cầu.
    }

    @Override
    public void getAllAccounts(Empty request, StreamObserver<AllAccountsResponse> responseObserver) {
        // Lấy tất cả tài khoản từ repository
        Map<Integer, Integer> accountsMap = AccountRepository.getAllBalances();

        // Chuyển đổi Map thành danh sách AccountBalance
        List<AccountBalance> accountBalances = accountsMap.entrySet().stream()
            .map(entry -> AccountBalance.newBuilder()
                .setAccountNumber(entry.getKey())
                .setBalance(entry.getValue())
                .build())
            .collect(Collectors.toList());

        // Xây dựng phản hồi
        AllAccountsResponse response = AllAccountsResponse.newBuilder()
            .addAllAccounts(accountBalances)
            .build();

        // Gửi phản hồi
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        var accountNumber = request.getAccountNumber(); //stk rút tiền
        var requestedAmount = request.getAmount(); // số tiền cần rut
        var accountBalance = AccountRepository.getBalance(accountNumber); //số tiền có trong tài khoản

//        requestedAmount = Math.min(requestedAmount, 10);

        if (accountBalance < requestedAmount) {
            responseObserver.onCompleted(); // we will change it to proper error later
            return;
        }

        for (int i = 0; i < (requestedAmount / 10); i++) {
            var money = Money.newBuilder().setAmount(10).build();
            responseObserver.onNext(money);
            System.out.println("money sent " + money);
            AccountRepository.deductAmount(accountNumber, 10);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        responseObserver.onCompleted();

    }
}
