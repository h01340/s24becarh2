package s24.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s24.car.domain.Car;
import s24.car.domain.CarRepository;

@SpringBootApplication
public class CarApplication {

	private static final Logger log = LoggerFactory.getLogger(CarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(CarRepository carRepository) {
		return (args) -> {

			
			log.info("save some cars");
			carRepository.save(new Car("Minna", "Minna"));
			carRepository.save(new Car("Ford", "Mustang"));
			carRepository.save(new Car("Nissan", "Leaf"));
			carRepository.save(new Car("Toyota", "Prius"));
			carRepository.save(new Car("Toyota", "Prius2"));

			log.info("tulostetaan autot");
			for (Car car : carRepository.findAll()) {
				log.info(car.toString());
			}

			
		};
	}
}
