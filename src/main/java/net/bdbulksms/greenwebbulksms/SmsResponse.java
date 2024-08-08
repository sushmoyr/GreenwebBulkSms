package net.bdbulksms.greenwebbulksms;

import com.fasterxml.jackson.annotation.JsonAlias;

public class SmsResponse {
    private String to;
    private String message;
    private String status;
    @JsonAlias("statusmsg")
    private String statusMessage;

    public SmsResponse() {
    }

    public SmsResponse(String to, String message, String status, String statusMessage) {
        this.to = to;
        this.message = message;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
