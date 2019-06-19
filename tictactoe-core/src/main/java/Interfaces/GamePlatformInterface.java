package Interfaces;


//Интерфейс для связи с платформой
public interface GamePlatformInterface
{
    void displayMenu();
    void displayOpponentChoosingMenu();
    void displayField(char[][] gameField);
    void displaySignCombinationChoosingMenu(String player1, String player2);
    void displayEndOfGame(String[] results);
    String enterAnswer();
    String enterTypeOfGame();
    String enterPlayer(int playerNum);
    char[] enterSign();
    int[] enterCoordinates(String playerName, char playerSign);
    boolean checkPlayersNamesMatch(String[] playersNames);

}