package manske.locadora.manskemathes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import manske.locadora.manskemathes.controller.LocadoraControler;
import manske.locadora.manskemathes.model.Filmes;
import manske.locadora.manskemathes.repository.FilmeRepository; 

@SpringBootApplication
public class ManskemathesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManskemathesApplication.class, args);
	}

	/*@Bean
	CommandLineRunner initDatabase(FilmeRepository filmeRepository) {
			return args ->{
			Filmes c = new Filmes();
			c.setNome("Teste");
			c.setGenero("Teste G");

			filmeRepository.save(c);
		};
	}*/
	
}
