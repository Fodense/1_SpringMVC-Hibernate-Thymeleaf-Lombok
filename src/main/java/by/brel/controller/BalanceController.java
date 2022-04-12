package by.brel.controller;

import by.brel.entity.Balance;
import by.brel.entity.Customer;
import by.brel.entity.Tariff;
import by.brel.service.BalanceService;
import by.brel.service.CustomerService;
import by.brel.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;
    private final CustomerService customerService;
    private final TariffService tariffService;

    @Autowired
    public BalanceController(BalanceService balanceService, CustomerService customerService, TariffService tariffService) {
        this.balanceService = balanceService;
        this.customerService = customerService;
        this.tariffService = tariffService;
    }

    @GetMapping("/main")
    public String getAllBalances(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Balance> balances = balanceService.getAllBalances(page);

        long countAllBalances = balanceService.getCountAllBalances();
        long countPages = (countAllBalances + 9) / 10;

        model.addAttribute("page", page);
        model.addAttribute("countPages", countPages);

        model.addAttribute("balances", balances);

        return "/balances/mainBalances";
    }

    @GetMapping("/main/{id}")
    public String getBalancesById(@PathVariable("id") long id, Model model) {
        Balance balance = balanceService.findBalanceById(id);

        List<Customer> customers = customerService.getAllCustomers();
        List<Tariff> tariffs = tariffService.getAllTariffs();

        model.addAttribute("customers", customers);
        model.addAttribute("tariffs", tariffs);

        model.addAttribute("balance", balance);

        return "/balances/newOrUpdateBalances";
    }

    @GetMapping("/new")
    public String showNewOrUpdateBalancesView(@ModelAttribute("balance") Balance balance, Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        List<Tariff> tariffs = tariffService.getAllTariffs();

        model.addAttribute("customers", customers);
        model.addAttribute("tariffs", tariffs);

        return "/balances/newOrUpdateBalances";
    }

    @PostMapping("/save")
    public String saveBalance(@ModelAttribute("balance") Balance balance) {
        balanceService.saveBalance(balance);

        return "redirect:/balances/main";
    }

    @GetMapping("/update/{id}")
    public String updateBalance(@PathVariable("id") long id, Model model) {
        Balance balance = balanceService.findBalanceById(id);
        List<Customer> customers = customerService.getAllCustomers();
        List<Tariff> tariffs = tariffService.getAllTariffs();

        model.addAttribute("balance", balance);
        model.addAttribute("customers", customers);
        model.addAttribute("tariffs", tariffs);

        return "/balances/newOrUpdateBalances";
    }

    @GetMapping("/delete/{id}")
    public String deleteBalance(@PathVariable("id") long id) {
        balanceService.deleteBalance(id);

        return "redirect:/balances/main";
    }
}