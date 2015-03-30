/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import matbulic_zadaca_3.client.ParamsData;
import matbulic_zadaca_3.mvc.model.Model;
import matbulic_zadaca_3.mvc.view.View;

/**
 *
 * @author Bule
 */
public class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initModel(ParamsData paramsData) {
        model.setParamsData(paramsData);
    }

    public void runApp() {
        model.makeInitialComposite();
        printMenuAndWaitForOperation();
    }

    public void processUserInput(String input) {
        switch (input) {
            case "1":
                view.print(model.getStatistics());
                printMenuAndWaitForOperation();
                break;
            case "2":
                model.getHierarhicalData();
                printMenuAndWaitForOperation();
                break;
            case "3":
                model.startComparator();
                printMenuAndWaitForOperation();
                break;
            case "4":
                model.stopComparator();
                printMenuAndWaitForOperation();
                break;
            case "5":
                view.print(model.getListOfSavedStructuers());
                printMenuAndWaitForOperation();
                break;
            case "6":
                view.print("Odaberite stanje: ");
                model.loadFromMemento(Integer.valueOf(readUserInput()) - 1);
                printMenuAndWaitForOperation();
                break;
            case "7":
                view.print("Odaberite stanja n zatim m: ");
                model.comapreStructures(
                        (Integer.valueOf(readUserInput()) - 1),
                        (Integer.valueOf(readUserInput()) - 1)
                );

                printMenuAndWaitForOperation();
                break;
            case "8":
                view.print("Odaberite stanje: ");
                model.loadFromMementoAndFlush(Integer.valueOf(readUserInput()) - 1);
                printMenuAndWaitForOperation();
                break;
            case "9":
                view.print("Minutu starih:");
                for (String string : Model.oneMinuteOldFiles) {
                    view.print(string);
                }
                
                view.print("Dvije minute starih:");
                for (String string : Model.twoMinuteOldFiles) {
                    view.print(string);
                }
                
                view.print("Jako starih:");
                for (String string : Model.veryOldFiles) {
                    view.print(string);
                }
                printMenuAndWaitForOperation();
                break;
            case "q":
                break;
            default:
                view.print("Ne podrzana operacija");
                printMenuAndWaitForOperation();
        }
    }

    public void printMenuAndWaitForOperation() {
        view.printMenu();
        processUserInput(readUserInput());
    }

    public String readUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

}
