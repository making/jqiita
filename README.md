# JQiita

[Qiita API](http://qiita.com/api/v2/docs) Java Client

## How to use

``` java
// list items
Qiita.client()
        .items()
        .list()
        .forEach(System.out::println);

// output verbose Log
Qiita.given()
        .log().all()
        .client()
        .items()
        .list()
        .forEach(System.out::println);

// list items by specific user
Qiita.client()
        .items()
        .listByUserId("making@github")
        .stream()
        .map(Item::getTitle)
        .forEach(System.out::println);

// list items by specific tag
Qiita.client()
        .items()
        .listByTagId("Java")
        .stream()
        .map(Item::getTitle)
        .forEach(System.out::println);


// with accessToken
Qiita.given()
        .accessToken("1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd")
        .client()
        .items()
        .list()
        .forEach(System.out::println);

// create item
Item item = Qiita.given()
        .accessToken("1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd")
        .client()
        .items()
        .create(new ItemRequest(
                "new item from JQiita",
                "* hello world!",
                Arrays.asList(new TagRequest("hoge"))));
System.out.println(item);

// update item
Qiita.client()
        .items()
        .update(item.getId(),
                new ItemRequest("Title has changed!", "* foo bar!", Arrays.asList(new TagRequest("hoge"))));

// for Qiita:Team
Qiita.given()
        .accessToken("1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd")
        .host("https://xxxx.qiita.com")
        .client()
        .items()
        .list()
        .forEach(System.out::println);
```

**Note that `QiitaClient` is cached for each access token**. `Qiita.given().someConfigure()` is ignored once the client is created unless `QiitaClient#clearCache()` is called.

## How to set access token

Priority is "Programatic > System Property > Environment Variable".

### Programatic

``` java
QiitaClient client = Qiita.given()
        .accessToken("1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd")
        .client();
// ...
```

### System Property

``` bash
-Djqiita.accessToken=1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd
```

### Environment Variable

``` bash
export JQIITA_ACCESS_TOKEN=1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd
```


## Requirements

* Java SE 8+
* [Retrofit](http://square.github.io/retrofit/)
* [Gson](https://code.google.com/p/google-gson/)

## License

Licensed under the Apache License, Version 2.0.