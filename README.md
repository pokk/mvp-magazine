# Expense

Recording your daily outcome.


# Description

This application is based on an architecture called __*MVP*__
architecture. Actually we don't only practice developing an application
simply, but also create a base MVP architecture for other projects, too.

This is the first time for us to mix two languages in this project, one
is __Kotlin__, the other one is __Java__, we could know which parts that
Kotlin is stronger and more convenient than Java.

### MVP architecture

We referenced the
[Google clean architecture](https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean/)
to this application. There are three parts of clean architecture as
below.

1. __Presentation Layer__
2. __Domain Layer__
3. __Data Layer__.

### programming language

__*Kotlin*__ in __Presentation Layer__. <br> __*Java*__ in __Domain
Layer__ and __Data Layer__.


# Third-party library

We're using a lot of Rx family libraries.

1. [RxKotlin](https://github.com/ReactiveX/RxKotlin)
2. [RxBinding](https://github.com/JakeWharton/RxBinding)
3. [RxBus](https://github.com/AndroidKnife/RxBus)
4. [RxLifecycle](https://github.com/trello/RxLifecycle)
5. [KotterKnife](https://github.com/JakeWharton/kotterknife)
6. [Retrofit2](https://github.com/square/retrofit)
7. [Glide](https://github.com/bumptech/glide)
8. [Dagger 2](https://github.com/google/dagger)


### TODO

### MVP architecture

Our architecture may not enough convenient for start-up project. If you
have any good idea, please tell us.

- [ ] Decouple between UseCases.
- [x] Decouple the injection of library component.

# Feature work

### expense funtions

- [ ] Location
- [ ] OCR
- [ ] Backup
- [ ] Voice


# License

```
   Copyright 2016 Jieyi Wu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
