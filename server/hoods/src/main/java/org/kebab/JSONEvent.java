package org.kebab;

public class JSONEvent {

    String rawData;

    public JSONEvent() {}
    public JSONEvent(String rawData) {
        this.rawData = rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getRawData() {
        return rawData;
    }
}
