/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.client.composite;

import java.util.LinkedList;
import java.util.List;
import matbulic_zadaca_3.mvc.model.Model;
import static matbulic_zadaca_3.mvc.model.Model.paths;
import matbulic_zadaca_3.mvc.model.TransferObject;

/**
 *
 * @author Bule
 */
public class DirektorijImpl implements IDatoteka {

    private String name;
    private float size;
    private long timeOfLastModifing;
    private IDatoteka parrent;
    private int numberOfFiles = 0;
    private int numberOfDirectories = 0;

    private List<IDatoteka> childes = new LinkedList<>();

    public DirektorijImpl(String name, long timeOfLastModifing, IDatoteka parrent) {
        this.name = name;
        this.timeOfLastModifing = timeOfLastModifing;
        this.parrent = parrent;
    }

    @Override
    public void addComponent(IDatoteka c) {

        childes.add(c);
    }

    @Override
    public void removeComponent(IDatoteka c) {
        childes.remove(c);
    }

    @Override
    public IDatoteka getChild(int index) {
        return childes.get(index);
    }

    @Override
    public void walkStructure(String string) {
        if (Model.paths != null) {
            paths.add(getFullPath(""));
        }
        for (IDatoteka iDatoteka : childes) {
            iDatoteka.walkStructure(string + "  ");
        }
    }

    @Override
    public void printStructure(String string) {

        System.out.println(string + name);

        for (IDatoteka iDatoteka : childes) {
            iDatoteka.printStructure(string + "  ");
        }
    }

    @Override
    public void find(String string) {

        if (string.equals(getFullPath(""))) {
            Model.transferObject = new TransferObject(numberOfFiles, getFullPath(""), timeOfLastModifing);
        }
        for (IDatoteka iDatoteka : childes) {
            iDatoteka.find(string);
        }
    }

    @Override
    public float getSize() {
        return childes.size();
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

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public int getNumberOfDirectories() {
        return numberOfDirectories;
    }

    public void setNumberOfDirectories(int numberOfDirectories) {
        this.numberOfDirectories = numberOfDirectories;
    }

    public void incrementNumberOfFiles() {
        numberOfFiles++;
    }

    public void incrementNumberOfDirs() {
        numberOfDirectories++;
    }

    @Override
    public String getName() {
        return name;
    }

}
