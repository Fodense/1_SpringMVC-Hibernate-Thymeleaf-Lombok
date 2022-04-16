package by.brel.rest.api.v1.controller;

import by.brel.entity.Tariff;
import by.brel.service.TariffService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TariffRESTController {

    private TariffService tariffService;

    @GetMapping("/tariffs")
    public List<Tariff> getAllTariffs(@RequestParam(defaultValue = "1") int page) {
        return tariffService.getAllTariffs(page);
    }

    @GetMapping("/tariffs/{id}")
    public Tariff getTariffById(@PathVariable("id") long id) {
        return tariffService.findTariffById(id);
    }

    @PostMapping("/tariffs")
    public Tariff saveTariff(@RequestBody Tariff tariff) {
        tariffService.saveTariff(tariff);

        return tariff;
    }

    @PutMapping("/tariffs")
    public Tariff updateTariff(@RequestBody Tariff tariff) {
        tariffService.saveTariff(tariff);

        return tariff;
    }

    @DeleteMapping("/tariffs/{id}")
    public String deleteTariff(@PathVariable("id") long id) {
        tariffService.deleteTariff(id);

        return "Tariff with id " + id + " was deleted";
    }
}
