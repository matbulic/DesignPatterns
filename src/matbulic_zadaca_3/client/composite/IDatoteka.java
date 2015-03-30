/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.client.composite;

/**
 *
 * @author Bule
 */
public interface IDatoteka {

    public void addComponent(IDatoteka c);

    public void removeComponent(IDatoteka c);

    public IDatoteka getChild(int index);

    public void printStructure(String string);

    public void walkStructure(String string);

    public float getSize();

    public String getName();

    public long getTimeOfLastModifing();

    public String getFullPath(String s);

    public void find(String string);

}
