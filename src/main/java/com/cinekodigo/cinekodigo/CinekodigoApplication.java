package com.cinekodigo.cinekodigo;

import com.cinekodigo.cinekodigo.entity.Client;
import com.cinekodigo.cinekodigo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinekodigoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CinekodigoApplication.class, args);
	}

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception{
        Client client1 = new Client("David","Mendoza","edgardo.mendoza1g@gmail.com");
        clientRepository.save(client1);

    }

}
