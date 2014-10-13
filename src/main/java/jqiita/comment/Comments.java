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

package jqiita.comment;

import jqiita.user.User;
import retrofit.http.*;

import java.util.List;

public interface Comments {
    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/comments/:id">API SPEC</a>
     */
    @GET("/comments/{id}")
    Comment get(@Path("id") String id);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#delete-/api/v2/comments/:id">API SPEC</a>
     */
    @DELETE("/comments/{id}")
    Void delete(@Path("id") String id);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#patch-/api/v2/comments/:id">API SPEC</a>
     */
    @POST("/comments/{id}")
    @Headers("X-Http-Method-Override: PATCH")
    Comment update(@Path("id") String id, @Body CommentRequest request);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/items/:item_id/comments">API SPEC</a>
     */
    @GET("/items/{item_id}/comments")
    List<User> listByItemId(@Path("item_id") String itemId);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/items/:item_id/comments">API SPEC</a>
     */
    @GET("/items/{item_id}/comments")
    List<User> listByItemId(@Path("item_id") String itemId, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#post-/api/v2/items/:item_id/comments">API SPEC</a>
     */
    @POST("/items/{item_id}/comments")
    Comment create(@Path("item_id") String itemId, @Body CommentRequest request);

}
