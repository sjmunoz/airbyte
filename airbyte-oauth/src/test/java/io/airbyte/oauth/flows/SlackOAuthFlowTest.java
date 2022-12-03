/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.oauth.flows;

import com.fasterxml.jackson.databind.JsonNode;
import io.airbyte.oauth.BaseOAuthFlow;
import io.airbyte.oauth.MoreOAuthParameters;
import java.util.Map;

public class SlackOAuthFlowTest extends BaseOAuthFlowTest {

  @Override
  protected BaseOAuthFlow getOAuthFlow() {
    return new SlackOAuthFlow(getConfigRepository(), getHttpClient(), this::getConstantState);
  }

  @Override
  protected String getExpectedConsentUrl() {
    return "https://slack.com/oauth/authorize?client_id=test_client_id&redirect_uri=https%3A%2F%2Fairbyte.io&state=state&scope=read";
  }

  @Override
  protected Map<String, String> getExpectedOutput() {
    return Map.of(
        "access_token", "access_token_response",
        "client_id", MoreOAuthParameters.SECRET_MASK,
        "client_secret", MoreOAuthParameters.SECRET_MASK);
  }

  @Override
  protected JsonNode getCompleteOAuthOutputSpecification() {
    return getJsonSchema(Map.of("access_token", Map.of("type", "string")));
  }

  @Override
  protected Map<String, String> getExpectedFilteredOutput() {
    return Map.of(
        "access_token", "access_token_response",
        "client_id", MoreOAuthParameters.SECRET_MASK);
  }

}
