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

import retrofit.http.DELETE;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface Thank {
    /**
     * @see <a href="http://qiita.com/api/v2/docs#put-/api/v2/comments/:comment_id/thank">API SPEC</a>
     */
    @PUT("/comments/{comment_id}/thank")
    Void put(@Path("comment_id") String commentId);

    /**
     * @see <a href="http://qiita.com/api/v2/docs#delete-/api/v2/comments/:comment_id/thank">API SPEC</a>
     */
    @DELETE("/comments/{comment_id}/thank")
    Void delete(@Path("comment_id") String commentId);
}
