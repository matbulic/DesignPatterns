/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.chainOfResponsibility;

/**
 *
 * @author Bule
 */
public abstract class Handler {

    protected Handler successor;

    public Handler(Handler h) {
        addSuccessor(h);
    }

    public final void addSuccessor(Handler successor) {
        this.successor = successor;
    }

    abstract public void handleRequest(String name, long time);
}
