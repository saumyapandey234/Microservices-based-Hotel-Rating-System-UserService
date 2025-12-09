package com.UserService.Config.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration

public class FeignClientInterceptor implements feign.RequestInterceptor {

  private OAuth2AuthorizedClientManager manager;
  String token = manager
      .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build())
      .getAccessToken().getTokenValue();

  @Override
  public void apply(feign.RequestTemplate requestTemplate) {
    // Implementation for adding headers or other configurations to Feign requests
    requestTemplate.header("Authorization", "HeaderValue" + token);
  }

}
