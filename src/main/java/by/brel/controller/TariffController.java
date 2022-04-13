package by.brel.controller;

import by.brel.entity.Tariff;
import by.brel.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tariffs")
public class TariffController {

    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/main")
    public String getAllTariffs(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Tariff> tariffList = tariffService.getAllTariffs(page);

        long countAllTariffs = tariffService.getCountAllTariffs();
        long countPages = (countAllTariffs + 9) / 10;

        model.addAttribute("page", page);
        model.addAttribute("countPages", countPages);

        model.addAttribute("tariffs", tariffList);

        return "/tariffs/mainTariffs";
    }

    @GetMapping("/main/{id}")
    public String getTariffById(@PathVariable("id") long id, Model model) {
        Tariff tariff = tariffService.findTariffById(id);

        model.addAttribute("tariff", tariff);

        return "/tariffs/newOrUpdateTariffs";
    }

    @GetMapping("/new")
    public String showNewOrUpdateTariffsView(@ModelAttribute("tariff") Tariff tariff) {
        return "/tariffs/newOrUpdateTariffs";
    }

    @PostMapping("/save")
    public String saveTariff(@ModelAttribute("customer") Tariff tariff) {
        tariffService.saveTariff(tariff);

        return "redirect:/tariffs/main";
    }

    @GetMapping("/update/{id}")
    public String updateTariff(@PathVariable("id") long id, Model model) {
        Tariff tariff = tariffService.findTariffById(id);

        model.addAttribute("tariff", tariff);

        return "/tariffs/newOrUpdateTariffs";
    }

    @GetMapping("/delete/{id}")
    public String deleteTariff(@PathVariable("id") long id) {
        tariffService.deleteTariff(id);

        return "redirect:/tariffs/main";
    }
}
