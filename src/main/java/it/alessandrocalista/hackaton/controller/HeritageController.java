package it.alessandrocalista.hackaton.controller;

import it.alessandrocalista.hackaton.dto.HeritageDto;
import it.alessandrocalista.hackaton.service.HeritageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/heritage")
public class HeritageController {
    private final HeritageService heritageService;

    public HeritageController(HeritageService heritageService) {
        this.heritageService = heritageService;
    }

    @GetMapping("/random")
    public HeritageDto getRandomHeritage() {
        List<HeritageDto> list = heritageService.getAll()
                .stream()
                .filter(heritageDto -> !heritageDto.images().isEmpty())
                .toList();

        List<HeritageDto> newList = new ArrayList<>(list);
        Collections.shuffle(newList);
        return newList.getFirst();
    }

    @GetMapping("/{heritageId}")
    public HeritageDto getHeritage(@PathVariable Long heritageId) {
        return heritageService.get(heritageId);
    }

    @GetMapping("/")
    public List<HeritageDto> getAll() {
        return heritageService.getAll().stream().filter(heritageDto -> !heritageDto.images().isEmpty()).toList();
    }

}
