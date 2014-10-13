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

package jqiita.item;

import retrofit.http.*;

import java.util.List;

public interface Items {
    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/items">API SPEC</a>
     */
    @GET("/items")
    List<Item> list();

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/items">API SPEC</a>
     */
    @GET("/items")
    List<Item> list(@Query("page") int page, @Query("per_page") int perPage);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/users/:user_id/items">API SPEC</a>
     */
    @GET("/users/{user_id}/items")
    List<Item> listByUserId(@Path("user_id") String userId);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/users/:user_id/items">API SPEC</a>
     */
    @GET("/users/{user_id}/items")
    List<Item> listByUserId(@Path("user_id") String userId, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/tags/:id/items">API SPEC</a>
     */
    @GET("/tags/{id}/items")
    List<Item> listByTagId(@Path("id") String id);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/tags/:id/items">API SPEC</a>
     */
    @GET("/tags/{id}/items")
    List<Item> listByTagId(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/items/:id">API SPEC</a>
     */
    @GET("/items/{id}")
    Item get(@Path("id") String id);

    @POST("/items")
    Item create(@Body ItemRequest request);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#patch-/api/v2/items/:id">API SPEC</a>
     */
    @POST("/items/{id}")
    @Headers("X-Http-Method-Override: PATCH")
    Item update(@Path("id") String id, @Body ItemRequest request);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#delete-/api/v2/items/:id">API SPEC</a>
     */
    @DELETE("/items/{id}")
    Void delete(@Path("id") String id);
}
