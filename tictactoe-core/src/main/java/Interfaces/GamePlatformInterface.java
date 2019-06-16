package Interfaces;


//Интерфейс для связи с платформой
public interface GamePlatformInterface
{
    void displayMenu();
    void displayOpponentChoosingMenu();
    void displayField(char[][] gameField);
    void displaySignCombinationChoosingMenu(String[] playersNames);
    void displayEndOfGame(String[] results);
    String enterAnswer();
    String enterTypeOfGame();
    String[] enterPlayerMultiplayer();
    String enterPlayer(int playerNum);
    char[] enterSign();
    int[] enterCoordinates(String playerName, char playerSign);

}