package be.kunlabora.demo.smooch.client;

import be.kunlabora.demo.smooch.rest.MessageTO;
import io.smooch.client.ApiClient;
import io.smooch.client.ApiException;
import io.smooch.client.Configuration;
import io.smooch.client.api.ConversationApi;
import io.smooch.client.auth.ApiKeyAuth;
import io.smooch.client.model.MessagePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Named
public class SmoochClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmoochClient.class);

    private static final String APP_ID = "5be1b200f0c6dc00274dfa61";
    private static final String FACEBOOK_USER_TOKEN = "EAADZA6ZBEURL0BAEvxceJFgKxZAA8KMezMK3FodHfQBc8ZCLvOhggntSeWfxs45kTLX5NW42xeHZAoGP3ZA14YpG4DjP5Lv6W4K4dVEPxI6ZCi9ZBY5m1oRcwCXmSKdpJKEwkCC6M8jBIMqTFqAX8xtVZBp4S9ZBYCe0N2Ti5eaZBnzByEo3yYkFuDjNBBJ2DrPPXgZD";
    private static final String FACEBOOK_PAGE_ACCESS_TOKEN = "EAADZA6ZBEURL0BAHwBTTaPsgrkTgkLn16HBNqBWNt8IoPUFQatPdGUwJ99NtCx7vZAsk30AkdMj6fys9UGOz6qwSyD98D40I8a8L16TRfGWuN2GUjSkejhNBTpomAEwdPKEUwqIy3LUZCRO2P3Qq3sw896ZBkS7YwMVGj4Ue92wE2NTVycVVkxdbPn5wu4YZCZCxehWMGYCkk5Qf1X9blIj5vAHRJIztmMP8U1S27u0BAZDZD";

    private static final String SMOOCH_APP_ACCESS_KEY = "app_5be1b2b2a7c44d0022e7d02d";
    private static final byte[] SMOOCH_APP_KEY_SECRET = "SAU0dOmW85YNiy9XFjboMNUoFAYUl3xLSJOjsllBGOfc4ZS9RT2M0gVvRdLFknnWM4SKlJnC1I_b9xXv0Vrugw".getBytes();
    private static final String SMOOCH_ACCOUNT_ACCESS_KEY = "act_5be1f74c8a474a0021bbf943";
    private static final byte[] SMOOCH_ACCOUNT_KEY_SECRET = "hPDpvcHSVRBeuYIE8hW3_0n2kJnW1ZkYLb2B-lYkvyo4namnY4-0gNT5TpLwDSIQyW-lIQP3lvdZq2BYH-oJeA".getBytes();

    public static final String USER_ID = "sander.paulus.188";
    public static final String JWT_AUTH_TOKEN = "eyJraWQiOiJhcHBfNWJlMWIyYjJhN2M0NGQwMDIyZTdkMDJkIiwiYWxnIjoiSFMyNTYifQ.eyJzY29wZSI6ImFwcCJ9.WNLDYbMFtxxI3RYUBoFkp9DQSXb33ppqLiAS-WzD7-o";

    private final RestTemplate restTemplate;

    public SmoochClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage(MessageTO messageTO) {
        ApiClient defaultApiClient = Configuration.getDefaultApiClient();
        ApiKeyAuth jwt = (ApiKeyAuth) defaultApiClient.getAuthentication("jwt");
        jwt.setApiKey(JWT_AUTH_TOKEN);
        jwt.setApiKeyPrefix("Bearer");

        ConversationApi conversationApi = new ConversationApi();
        try {
            conversationApi.postMessage(APP_ID, messageTO.getReceiver(), createMessage(messageTO));
        } catch (ApiException e) {
            LOGGER.error("api call threw exception.", e);
            throw new RuntimeException(e);
        }
    }

    private MessagePost createMessage(MessageTO messageTO) {
        return new MessagePost()
                .role("appMaker")
                .type("text")
                .name("Kunlaborealis")
                .text(messageTO.getMessage());
    }


    private HttpHeaders getBasicAuthenticationHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, "bearer " + JWT_AUTH_TOKEN);
        return headers;
    }
}
