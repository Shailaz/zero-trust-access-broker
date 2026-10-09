package com.zerotrust.access_broker.model;

public class AccessResponse {
    private String user;
    private String service;
    private String decision;
    private String reason;

    public AccessResponse() {
    }

    public AccessResponse(String user, String service, String decision, String reason) {
        this.user = user;
        this.service = service;
        this.decision = decision;
        this.reason = reason;
    }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
