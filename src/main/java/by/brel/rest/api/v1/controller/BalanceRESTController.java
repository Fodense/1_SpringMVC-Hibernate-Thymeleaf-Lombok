package by.brel.rest.api.v1.controller;

import by.brel.entity.Balance;
import by.brel.service.BalanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BalanceRESTController {

    private BalanceService balanceService;

    @GetMapping("/balances")
    public List<Balance> getAllBalances(@RequestParam(defaultValue = "1") int page) {
        return balanceService.getAllBalances(page);
    }

    @GetMapping("/balances/{id}")
    public Balance getBalanceById(@PathVariable("id") long id) {
        return balanceService.findBalanceById(id);
    }

    @PostMapping("/balances")
    public Balance saveBalance(@RequestBody Balance balance) {
        balanceService.saveBalance(balance);

        return balance;
    }

    @PutMapping("/balances")
    public Balance updateBalance(@RequestBody Balance balance) {
        balanceService.saveBalance(balance);

        return balance;
    }

    @DeleteMapping("/balances/{id}")
    public String deleteBalance(@PathVariable("id") long id) {
        balanceService.deleteBalance(id);

        return "Balance with id " + id + " was deleted";
    }
}
