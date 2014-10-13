/*
 * Copyright (C) 2014 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jqiita;


import java.util.concurrent.ConcurrentHashMap;

public class Qiita {
    private static final String DEFAULT_HOST = "https://qiita.com";
    private static final String DEFAULT_LOG_LEVEL = "NONE";

    private static final ConcurrentHashMap<String, QiitaClient> clientCache = new ConcurrentHashMap<>();
    private static final ConfiguredAccessToken configuredAccessToken = new ConfiguredAccessToken();


    public static QiitaClient client() {
        String accessToken = configuredAccessToken.getToken();
        return clientCache.computeIfAbsent(accessToken, key -> new QiitaClient(DEFAULT_HOST, accessToken, DEFAULT_LOG_LEVEL));
    }

    public static QiitaClientBuilder given() {
        return new QiitaClientBuilder();
    }

    public static class QiitaClientBuilder {
        private String accessToken = configuredAccessToken.getToken();
        private String host = DEFAULT_HOST;
        private String logLevel = DEFAULT_LOG_LEVEL;

        public QiitaClient client() {
            return clientCache.computeIfAbsent(accessToken, key -> new QiitaClient(host, accessToken, logLevel));
        }

        public QiitaClientBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public QiitaClientBuilder host(String host) {
            this.host = host;
            return this;
        }

        public LogLevelBuilder log() {
            return new LogLevelBuilder(this);
        }
    }


    public static class LogLevelBuilder {
        private final QiitaClientBuilder clientBuilder;

        LogLevelBuilder(QiitaClientBuilder clientBuilder) {
            this.clientBuilder = clientBuilder;
        }

        public QiitaClientBuilder none() {
            clientBuilder.logLevel = "NONE";
            return clientBuilder;
        }

        public QiitaClientBuilder basic() {
            clientBuilder.logLevel = "BASIC";
            return clientBuilder;
        }

        public QiitaClientBuilder all() {
            clientBuilder.logLevel = "FULL";
            return clientBuilder;
        }
    }

}
