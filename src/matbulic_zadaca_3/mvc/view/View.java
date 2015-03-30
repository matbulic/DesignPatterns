/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.mvc.view;

import matbulic_zadaca_3.mvc.IObserver;

/**
 *
 * @author Bule
 */
public class View implements IObserver {

    @Override
    public void update(Object o) {
        print(o.toString());
    }

    public void printMenu() {
        print("=================== IZBORNIK ===================");
        print("1. Ispis statistike");
        print("2. Ispis hijerarhije");
        print("3. Pokreni dretvu");
        print("4. Ugasi dretvu");
        print("5. Ispis stanja");
        print("6. Postavljanje stanja");
        print("7. Usporedivanje stanja");
        print("8. Postavljanje novog stanja");
        print("9. Dodana funkcionalnost");
        print("Q. Izlaz");
        print("=================== IZBORNIK ===================");
        print("Odabir: ");
    }

    public void print(String s) {
        System.out.println(s);
    }

}
