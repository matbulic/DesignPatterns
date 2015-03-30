/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.mvc.model;

/**
 *
 * @author Bule
 */
public class TransferObject {

    private float size;
    private String fullName;
    private long lastModified;

    public TransferObject(float size, String fullName, long lastModified) {
        this.size = size;
        this.fullName = fullName;
        this.lastModified = lastModified;
    }

    public TransferObject(TransferObject transferObject) {
        this.size = transferObject.getSize();
        this.fullName = transferObject.getFullName();
        this.lastModified = transferObject.getLastModified();
    }

    public float getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
    
    public String compare(TransferObject newFileData, TransferObject oldFileData) {
        
        String changes = "";
        
        if (newFileData.getSize() != oldFileData.getSize()) {
            changes += "   Promjena u velicini datoteke: " + (newFileData.getSize() - oldFileData.getSize()) + "\n";
        }
        
        if (newFileData.getLastModified() != oldFileData.getLastModified()) {
            changes += "   Promjena u vremenu modificiranja: " + (newFileData.getLastModified() - oldFileData.getLastModified()) + "\n";
        }
        
        return changes;
    }

    @Override
    public String toString() {
        return "TransferObject{" + "size=" + size + ", fullName=" + fullName + ", lastModified=" + lastModified + '}';
    }

}
