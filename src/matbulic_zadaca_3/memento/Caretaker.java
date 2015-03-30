/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.memento;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bule
 */
public class Caretaker {

    private List<Object> savedStates = new LinkedList<>();

    public void addMemento(Object m) {
        savedStates.add(m);
    }

    public Object getMemento(int index) {
        return savedStates.get(index);
    }

    public Object getMementoAndFlush(int index) {
        Object m = savedStates.get(index);
        savedStates = savedStates.subList(index, savedStates.size());
        return m;
    }

    public List<Object> getSavedStates() {
        return savedStates;
    }

}
