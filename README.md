# Reddit (Sort of)

[![CI](https://github.com/jlmcdonnell/RedditSortOf/actions/workflows/main.yml/badge.svg?branch=master)](https://github.com/jlmcdonnell/RedditSortOf/actions/workflows/main.yml)

A small Android app to demonstrate Android development practices using the Reddit API. As of now, there's no paging support.

## Requirements

- Android Studio [Arctic Fox](https://androidstudio.googleblog.com/2021/02/android-studio-arctic-fox-canary-8.html)

## Tech stack

- [Hilt](http://dagger.dev/hilt) dependency injection framework
- [Jetpack Navigation](https://developer.android.com/guide/navigation)
- [Retrofit](https://square.github.io/retrofit/) for API requests
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for asynchronous programming
- [MockK](https://github.com/mockk/mockk) testing library for mocking object with Kotlin

## Reddit API

- The Reddit API responses are parsed as per their [documentation](https://www.reddit.com/dev/api/)
