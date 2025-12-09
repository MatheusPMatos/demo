package br.edu.ifpr.demo.service;

import br.edu.ifpr.demo.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class TravelService {

    @Autowired
    private final TravelRepository travelRepo;
    @Autowired
    private final PassengerRepository passengerRepository;
    @Autowired
    private final DriverRepository driverRepository;

    // Passageiro cria a viagem
    public Travel createRequest(long passengerId, String origin, String destination) {

        // Valida se o passageiro existe no banco
       Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passageiro não encontrado")
        );


        Travel tr = new Travel();
        tr.setPassenger(passenger);
        tr.setOrigin(origin);
        tr.setDestination(destination);
        tr.setStatus(TravelStatus.CREATED);
        return travelRepo.save(tr);
    }

    // Motorista aceita a viagem
    public Travel acceptRequest(Long requestId, Driver driver) {
        Travel tr = travelRepo.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));

         driver = driverRepository.findById(driver.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado")
                );
        tr.setDriver(driver);

        // Regra: só pode aceitar se estiver CREATED
        if (tr.getStatus() != TravelStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Viagem não pode ser aceita. Status atual: " + tr.getStatus());
        }

        tr.setDriver(driver);
        tr.setStatus(TravelStatus.ACCEPTED);

        return travelRepo.save(tr);
    }

    public Travel GetTravel(long travelId) {
        // Valida se o passageiro existe no banco
       return travelRepo.findById(travelId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada")
        );
    }
}
