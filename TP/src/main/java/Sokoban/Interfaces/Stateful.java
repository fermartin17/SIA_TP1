package Sokoban.Interfaces;

public interface Stateful<T> {

    //método para saber si llegamos al estado de corte
    Boolean isDone();

    //método para chequear si el estado es válido
    Boolean isValid();
}
