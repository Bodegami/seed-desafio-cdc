package br.com.deveficiente.bookstore;

import br.com.deveficiente.bookstore.cupom.Cupom;
import br.com.deveficiente.bookstore.cupom.NovoCupomForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

}
