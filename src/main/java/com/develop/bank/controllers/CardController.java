package com.develop.bank.controllers;

import com.develop.bank.model.Card;
import com.develop.bank.model.card.CardAmountType;
import com.develop.bank.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/cards/add")
    @ResponseBody
    public Card addCard(@RequestHeader("token") String token, @RequestBody CardAmountType amountType) {
        Card card = cardService.addCard(token, amountType.getAmountType());
        return card;
    }

    @GetMapping("/cards/{cardNumber}")
    @ResponseBody
    public Card getCard(@RequestHeader("token") String token, @PathVariable("cardNumber") String cardNumber) {
        Card card = cardService.getCard(token, cardNumber);
        return card;
    }

    @GetMapping("/cards/get")
    @ResponseBody
    public List<Card> getCards(@RequestHeader("token") String token) {
        List<Card> cards = cardService.getCards(token);
        return cards;
    }

    @DeleteMapping("/cards/{cardNumber}")
    public String removeCard(@RequestHeader("token") String token, @PathVariable("cardNumber") String cardNumber) {
        String status = cardService.removeCard(token, cardNumber);
        return status;
    }
}
