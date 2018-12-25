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
    public Card addCard(@RequestHeader("token") String token, @RequestHeader("username") String username,
                        @RequestBody CardAmountType amountType) {
        Card card = cardService.addCard(username, token, amountType.getAmountType());
        return card;
    }

    @GetMapping("/cards/get/{cardNumber}")
    @ResponseBody
    public Card getCard(@RequestHeader("username") String username, @RequestHeader("token") String token,
                        @PathVariable("cardNumber") String cardNumber) {
        Card card = cardService.getCard(username, token, cardNumber);
        return card;
    }

    @GetMapping("/cards/get")
    @ResponseBody
    public List<Card> getCards(@RequestHeader("username") String username, @RequestHeader("token") String token) {
        List<Card> cards = cardService.getCards(username, token);
        return cards;
    }

    @DeleteMapping("/cards/{cardNumber}")
    public String removeCard(@RequestHeader("username") String username, @RequestHeader("token") String token,
                             @PathVariable("cardNumber") String cardNumber) {
        String status = cardService.removeCard(username, token, cardNumber);
        return status;
    }
}
