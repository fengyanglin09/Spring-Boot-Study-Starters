package diy.mqml.myretroapp.service;



import diy.mqml.myretroapp.board.Card;
import diy.mqml.myretroapp.board.RetroBoard;
import diy.mqml.myretroapp.persistence.RetroBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RetroBoardService {

    RetroBoardRepository retroBoardRepository;

    public Mono<RetroBoard> save(RetroBoard domain) {
        return this.retroBoardRepository.save(domain);
    }

    public Mono<RetroBoard> findById(UUID uuid) {
        return this.retroBoardRepository.findById(uuid);
    }

    public Flux<RetroBoard> findAll() {
        return this.retroBoardRepository.findAll();
    }

    public Mono<Void> delete(UUID uuid) {
        return this.retroBoardRepository.deleteById(uuid);
    }

    public Flux<Card> findAllCardsFromRetroBoard(UUID uuid) {
        return this.findById(uuid).flatMapIterable(RetroBoard::getCards);
    }

    public Mono<Card> addCardToRetroBoard(UUID uuid, Card card) {
        return this.findById(uuid).flatMap(retroBoard -> {
            if (card.getId() == null)
                card.setId(UUID.randomUUID());
            retroBoard.getCards().add(card);
            return this.save(retroBoard).thenReturn(card);
        });
    }

    public Mono<Card> findCardByUUID(UUID uuidCard) {
        Mono<RetroBoard> result = retroBoardRepository.findRetroBoardByIdAndCardId(uuidCard);
        return result.flatMapIterable(RetroBoard::getCards).filter(card -> card.getId().equals(uuidCard)).next();
    }

    public Mono<Void> removeCardByUUID(UUID uuid, UUID cardUUID) {
        return retroBoardRepository.removeCardFromRetroBoard(uuid, cardUUID);
    }
}
