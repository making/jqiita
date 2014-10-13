# JQiita

[Qiita API](http://qiita.com/api/v2/docs) Java Client

## How to use

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

    // with accessToken
    Qiita.given()
            .accessToken("1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd")
            .client()
            .items()
            .list()
            .forEach(System.out::println);

    // for Qiita:Team
    Qiita.given()
            .accessToken("1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcd")
            .host("https://xxxx.qiita.com")
            .client()
            .items()
            .list()
            .forEach(System.out::println);