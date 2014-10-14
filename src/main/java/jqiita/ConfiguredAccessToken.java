package jqiita;

import lombok.Getter;

class ConfiguredAccessToken {
    private static final String ACCESS_TOKEN_PROPERTY_NAME = "jqiita.accessToken";
    private static final String ACCESS_TOKEN_ENV_NAME = "JQIITA_ACCESS_TOKEN";

    @Getter
    private String token;

    public ConfiguredAccessToken() {
        // PROPERTY > ENV
        String token = "";
        if (System.getProperty(ACCESS_TOKEN_PROPERTY_NAME) != null) {
            token = System.getProperty(ACCESS_TOKEN_PROPERTY_NAME);
        } else if (System.getenv(ACCESS_TOKEN_ENV_NAME) != null) {
            token = System.getenv(ACCESS_TOKEN_ENV_NAME);
        }
        this.token = token;
    }
}
