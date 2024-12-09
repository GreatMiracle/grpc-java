package com.example.grpc_playground.sec06;

import com.example.grpc_playground.models.sec06.WithdrawRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GrpcServerStreamingClient {

    public static void main(String[] args) {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
//            .usePlaintext()
//            .build();
//        WithdrawRequest request = WithdrawRequest.newBuilder()
//            .setAmount(50) // Số tiền cần rút
//            .build();
//
//        StreamObserver<WithdrawResponse> responseObserver = new StreamObserver<WithdrawResponse>() {
//            @Override
//            public void onNext(WithdrawResponse value) {
//                System.out.println("Rút tiền: " + value.getAmount() + " " + value.getCurrency());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.err.println("Lỗi: " + t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Hoàn tất rút tiền.");
//            }
//        };
//
//        asyncStub.withdrawMoney(request, responseObserver);
//
//        try {
//            Thread.sleep(60000); // Đợi để nhận hết các phản hồi từ server
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        channel.shutdown();

    }
}