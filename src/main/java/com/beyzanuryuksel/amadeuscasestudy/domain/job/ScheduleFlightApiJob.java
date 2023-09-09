package com.beyzanuryuksel.amadeuscasestudy.domain.job;
import com.beyzanuryuksel.amadeuscasestudy.model.Amadeus.AmadeusFlightOffersResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.Amadeus.TokenResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduleFlightApiJob {

    private final RestTemplate restTemplate;
    private final String AMADEUS_TOKEN_API_URL = "https://test.api.amadeus.com/v1/security/oauth2/token";
    private final String AMADEUS_API_URL = "https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate=2023-10-01&adults=1&max=2";
    private final String AMADEUS_CLIENT_ID = "***";
    private final String AMADEUS_CLIENT_CREDENTIAL = "***";


    @Scheduled(cron =  "0 30 10 * * *") // run at every 10 30 am
    public void fetchFlightAPI() {
        Optional<String> token = getAccessToken();
        Objects.requireNonNull(token).ifPresent(this::sendFlightApiRequest);
    }

    private void sendFlightApiRequest(String token) {
        try {
            HttpHeaders header = createGetHttpHeaders(token);

            ResponseEntity<AmadeusFlightOffersResponse> response = restTemplate.exchange(AMADEUS_API_URL, HttpMethod.GET,
                    new HttpEntity<>(null, header), AmadeusFlightOffersResponse.class);

            saveResultsToDb(response.getBody());
        }
        catch (Exception exception) {
            log.error("** Exception: "+ exception.getMessage());
        }
    }

    private void saveResultsToDb(AmadeusFlightOffersResponse response) {
        // save results to db
    }

    private Optional<String> getAccessToken() {
        try {
            HttpHeaders header = createHttpHeaders();

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "client_credentials");
            body.add("client_id", AMADEUS_CLIENT_ID);
            body.add("client_secret", AMADEUS_CLIENT_CREDENTIAL);
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<TokenResponseModel> response = restTemplate.exchange(AMADEUS_TOKEN_API_URL, HttpMethod.POST,
                    new HttpEntity<>(body, header), TokenResponseModel.class);
            return Optional.ofNullable(Objects.requireNonNull(response.getBody()).access_token);
        }
        catch (Exception exception) {
            log.error("** Exception: "+ exception.getMessage());
        }
        return null;
    }

    private HttpHeaders createHttpHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private HttpHeaders createGetHttpHeaders(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}
