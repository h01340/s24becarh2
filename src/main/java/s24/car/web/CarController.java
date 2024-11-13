package s24.car.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import s24.car.domain.Car;
import s24.car.domain.CarRepository;

@Controller
public class CarController {

	private static final Logger log = LoggerFactory.getLogger(CarController.class);

	/*
	 * private final CarRepository carRepository;
	 * public CarController(CarRepository carRepository) {
	 * this.carRepository = carRepository;
	 * }
	 */
	@Autowired
	private CarRepository carRepository;

	@GetMapping(value = { "/", "main" })
	public String showMainPage() {
		log.info("open main page");
		return "main";
	}

	@GetMapping("/carlist")
	public String showCars(Model model) {
		log.info("Read cars from database..");
		model.addAttribute("cars", carRepository.findAll());
		return "carlist";
	}

	@GetMapping("/newcar")
	public String addCar(Model model) {
		model.addAttribute("car", new Car());
		return "newCar";
	}

	@PostMapping("/saveCar")
	public String saveCar(Car car) {
		log.info("CONTROLLER: Save car: " + car);
		carRepository.save(car);
		return "redirect:carlist";
	}

	@GetMapping("editCar/{id}")
	public String editCar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editCar", carRepository.findById(id));
		return "editCar";
	}

	@PostMapping("/saveEditedCar")
	public String saveEditedCar(Car car) {
		log.info("CONTROLLER: Save edited car: " + car);
		carRepository.save(car);
		return "redirect:carlist";
	}

	@GetMapping("delete/{id}")
	public String deleteCar(@PathVariable("id") Long id) {
		log.info("delete car " + id);
		carRepository.deleteById(id);
		return "redirect:/carlist";
	}

}
