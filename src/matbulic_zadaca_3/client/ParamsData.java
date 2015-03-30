/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matbulic_zadaca_3.client;

/**
 *
 * @author Bule
 */
public class ParamsData {

    private String pathToRoot;
    private int interval;
    private String logFilePath;

    public ParamsData(String pathToRoot, int interval, String logFilePath) {
        this.pathToRoot = pathToRoot;
        this.interval = interval;
        this.logFilePath = logFilePath;
    }

    public String getPathToRoot() {
        return pathToRoot;
    }

    public void setPathToRoot(String pathToRoot) {
        this.pathToRoot = pathToRoot;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public String toString() {
        return "ParamsData{" + "pathToRoot=" + pathToRoot + ", interval=" + interval + ", logFilePath=" + logFilePath + '}';
    }

}
