package com.example.grpc_playground;

import com.example.grpc_playground.models.sec03.CarList;
import com.example.grpc_playground.models.sec03.CarType;
import com.example.grpc_playground.models.sec03.Dealer;
import com.example.grpc_playground.models.sec03.Inventory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MapExample {

    public static void main(String[] args) throws IOException {
// Tạo các đối tượng CarType
        var car1 = CarType.newBuilder()
            .setMake("Honda")
            .setModel("Civic")
            .setYear(2000)
            .build();

        var car2 = CarType.newBuilder()
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear(2002)
            .build();

        // Tạo danh sách xe cho năm 2002
        var car3 = CarType.newBuilder()
            .setMake("Ford")
            .setModel("Focus")
            .setYear(2002)
            .build();

        // Tạo đối tượng Dealer và thêm xe vào inventory
        var dealer = Dealer.newBuilder()
            .putInventory(car1.getYear(), car1)
            .putInventory(car2.getYear(), car2)
            .build();

        var carList2000 = CarList.newBuilder()
            .addCars(car1)
            .addCars(car2)
            .build();

        var carList2002 = CarList.newBuilder()
            .addCars(car3)
            .build();

        // Tạo Inventory (ánh xạ năm -> danh sách xe)
        var inventory = Inventory.newBuilder()
            .putInventory(2000, carList2000)
            .putInventory(2002, carList2002)
            .build();

        // Kiểm tra thông tin trong inventory
        System.out.println("Dealer Inventory: " + dealer);

        // Kiểm tra xe theo năm
        int yearToCheck = 2002;
        boolean containsCar = dealer.containsInventory(yearToCheck);
        System.out.println("Contains car for year " + yearToCheck + ": " + containsCar);

        if (containsCar) {
            var car = dealer.getInventoryOrThrow(yearToCheck);
            System.out.println("Car for year " + yearToCheck + ": " + car);
        } else {
            System.out.println("No car found for year " + yearToCheck);
        }

        // Lấy toàn bộ Map từ dealer
        var inventoryMap = dealer.getInventoryMap();
        System.out.println("Full Inventory Map: " + inventoryMap);

        // Lấy danh sách xe của năm 2000
        if (inventory.containsInventory(2000)) {
            var cars2000 = inventory.getInventoryOrThrow(2000);
            System.out.println("Cars in 2000: " + cars2000);
        }

    }
}
