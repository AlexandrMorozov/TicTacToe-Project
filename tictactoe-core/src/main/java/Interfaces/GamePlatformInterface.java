package Interfaces;


//Интерфейс для связи с платформой
public interface GamePlatformInterface
{
    void displayMenu();
    String enterAnswer();
    void displayField(char[][] gameField);
}