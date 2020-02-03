package pl.javastart.restoffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.controller.Filters;
import pl.javastart.restoffers.dto.OfferDto;
import pl.javastart.restoffers.form.OfferForm;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.model.Offer;
import pl.javastart.restoffers.repository.CategoryRepository;
import pl.javastart.restoffers.repository.OfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public OfferService(final OfferRepository offerRepository, final CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferDto> findAll(final Filters filters) {
        final String titleFilter = Optional.ofNullable(filters.getTitle())
            .orElse("");

        final List<Offer> all = offerRepository.findAllByTitleContaining(titleFilter);

        return all.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    private OfferDto toDto(final Offer offer) {
        return new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(),
            offer.getPrice(), offer.getCategory().getName());
    }

    public void save(final OfferForm form) {
        final String categoryName = form.getCategory();
        final Category category = categoryRepository.findByName(categoryName)
            .orElseThrow(IllegalArgumentException::new);
        final Offer offer = new Offer(form.getTitle(), form.getDescription(),
            form.getImgUrl(), form.getPrice(), category);
        offerRepository.save(offer);
    }
}
