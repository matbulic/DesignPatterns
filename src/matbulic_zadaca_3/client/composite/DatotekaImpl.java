/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.client.composite;

import matbulic_zadaca_3.mvc.model.Model;
import static matbulic_zadaca_3.mvc.model.Model.paths;
import matbulic_zadaca_3.mvc.model.TransferObject;

/**
 *
 * @author Bule
 */
public class DatotekaImpl implements IDatoteka {

    private String name;
    private float size;
    private long timeOfLastModifing;
    private IDatoteka parrent;

    public DatotekaImpl(String name, float size, long timeOfLastModifing, IDatoteka parrent) {
        this.name = name;
        this.size = size;
        this.timeOfLastModifing = timeOfLastModifing;
        this.parrent = parrent;
    }

    @Override
    public void addComponent(IDatoteka c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeComponent(IDatoteka c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDatoteka getChild(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void walkStructure(String string) {
        if (Model.paths != null) {
            paths.add(getFullPath(""));
        }
    }

    @Override
    public void printStructure(String string) {

        System.out.println(string + name);

    }

    @Override
    public void find(String string) {
        if (string.equals(getFullPath(""))) {
            Model.transferObject = new TransferObject(getSize(), getFullPath(""), timeOfLastModifing);
        }
    }

    @Override
    public float getSize() {
        return size;
    }

    @Override
    public long getTimeOfLastModifing() {
        return timeOfLastModifing;
    }

    @Override
    public String getFullPath(String s) {
        s = this.name + "\\" + s;
        if (parrent != null) {
            s = parrent.getFullPath(s);
        }

        return s;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setTimeOfLastModifing(long timeOfLastModifing) {
        this.timeOfLastModifing = timeOfLastModifing;
    }

    @Override
    public String getName() {
        return name;
    }

}
