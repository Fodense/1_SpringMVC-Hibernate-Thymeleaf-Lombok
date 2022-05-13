package by.brel.rest.api.v1.controller;

import by.brel.entity.Tariff;
import by.brel.service.TariffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Tariffs")
public class TariffRESTController {

    private TariffService tariffService;

    @GetMapping("/tariffs")
    @ApiOperation(value = "${tariffs.controller.method.getAllTariffs}")
    public List<Tariff> getAllTariffs() {
        return tariffService.getAllTariffs();
    }

    @GetMapping("/tariffs/{id}")
    @ApiOperation(value = "${tariffs.controller.method.getCustomerById}")
    public Tariff getTariffById(
            @ApiParam(value = "${tariffs.controller.method.param.getCustomerById}", required = true, example = "1")
            @PathVariable("id") long id
    ) {
        return tariffService.findTariffById(id);
    }

    @PostMapping("/tariffs")
    @ApiOperation(value = "${tariffs.controller.method.saveTariff}")
    public Tariff saveTariff(
            @ApiParam(value = "${tariffs.controller.method.param.saveTariff}", required = true)
            @RequestBody Tariff tariff
    ) {
        tariffService.saveTariff(tariff);

        return tariff;
    }

    @PutMapping("/tariffs")
    @ApiOperation(value = "${tariffs.controller.method.updateTariff}")
    public Tariff updateTariff(
            @ApiParam(value = "${tariffs.controller.method.param.updateTariff}", required = true)
            @RequestBody Tariff tariff
    ) {
        tariffService.saveTariff(tariff);

        return tariff;
    }

    @DeleteMapping("/tariffs/{id}")
    @ApiOperation(value = "${tariffs.controller.method.deleteTariff}")
    public String deleteTariff(
            @ApiParam(value = "${tariffs.controller.method.param.deleteTariff}", required = true, example = "1")
            @PathVariable("id") long id
    ) {
        tariffService.deleteTariff(id);

        return "Tariff with id " + id + " was deleted";
    }
}
