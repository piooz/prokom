package jaka.jakakolwiek.nonesense;

import java.util.*;

public abstract class Observable<T> {

    List<IObserver<T>> observers = new ArrayList<IObserver<T>>();

    public void notifyObservers(T event) {
        observers.forEach(observer -> observer.update(event));
    }

    public void addObserver(IObserver<T> observer) {
        observers.add(observer);
    }

}
