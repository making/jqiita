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

import com.google.gson.annotations.SerializedName;
import jqiita.tag.Tag;
import jqiita.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class Item implements Serializable {
    private String body;
    private boolean coediting;
    private OffsetDateTime createdAt;
    private String id;
    @SerializedName("private")
    @Accessors(prefix = "_")
    private boolean _private;
    private List<Tag> tags;
    private String title;
    private OffsetDateTime updatedAt;
    private User user;
}
