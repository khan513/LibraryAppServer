package net;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    private RequestType type;
    private String description;

    private Object object;
    private List<String> args;

    public Request() {
    }

    public Request(RequestType type, String description, Object object, List<String> args) {
        this.type = type;
        this.description = description;
        this.object = object;
        this.args = args;
    }

    public RequestType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}