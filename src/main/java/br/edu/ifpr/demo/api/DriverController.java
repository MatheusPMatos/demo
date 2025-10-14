package br.edu.ifpr.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
