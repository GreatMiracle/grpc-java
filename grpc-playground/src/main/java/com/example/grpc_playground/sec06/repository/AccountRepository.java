package com.example.grpc_playground.sec06.repository;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountRepository {
    // Giả lập cơ sở dữ liệu bằng Map
    private static final Map<Integer, Integer> db = IntStream.rangeClosed(1, 10)
        .boxed()
        .collect(Collectors.toConcurrentMap(
            accountNumber -> accountNumber, // Key: Số tài khoản
            value -> 100           // Value: Số dư mặc định
        ));

    // Lấy số dư tài khoản
    public static Integer getBalance(int accountNumber) {
        return db.get(accountNumber);
    }

    // Cập nhật số dư tài khoản
    public static void updateBalance(int accountNumber, int newBalance) {
        db.put(accountNumber, newBalance);
    }

    public static Map<Integer, Integer> getAllBalances() {
        // Trả về Map không thể thay đổi
        return Collections.unmodifiableMap(db);
    }

    //trừ tiền vào tk sau mỗi lần rút
    public static void deductAmount(int accountNumber, int amount){
        db.computeIfPresent(accountNumber, (k, v) -> v - amount);
    }


}
