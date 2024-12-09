package com.example.grpc_playground.common;

import com.example.grpc_playground.sec06.BankService01;

public class Demo {


        public static void main(String[] args) {

            GrpcServer.create(6566, new BankService01())
                .start()
                .await();

        }
}
