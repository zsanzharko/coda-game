package kz.zsanzharrko.service.session;


import kz.zsanzharrko.config.GameConfig;
import kz.zsanzharrko.exception.game.CardIsExistInArenaException;
import kz.zsanzharrko.exception.game.GameException;
import kz.zsanzharrko.gamecard.GameCard;
import kz.zsanzharrko.model.Player;
import kz.zsanzharrko.model.PlayerState;
import kz.zsanzharrko.service.session.preparation.SessionGameBalancerService;
import kz.zsanzharrko.service.statistic.GameStatisticsState;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provide full game control on session with players.
 *
 * @author Sanzhar
 * @see GameServiceImpl
 */
@Slf4j
@Getter
public class GameSession implements GameSessionService {
  @Setter
  private GameRoundState roundState;
  private Map<Player, List<GameCard>> playersWithCard;
  private GameArena gameArena;
  private SessionGameBalancerService gameBalancerService;

  public GameSession(List<Player> players, GameConfig gameConfig) throws Exception {
    this.gameArena = new GameArena(players, gameConfig);
    this.gameBalancerService = SessionGameBalancerService.getInstance(gameConfig);
    this.playersWithCard = this.gameBalancerService.preparationBalanceCards(players);
    if (this.playersWithCard == null) {
      throw new Exception("Can't init cards on players, cause ");
    }
    this.roundState = GameRoundState.NONE;
    log.debug("Game session is configured");
  }

  public Set<Player> getPlayers() {
    return new HashSet<>(playersWithCard.keySet());
  }

  void stopSession() {
    if (roundState == GameRoundState.NONE) {
      playersWithCard.keySet().forEach(p -> p.setState(PlayerState.NONE));
      playersWithCard = null;
      gameArena = null;
      gameBalancerService = null;
    }
  }

  @Override
  public GameCard addCard(Player player, Integer row, GameCard card) {
    if (playerNotExist(player) || invalidCard(player, card)) {
      return null;
    }
    return gameArena.addCard(player, row, card);
  }

  @Override
  public boolean removeCard(Player player, Integer row, GameCard card) {
    checkCardInArena(player, card);
    if (playerNotExist(player) || invalidCard(player, card)) {
      return false;
    }
    return gameArena.removeCard(player, row, card);
  }

  private void checkCardInArena(Player player, GameCard card) {
    if (player.getId().equals(card.getPlayerId())) {
      String message = String.format("The card is not a valid deck. Card id: %s, Player id: %s",
          card.getId(),
          player.getId()
      );
      throw new GameException(message);
    }
    var gamePlayerArena = gameArena.getArena(player);
    for (Map.Entry<Integer, List<GameCard>> map : gamePlayerArena.entrySet()) {
      for (GameCard gameCard : map.getValue()) {
        if (gameCard.getId().equals(card.getId())) {
          String message = String.format("Card '%s' is exist in arena", card.getId());
          throw new CardIsExistInArenaException(message);
        }
      }
    }
  }

  @Override
  public Map<Player, Map<GameStatisticsState, String>> getStatistics() {
    return gameArena.getStatistics();
  }

  @Override
  public Map<Player, Map<Integer, List<GameCard>>> getArena() {
    if (gameArena != null) {
      return gameArena.getArena();
    }
    return null;
  }

  private boolean playerNotExist(Player player) {
    return !gameArena.getArena().containsKey(player);
  }

  private boolean invalidCard(Player player, GameCard card) {
    return !playersWithCard.containsKey(player)
        || !playersWithCard.get(player).contains(card);
  }
}
