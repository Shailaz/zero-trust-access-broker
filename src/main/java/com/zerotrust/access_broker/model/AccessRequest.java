package com.zerotrust.access_broker.model;

public class AccessRequest {
    private String user;
    private String service;

    public AccessRequest() {
    }

    public AccessRequest(String user, String service) {
        this.user = user;
        this.service = service;
    }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
}
