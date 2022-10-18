package com.nbw.booksearchapp.data.api

// Hilt DI 사용으로 AppModule에서 의존성 주입을 하기 때문에 더이상 사용하지 않음
//object RetrofitInstance {
//    private val okHttpClient: OkHttpClient by lazy {
//        // okhttp의 Interceptor : 서버와 어플리케이션 사이에서 데이터를 인터셉터하는 기능 - Logging을 위해 사용
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(okHttpClient)
//            .baseUrl(BASE_URL)
//            .build()
//    }
//
//    val api: BookSearchApi by lazy {
//        retrofit.create(BookSearchApi::class.java)
//    }
//}