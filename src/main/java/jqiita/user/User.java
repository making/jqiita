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

package jqiita.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String description;
    private String facebookId;
    private int followeesCount;
    private int followersCount;
    private String githubLoginName;
    private String id;
    private String linkedinId;
    private String location;
    private String name;
    private String organization;
    private String profileImageUrl;
    private String twitterScreenName;
    private String websiteUrl;
}
