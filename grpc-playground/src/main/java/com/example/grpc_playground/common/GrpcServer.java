package com.example.grpc_playground.common;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.Arrays;

public class GrpcServer {

    private final Server server;

    private GrpcServer(Server server) {
        this.server = server;
    }

    public static GrpcServer create(int port, BindableService... services) {
        ServerBuilder<?> builder = ServerBuilder.forPort(port);
        Arrays.asList(services).forEach(builder::addService);
        return new GrpcServer(builder.build());
    }

    public GrpcServer start() {
        try {
            server.start();
            System.out.println("Server started on port: " + server.getPort());
            server.getServices().forEach(service ->
                System.out.println("Service registered: " + service.getServiceDescriptor().getName()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to start server", e);
        }
        return this;
    }

    public void await() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException("Server interrupted", e);
        }
    }

    public void stop() {
        server.shutdownNow();
        System.out.println("Server stopped.");
    }

}
