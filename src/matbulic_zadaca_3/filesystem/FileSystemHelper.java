/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.filesystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import matbulic_zadaca_3.chainOfResponsibility.Handler;
import matbulic_zadaca_3.chainOfResponsibility.InMinuteOldImpl;
import matbulic_zadaca_3.chainOfResponsibility.SinceTwoMinutesImpl;
import matbulic_zadaca_3.chainOfResponsibility.TwoMinOldImpl;
import matbulic_zadaca_3.client.composite.DatotekaImpl;
import matbulic_zadaca_3.client.composite.DirektorijImpl;
import matbulic_zadaca_3.client.composite.IDatoteka;
import matbulic_zadaca_3.mvc.model.Model;

/**
 *
 * @author Bule
 */
public class FileSystemHelper {

    private IDatoteka rootComponent;
    private Handler h = new InMinuteOldImpl(new TwoMinOldImpl(new SinceTwoMinutesImpl(null)));

    public void write(String toWrite, String path) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
            out.println(toWrite);
        } catch (IOException e) {
        }

    }

    public IDatoteka makeComposite(String rootPath) {
        Model.oneMinuteOldFiles.clear();
        Model.twoMinuteOldFiles.clear();
        Model.veryOldFiles.clear();
        recursiveWalk(new File(rootPath), null);
        return rootComponent;
    }

    private void recursiveWalk(File f, IDatoteka iDatoteka) {

        if (!f.isDirectory()) {
            IDatoteka newFile = new DatotekaImpl(
                    f.getName(),
                    f.length(),
                    f.lastModified(),
                    iDatoteka
            );

            iDatoteka.addComponent(newFile);
            h.handleRequest(f.getName(), f.lastModified());
            ((DirektorijImpl) rootComponent).incrementNumberOfFiles();
        } else {

            File[] files = f.listFiles();

            if (iDatoteka == null) {
                iDatoteka = new DirektorijImpl(f.getName(), f.lastModified(), null);
                rootComponent = iDatoteka;
                for (File file : files) {
                    recursiveWalk(file, iDatoteka);
                }
            } else {
                h.handleRequest(f.getName(), f.lastModified());
                ((DirektorijImpl) rootComponent).incrementNumberOfDirs();
                IDatoteka newDirectory = new DirektorijImpl(
                        f.getName(),
                        f.lastModified(),
                        iDatoteka
                );

                iDatoteka.addComponent(newDirectory);
                for (File file : files) {
                    recursiveWalk(file, newDirectory);
                }
            }

        }

    }

}
