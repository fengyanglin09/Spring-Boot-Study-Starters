package diy.mqml.myretroapp.service;


import diy.mqml.myretroapp.board.Card;
import diy.mqml.myretroapp.board.RetroBoard;
import diy.mqml.myretroapp.exception.CardNotFoundException;
import diy.mqml.myretroapp.persistence.Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class RetroBoardService {

    Repository<RetroBoard,UUID> repository;

    public RetroBoard save(RetroBoard domain) {
        if (domain.getCards() == null)
            domain.setCards(new ArrayList<>());
        return this.repository.save(domain);
    }

    public RetroBoard findById(UUID uuid) {
        return this.repository.findById(uuid).get();
    }

    public Iterable<RetroBoard> findAll() {
        return this.repository.findAll();
    }

    public void delete(UUID uuid) {
        this.repository.delete(uuid);
    }

    public Iterable<Card> findAllCardsFromRetroBoard(UUID uuid) {
        return this.findById(uuid).getCards();
    }

    public Card addCardToRetroBoard(UUID uuid, Card card){
        if (card.getId() == null)
            card.setId(UUID.randomUUID());

        RetroBoard retroBoard = this.findById(uuid);
        List<Card> cardList = new ArrayList<>(retroBoard.getCards());
        cardList.add(card);

        retroBoard.setCards(cardList);
        return card;
    }

    public Card findCardByUUIDFromRetroBoard(UUID uuid, UUID uuidCard){
        RetroBoard retroBoard = this.findById(uuid);
        Optional<Card> card = retroBoard.getCards().stream().filter(c -> c.getId().equals(uuidCard)).findFirst();
        if (card.isPresent())
            return card.get();
        throw new CardNotFoundException();
    }

    public void removeCardFromRetroBoard(UUID uuid, UUID cardUUID){
        RetroBoard retroBoard = this.findById(uuid);
        List<Card> cardList = new ArrayList<>(retroBoard.getCards());
        cardList.removeIf(card -> card.getId().equals(cardUUID));
        retroBoard.setCards(cardList);
    }
}
