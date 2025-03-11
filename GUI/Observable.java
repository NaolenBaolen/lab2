package GUI;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void uppdateObservers(CarFactery carFactery);
}
