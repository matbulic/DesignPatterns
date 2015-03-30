/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.client;

import matbulic_zadaca_3.mvc.controller.Controller;
import matbulic_zadaca_3.mvc.model.Model;
import matbulic_zadaca_3.mvc.view.View;

/**
 *
 * @author Bule
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String pathToRoot = "";
        int interval = 0;
        String logFilePath = "";

        if (args.length == 2 || args.length == 3) {
            pathToRoot = args[0];
            interval = Integer.valueOf(args[1]);
        }
        if (args.length == 3) {
            logFilePath = args[2];
        }
        View v = new View();
        Model m = new Model();
        m.addObserver(v);

        ParamsData params = new ParamsData(pathToRoot, interval, logFilePath);
        Controller c = new Controller(m, v);
        c.initModel(params);
        c.runApp();
    }

}
