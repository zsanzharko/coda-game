package kz.zsanzharrko.gamecard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

/**
 * Presentation card for get information.
 */
@Getter
@Setter
@AllArgsConstructor
public class GameCard {
  private UUID id;
  private String title;
  private String description;
  private Integer power;
  private UUID playerId;

  public GameCard(UUID id, String title, int power) {
    this.id = id;
    this.title = title;
    this.description = "";
    this.power = power;
    this.playerId = null;
  }

  public GameCard(UUID id, String title, String description, int power) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.power = power;
    this.playerId = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GameCard card = (GameCard) o;
    return Objects.equals(playerId, card.playerId) && Objects.equals(id, card.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, playerId);
  }
}
