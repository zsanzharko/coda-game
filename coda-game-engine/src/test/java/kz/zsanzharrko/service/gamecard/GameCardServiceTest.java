package kz.zsanzharrko.service.gamecard;

import kz.zsanzharrko.gamecard.GameCard;
import kz.zsanzharrko.gamecard.GameCardService;
import kz.zsanzharrko.gamecard.service.SimpleCardServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GameCardServiceTest {

  @Test
  void createInstance() {
    GameCardService service = GameCardService.getInstance(new SimpleCardServiceImpl());

    assertNotNull(service);
  }

  @Test
  void createInstanceWithTestCards() {
    List<GameCard> testCards = List.of(
            new GameCard(UUID.randomUUID(), "", 0),
            new GameCard(UUID.randomUUID(), "", 0),
            new GameCard(UUID.randomUUID(), "", 0),
            new GameCard(UUID.randomUUID(), "", 0)
    );


    GameCardService service = GameCardService.getInstance(new SimpleCardServiceImpl(testCards));

    assertNotNull(service);
    assertEquals(testCards.size(), service.getCards().size());
  }

  @Test
  void assertionInitCache() {
    GameCardService service = GameCardService.getInstance(new SimpleCardServiceImpl());

    assertNotNull(service);
    assertNotEquals(0, service.getCards().size());
  }

  @Test
  void getCardByTitle() {
    UUID test1 = UUID.randomUUID();
    UUID test2 = UUID.randomUUID();
    UUID test3 = UUID.randomUUID();
    UUID test4 = UUID.randomUUID();
    List<GameCard> testCards = List.of(
            new GameCard(test1, "test-1", 0),
            new GameCard(test2, "test-2", 0),
            new GameCard(test3, "test-3", 0),
            new GameCard(test4, "test-4", 0)
    );
    GameCardService service = GameCardService.getInstance(new SimpleCardServiceImpl(testCards));

    Optional<GameCard> card = service.getCardByTitle("test-3");

    assertTrue(card.isPresent());
    assertEquals("test-3", card.get().getTitle());

    card = service.getCardByTitle("test-5");

    assertFalse(card.isPresent());
  }

  @Test
  void getCards() {
    GameCardService service = GameCardService.getInstance(new SimpleCardServiceImpl());
    assertNotNull(service);
    assertNotNull(service.getCards());
    assertNotEquals(0, service.getCards().size());
  }
}