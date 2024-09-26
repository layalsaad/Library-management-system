package ObserverDP;

public interface Observable {
    void registerObserver(Observer ob);
    void unregisterObserver(Observer ob);
    void notifyObservers();
}
