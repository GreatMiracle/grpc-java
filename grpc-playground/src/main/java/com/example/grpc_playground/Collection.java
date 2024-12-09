package com.example.grpc_playground;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Collection {

	public static void main(String[] args) throws IOException {
		
//		SpringApplication.run(GrpcPlaygroundApplication1.class, args);
		// Tạo các đối tượng Book
		var book1 = com.example.grpc_playground.models.sec03.Book.newBuilder()
			.setTitle("Harry Potter Part 1")
			.setAuthor("J.K. Rowling")
			.setPublicationYear(1997)
			.build();

		var book2 = com.example.grpc_playground.models.sec03.Book.newBuilder()
			.setTitle("Harry Potter Part 2")
			.setAuthor("J.K. Rowling")
			.setPublicationYear(1998)
			.build();

		var book3 = com.example.grpc_playground.models.sec03.Book.newBuilder()
			.setTitle("Harry Potter Part 3")
			.setAuthor("J.K. Rowling")
			.setPublicationYear(1999)
			.build();

		// Tạo Library chứa danh sách các Book
		var library = com.example.grpc_playground.models.sec03.Library.newBuilder()
			.setName("Fantasy Library")
			.addBooks(book1)
			.addBooks(book2)
			.addBooks(book3)
			.build();

		// In thông tin Library
		System.out.println("Library Information:");
		System.out.println("Name: " + library.getName());
		System.out.println("Books: ");


		library.getBooksList().forEach(book -> {
			System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (" + book.getPublicationYear() + ")");
		});

		// Truy cập thông tin cụ thể
		System.out.println("\nTotal books: " + library.getBooksCount());
		System.out.println("First book: " + library.getBooks(0).getTitle());

		// Tạo Library chứa danh sách các Book
		var libraryList = com.example.grpc_playground.models.sec03.Library.newBuilder()
			.setName("fantasy library")
			.addAllBooks(List.of(book1, book2, book3))
			.build();
	}

}
