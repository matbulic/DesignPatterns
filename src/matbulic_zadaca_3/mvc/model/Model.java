/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.mvc.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import matbulic_zadaca_3.client.ParamsData;
import matbulic_zadaca_3.client.composite.DirektorijImpl;
import matbulic_zadaca_3.client.composite.IDatoteka;
import matbulic_zadaca_3.filesystem.FileSystemHelper;
import matbulic_zadaca_3.memento.Caretaker;
import matbulic_zadaca_3.memento.Memento;
import matbulic_zadaca_3.memento.Originator;
import matbulic_zadaca_3.mvc.IObservable;
import matbulic_zadaca_3.mvc.IObserver;

/**
 *
 * @author Bule
 */
public class Model implements IObservable {

    private IDatoteka activeStructure;
    private final List<IObserver> observers = new ArrayList();
    private final FileSystemHelper fileSystemHelper = new FileSystemHelper();
    private ParamsData paramsData;
    private final Caretaker caretaker = new Caretaker();
    private final Originator originator = new Originator();
    private Comparator comparator;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    public static List<String> paths;
    
    public static List<String> oneMinuteOldFiles = new ArrayList<>();
    public static List<String> twoMinuteOldFiles = new ArrayList<>();
    public static List<String> veryOldFiles = new ArrayList<>();

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    private void notifyObservers(Object o) {
        for (IObserver observer : observers) {
            observer.update(o);
        }

        if (!paramsData.getLogFilePath().equals("")) {
            try {
                fileSystemHelper.write(o.toString(), paramsData.getLogFilePath());
            } catch (IOException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ParamsData getParamsData() {
        return paramsData;
    }

    public void setParamsData(ParamsData paramsData) {
        this.paramsData = paramsData;
    }

    public void makeInitialComposite() {
        activeStructure = fileSystemHelper.makeComposite(paramsData.getPathToRoot());
        saveStructure();

    }

    public void saveStructure() {
        originator.setStructure(activeStructure);
        caretaker.addMemento(originator.saveToMemento());
    }

    public void loadFromMemento(int i) {
        activeStructure = originator.restoreFromMemento(caretaker.getMemento(i));
    }

    public void loadFromMementoAndFlush(int i) {
        Memento loadedState = (Memento) caretaker.getMementoAndFlush(i);
        activeStructure = loadedState.getStructure();
    }

    public void comapreStructures(int newStructure, int oldStructure) {
        IDatoteka newS = ((Memento) caretaker.getMemento(newStructure)).getStructure();
        IDatoteka oldS = ((Memento) caretaker.getMemento(oldStructure)).getStructure();
        
        compare(newS, oldS);
    }

    public void startComparator() {

        try {
            comparator = new Comparator();
            comparator.start();
            isThreadRunning = true;
            notifyObservers("Dretva je pokrenuta.");
        } catch (Exception e) {
            notifyObservers("Dretva je vec pokrenuta.");
        }
    }

    public void stopComparator() {
        
        if (isThreadRunning) {
            isThreadRunning = false;
            notifyObservers("Pricekajte za zaustavljanje dretve.");
        }

    }

    private boolean isThreadRunning = false;

    public String getListOfSavedStructuers() {
        String list = "Spremljene strukture: \n";
        for (Object state : caretaker.getSavedStates()) {
            Memento m = (Memento) state;
            list += (caretaker.getSavedStates().indexOf(m) + 1) + ". Vrijeme: " + formatter.format(m.getTimeOfSave()) + "\n";
        }
        return list;
    }

    public String getStatistics() {
        return "Direktorija: "
                + ((DirektorijImpl) activeStructure).getNumberOfDirectories()
                + " Datoteka: "
                + ((DirektorijImpl) activeStructure).getNumberOfFiles();
    }

    public void getHierarhicalData() {
        activeStructure.printStructure("");

    }

    public static TransferObject transferObject;

    private class Comparator extends Thread {

        @Override
        public void run() {
            isThreadRunning = true;
            while (isThreadRunning) {

                long started = System.currentTimeMillis();

                IDatoteka newStructure = fileSystemHelper.makeComposite(paramsData.getPathToRoot());
                IDatoteka oldStructure = activeStructure;

                compare(newStructure, oldStructure);

                long elapsed = System.currentTimeMillis() - started;
                try {
                    Thread.sleep(paramsData.getInterval() * 1000 - elapsed);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            isThreadRunning = false;
            notifyObservers("Dretva je zaustavljena.");
        }

        

    }
    
    private void compare(IDatoteka newStructure, IDatoteka oldStructure) {

            paths = new ArrayList();
            newStructure.walkStructure("");
            List<String> newPaths = new ArrayList();
            for (String string : paths) {
                newPaths.add(string);
            }

            paths = new ArrayList();
            oldStructure.walkStructure("");
            List<String> oldPaths = new ArrayList();
            for (String string : paths) {
                oldPaths.add(string);
            }

            List<String> pathsForDeepAnalyse = new ArrayList();

            String changes = "";

            for (String stringOld : oldPaths) {

                boolean match = false;

                for (String stringNew : newPaths) {
                    //Ono sto nema u novima je obrisano
                    if (stringOld.equals(stringNew)) {
                        match = true;
                        break;
                    } else {
                        match = false;
                    }
                }

                if (!match) {
                    changes += stringOld + " je obrisan!\n";
                } else {
                    pathsForDeepAnalyse.add(stringOld);
                }
            }

            for (String stringNew : newPaths) {

                boolean match = false;

                for (String stringOld : oldPaths) {
                    //Ono sto nema u novima je dodano
                    if (stringNew.equals(stringOld)) {
                        match = true;
                        break;
                    } else {
                        match = false;
                    }
                }

                if (!match) {
                    changes += stringNew + " je dodan!\n";
                }
            }

            for (String string : pathsForDeepAnalyse) {
                
                newStructure.find(string);
                TransferObject newFile = new TransferObject(transferObject);

                oldStructure.find(string);
                TransferObject oldFile = new TransferObject(transferObject);
                
                String fileChanges = oldFile.compare(newFile, oldFile);
                if (!fileChanges.equals("")) {
                    
                    changes += string + ": \n" + fileChanges;
                }
            }

            if (!changes.equals("")) {
                originator.setStructure(newStructure);
                caretaker.addMemento(originator.saveToMemento());
                notifyObservers("Promjene: " + formatter.format(new Date()) + "\n" + changes);
            } else {
                notifyObservers("Nema promjene, vrijeme: " + formatter.format(new Date()));
            }

        }

}
