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

public interface Stocks {
    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/users/:user_id/stocks">API SPEC</a>
     */
    @GET("/users/{user_id}/stocks")
    List<Item> listByUserId(@Path("user_id") String userId);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#get-/api/v2/users/:user_id/stocks">API SPEC</a>
     */
    @GET("/users/{user_id}/stocks")
    List<Item> listByUserId(@Path("user_id") String userId, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#put-/api/v2/items/:item_id/stock">API SPEC</a>
     */
    @PUT("/items/{item_id}/stock")
    Void put(@Path("item_id") String itemId);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#delete-/api/v2/items/:item_id/stock">API SPEC</a>
     */
    @DELETE("/items/{item_id}/stock")
    Void delete(@Path("item_id") String itemId);
}
