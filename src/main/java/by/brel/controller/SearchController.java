package by.brel.controller;

import by.brel.entity.Tariff;
import by.brel.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final TariffService tariffService;

    @Autowired
    public SearchController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/search")
    public String getResultSearch(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(defaultValue = "1") int page,
                                  Model model) {
        List<Tariff> tariffList = tariffService.findTariffByTitle(keyword.trim());

        long countAllTariffs = tariffService.getCountAllTariffsSearch(keyword);
        long countPages = (countAllTariffs + 9) / 10;

        model.addAttribute("page", page);
        model.addAttribute("countPages", countPages);

        model.addAttribute("tariffs", tariffList);

        return "/tariffs/mainTariffs";
    }
}
