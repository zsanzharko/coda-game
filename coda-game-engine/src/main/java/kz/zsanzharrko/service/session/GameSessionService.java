package kz.zsanzharrko.service.session;


import kz.zsanzharrko.gamecard.GameCard;
import kz.zsanzharrko.model.Player;
import kz.zsanzharrko.service.statistic.GameStatisticsState;

import java.util.List;
import java.util.Map;

public interface GameSessionService {

  GameCard addCard(Player player, Integer row, GameCard card);
  boolean removeCard(Player player, Integer row, GameCard card);

  Map<Player, Map<GameStatisticsState, String>> getStatistics();

  Map<Player, Map<Integer, List<GameCard>>> getArena();
}
