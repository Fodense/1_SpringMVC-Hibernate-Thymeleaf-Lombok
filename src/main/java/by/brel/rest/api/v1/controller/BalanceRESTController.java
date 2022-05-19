package by.brel.rest.api.v1.controller;

import by.brel.entity.Balance;
import by.brel.service.BalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Balances")
public class BalanceRESTController {

    private BalanceService balanceService;

    @GetMapping("/balances")
    @ApiOperation(value = "${balances.controller.method.getAllBalances}", position = 0)
    public List<Balance> getAllBalances() {
        return balanceService.getAllBalances();
    }

    @GetMapping("/balances/{id}")
    @ApiOperation(value = "${balances.controller.method.getBalanceById}", position = 1)
    public Balance getBalanceById(
            @ApiParam(value = "${balances.controller.method.param.getBalanceById}", required = true, example = "1")
            @PathVariable("id") long id
    ) {
        return balanceService.findBalanceById(id);
    }

    @PostMapping("/balances")
    @ApiOperation(value = "${balances.controller.method.saveBalance}", position = 2)
    public Balance saveBalance(
            @ApiParam(value = "${balances.controller.method.param.saveBalance}", required = true)
            @RequestBody Balance balance
    ) {
        balanceService.saveBalance(balance);

        return balance;
    }

    @PutMapping("/balances")
    @ApiOperation(value = "${balances.controller.method.updateBalance}", position = 3)
    public Balance updateBalance(
            @ApiParam(value = "${balances.controller.method.param.updateBalance}", required = true)
            @RequestBody Balance balance
    ) {
        balanceService.saveBalance(balance);

        return balance;
    }

    @DeleteMapping("/balances/{id}")
    @ApiOperation(value = "${balances.controller.method.deleteBalance}", position = 4)
    public String deleteBalance(
            @ApiParam(value = "${balances.controller.method.param.deleteBalance}", required = true, example = "1")
            @PathVariable("id") long id
    ) {
        balanceService.deleteBalance(id);

        return "Balance with id " + id + " was deleted";
    }
}
