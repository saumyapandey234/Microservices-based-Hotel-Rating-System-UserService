package com.UserService.Config.Interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

  private OAuth2AuthorizedClientManager manager;

  public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
    this.manager = manager;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {

    var authRequest = OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client")
        .principal("internal").build();

    var authorizedClient = manager.authorize(authRequest);

    if (authorizedClient != null && authorizedClient.getAccessToken() != null) {
      String token = authorizedClient.getAccessToken().getTokenValue();
      request.getHeaders().add("Authorization", "Bearer " + token);
    }

    return execution.execute(request, body);
  }
}
