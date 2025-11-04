package br.edu.ifpr.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.demo.domain.Driver;
import br.edu.ifpr.demo.domain.DriverRepository;

@Service
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    @GetMapping("/drivers")
    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    // melhorar o tratamento de exceção
    @GetMapping("/drivers/{id}")
    public Driver findDriver(@PathVariable("id") Long id) {
        return driverRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/drivers")
    public Driver createDriver(@RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    //altera o objeto inteiro
    @PutMapping("/drivers/{id}")
    public Driver fullUpdateDriver(@PathVariable("id") Long id, @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);
        foundDriver.setName(driver.getName());
        foundDriver.setBirthDate(driver.getBirthDate());
        return driverRepository.save(foundDriver);
    }

    @PatchMapping("/drivers/{id}")
    public Driver incrementalUpdateDriver(@PathVariable("id") Long id, @RequestBody Driver driver){
        Driver foundDriver = findDriver(id);

        foundDriver.setName(Optional.ofNullable(driver.getName())
        .orElse(foundDriver.getName()));

        foundDriver.setBirthDate(Optional.ofNullable(driver.getBirthDate())
        .orElse(foundDriver.getBirthDate()));
        return driverRepository.save(foundDriver);
    }

    @DeleteMapping("/drivers/{id}")
    public void deleteDriver(@PathVariable("id") Long id){
        driverRepository.deleteById(id);
    }

}
