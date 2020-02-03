package pl.javastart.restoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.restoffers.dto.OfferDto;
import pl.javastart.restoffers.form.OfferForm;
import pl.javastart.restoffers.service.OfferService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(final OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public List<OfferDto> findOffers(Filters filters) {
        return offerService.findAll(filters);
    }

    @PostMapping("/offers")
    public String createOffer(@Valid @RequestBody OfferForm form, BindingResult bindingResult) {
        offerService.save(form);
    }
}
