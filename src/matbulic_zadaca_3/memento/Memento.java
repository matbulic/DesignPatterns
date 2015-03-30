/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.memento;

import java.util.Date;
import matbulic_zadaca_3.client.composite.IDatoteka;

/**
 *
 * @author Bule
 */
public class Memento {

    private Date timeOfSave;
    private IDatoteka structure;

    public Memento(IDatoteka structure) {
        this.structure = structure;
        this.timeOfSave = new Date();
    }

    public Date getTimeOfSave() {
        return timeOfSave;
    }

    public void setTimeOfSave(Date timeOfSave) {
        this.timeOfSave = timeOfSave;
    }

    public IDatoteka getStructure() {
        return structure;
    }

    public void setStructure(IDatoteka structure) {
        this.structure = structure;
    }

    @Override
    public String toString() {
        return "Memento{" + "timeOfSave=" + timeOfSave + ", structure=" + structure + '}';
    }

}
