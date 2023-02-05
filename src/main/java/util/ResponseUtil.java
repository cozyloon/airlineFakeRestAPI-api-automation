package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class ResponseUtil {
    public static  <T> T mapResponseToModel(Response response, Class<T> modelClass) {
        // Convert the response body to a string
        String body = response.getBody().asString();

        // Create an object mapper to map the response body to the model class
        ObjectMapper objectMapper = new ObjectMapper();

        // Map the response body to an instance of the model class
        try {
            return objectMapper.readValue(body, modelClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}