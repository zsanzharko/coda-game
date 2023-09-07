package kz.zsanzharrko.pojo;

import kz.zsanzharrko.gamecard.GameCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class GameCardTest {

  @Test
  void createNewObject() {
    UUID id = UUID.randomUUID();
    UUID playerId = UUID.randomUUID();
    new GameCard(id, "Title", "", 1, playerId);
  }

  @Test
  void createObjectAndUpdateTitle() {
    UUID id = UUID.randomUUID();
    UUID playerId = UUID.randomUUID();
    GameCard card = new GameCard(id, "Title", "", 1, playerId);
    card.setTitle("New_Title");
    Assertions.assertEquals("New_Title", card.getTitle());
  }

  @Test
  void createObjectAndUpdateDescription() {
    UUID id = UUID.randomUUID();
    UUID playerId = UUID.randomUUID();
    GameCard card = new GameCard(id, "Title", "", 1, playerId);
    card.setDescription("New_Desc");
    Assertions.assertEquals("New_Desc", card.getDescription());
  }

  @Test
  void createObjectAndUpdatePower() {
    UUID id = UUID.randomUUID();
    UUID playerId = UUID.randomUUID();
    GameCard card = new GameCard(id, "Title", "", 1, playerId);
    card.setPower(2);
    Assertions.assertEquals(2, card.getPower());
  }
}