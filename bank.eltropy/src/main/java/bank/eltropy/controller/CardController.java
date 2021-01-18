package bank.eltropy.controller;


import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")

public class CardController {

    private final CardFacade cardFacade;

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@NonNull @RequestParam long accountId) {
        log.info("Card creation {}", accountId);
        cardFacade.create(accountId);
    }
}