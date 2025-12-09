package br.edu.ifpr.demo.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.demo.domain.Driver;
import br.edu.ifpr.demo.domain.Travel;
import br.edu.ifpr.demo.dto.TravelAceptDto;
import br.edu.ifpr.demo.dto.TravelRequestDto;
import br.edu.ifpr.demo.service.TravelService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController {
    @Autowired
    private final TravelService travelService;

    @GetMapping("/{id}")
    public Travel get(@PathVariable("id") Long id) {
        return travelService.GetTravel(id);
    }

    @PostMapping()
    public Travel create(@RequestBody TravelRequestDto dto) {
        return travelService.createRequest(dto.passengerId(), dto.origin(), dto.destination());
    }


    @PutMapping("/{id}")
    public Travel accept(@PathVariable("id") Long id, @RequestBody TravelAceptDto dto) {

        Driver d = new Driver();
        d.setId(dto.driverId()); // Ideal: buscar do banco

        return travelService.acceptRequest(id, d);
    }

}
