/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.memento;

import matbulic_zadaca_3.client.composite.IDatoteka;

/**
 *
 * @author Bule
 */
public class Originator {

    private IDatoteka structure;

    public void setStructure(IDatoteka structure) {
        this.structure = structure;
    }

    public Object saveToMemento() {
        return new Memento(structure);
    }

    public IDatoteka restoreFromMemento(Object m) {
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            return memento.getStructure();
        }
        return null;

    }
}
