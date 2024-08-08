package net.bdbulksms.greenwebbulksms;

public record SmsRequest (
        String to,
        String message
) {
}
