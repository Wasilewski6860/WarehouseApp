package com.example.newwarehouseapp.di

import com.example.newwarehouseapp.data.db.WarehouseDatabase
import com.example.newwarehouseapp.data.db.WarehouseStorage
import com.example.newwarehouseapp.data.db.impl.WarehouseStorageImpl
import com.example.newwarehouseapp.data.repository.WarehouseRepositoryImpl
import com.example.newwarehouseapp.domain.repositories.WarehouseRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<WarehouseStorage>{ WarehouseStorageImpl(warehouseDatabase = get() )}
    single<WarehouseRepository>{ WarehouseRepositoryImpl(warehouseStorage =  get()) }
    single<WarehouseDatabase> { WarehouseDatabase.getInstance(context = get()) }
//    single<CatsStorage> { CatStorageImpl(catsDatabase = get(), catApi = get()) }
//
//
//    single<CatsRepository> { CatRepositoryImpl(catsStorage = get()) }
//
//    single<CatsApi> {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL).client(
//                OkHttpClient.Builder()
//                .build())
//            .addConverterFactory(GsonConverterFactory.create()).build().create(CatsApi::class.java)
//    }
//
//    single<CatsDatabase> { CatsDatabase.getDataBase(context = get()) }
}