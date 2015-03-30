/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.chainOfResponsibility;

import java.util.Date;
import matbulic_zadaca_3.mvc.model.Model;

/**
 *
 * @author Bule
 */
public class InMinuteOldImpl extends Handler {

    public InMinuteOldImpl(Handler h) {
        super(h);
    }

    @Override
    public void handleRequest(String name, long time) {
        
        
        if ((new Date().getTime() - time) < (60000 * 1)) {
            Model.oneMinuteOldFiles.add(name);
        } else {
            if (successor != null) {
                successor.handleRequest(name, time);
            }
        }
    }

}
