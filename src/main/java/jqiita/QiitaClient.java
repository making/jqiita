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

import com.google.gson.*;
import jqiita.comment.Comments;
import jqiita.comment.Thank;
import jqiita.exception.QiitaErrorHandler;
import jqiita.item.Items;
import jqiita.item.Lgtm;
import jqiita.item.Stocks;
import jqiita.tag.FollowingTags;
import jqiita.tag.Tags;
import jqiita.template.ExpandedTemplate;
import jqiita.template.Templates;
import jqiita.user.AuthenticatedUser;
import jqiita.user.Followees;
import jqiita.user.Followers;
import jqiita.user.Users;
import lombok.Data;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class QiitaClient {
    public static final String API_VERSION = "v2";
    private static final ConcurrentHashMap<String, Object> objectCache = new ConcurrentHashMap<>();
    private final String host;
    private final String accessToken;
    private final String logLevel;

    String getKey(Class<?> clazz, String accessToken) {
        return clazz + "$" + accessToken;
    }

    @SuppressWarnings("unchecked")
    public <T> T resource(Class<T> clazz) {
        RestAdapter builder = (RestAdapter) objectCache.computeIfAbsent(getKey(RestAdapter.class, accessToken),
                (key) -> {
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
                            .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeConverter())
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create();
                    return new RestAdapter.Builder()
                            .setEndpoint(host + "/api/" + API_VERSION)
                            .setConverter(new GsonConverter(gson))
                            .setLogLevel(RestAdapter.LogLevel.valueOf(logLevel))
                            .setErrorHandler(new QiitaErrorHandler())
                            .setRequestInterceptor((request) -> {
                                if (accessToken != null && !accessToken.isEmpty()) {
                                    request.addHeader("Authorization", "Bearer " + accessToken);
                                }
                            })
                            .build();
                });
        return (T) objectCache.computeIfAbsent(getKey(clazz, accessToken), (key) -> builder.create(clazz));
    }

    public QiitaClient clearCache() {
        objectCache.clear();
        return this;
    }

    public Comments comments() {
        return resource(Comments.class);
    }

    public Thank thank() {
        return resource(Thank.class);
    }

    public Items items() {
        return resource(Items.class);
    }

    public Lgtm lgtm() {
        return resource(Lgtm.class);
    }

    public Stocks stocks() {
        return resource(Stocks.class);
    }

    public FollowingTags followingTags() {
        return resource(FollowingTags.class);
    }

    public Tags tags() {
        return resource(Tags.class);
    }

    public ExpandedTemplate expandedTemplate() {
        return resource(ExpandedTemplate.class);
    }

    public Templates templates() {
        return resource(Templates.class);
    }

    public AuthenticatedUser authenticatedUser() {
        return resource(AuthenticatedUser.class);
    }

    public Followees followees() {
        return resource(Followees.class);
    }

    public Followers followers() {
        return resource(Followers.class);
    }

    public Users users() {
        return resource(Users.class);
    }

    static class OffsetDateTimeConverter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {
        @Override
        public OffsetDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(jsonElement.getAsString(), OffsetDateTime::from);
        }

        @Override
        public JsonElement serialize(OffsetDateTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime));
        }
    }
}
