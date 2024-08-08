package net.bdbulksms.greenwebbulksms;

public class GreenwebBulkSmsApplication {

    public static void main(String[] args) {
//        SpringApplication.run(GreenwebBulkSmsApplication.class, args);
        SmsClient client = new SmsClient("35371842521723120972a3c2c9e103f2ff9149c4cf1f5a0d1cd1");

        SmsRequest smsRequest = new SmsRequest("+8801854489406", "Your otp is 1234. Please do not share this with anyone.");

        Result<SmsResponse> response = client.sendSms(smsRequest);
        response.when(
                success -> {
                    System.out.println("SMS sent successfully.");
                    System.out.println("To: " + success.getTo());
                    System.out.println("Message: " + success.getMessage());
                    System.out.println("Status: " + success.getStatus());
                    System.out.println("Status Message: " + success.getStatusMessage());
                },
                failure -> {
                    System.out.println("Failed to send SMS.");
                    System.out.println("Reason: " + failure);
                }
        );
    }

}
