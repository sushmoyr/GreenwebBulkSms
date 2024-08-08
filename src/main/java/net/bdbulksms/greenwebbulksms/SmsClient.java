package net.bdbulksms.greenwebbulksms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class SmsClient {
    private final String token;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public SmsClient(String token) {
        this.token = token;
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public SmsClient(String token, ObjectMapper mapper) {
        this.token = token;
        this.client = new OkHttpClient();
        this.objectMapper = mapper;
    }

    public Result<SmsResponse> sendSms(SmsRequest smsRequest) {
        RequestBody requestBody = new FormBody.Builder()
                .add("token", token)
                .add("to", smsRequest.to())
                .add("message", smsRequest.message())
                .build();

        Request postRequest = new Request.Builder()
                .url("https://api.bdbulksms.net/api.php?json")
                .post(requestBody)
                .build();
        // send sms
        try (Response response = client.newCall(postRequest).execute()) {
            if (!response.isSuccessful()) {
                return Result.failure("Failed to send sms. Unexpected response code: " + response.code());
            }

            if (response.body() == null) {
                return Result.failure("Failed to send sms. Response body is empty.");
            }

            SmsResponse smsResponse = parseResponse(response.body().string());
            return Result.success(smsResponse);

        } catch (IOException e) {
            return Result.failure(e.getMessage());
        }
    }

    private SmsResponse parseResponse(String response) throws IOException {
        TypeReference<List<SmsResponse>> typeReference = new TypeReference<>() {
        };

        return objectMapper.readValue(response, typeReference).getFirst();
    }
}
