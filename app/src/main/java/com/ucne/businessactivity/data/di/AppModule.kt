package com.ucne.businessactivity.data.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.businessactivity.data.local.database.BusinessDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

//    @Provides
//    @Singleton
//    fun providesProductoApi(moshi: Moshi): ProductoApi {
//        return Retrofit.Builder()
//            .baseUrl("https://ap2ticket.azurewebsites.net/")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(ProductoApi::class.java)
//    }

//    @Provides
//    @Singleton
//    fun providePromocionApi(moshi: Moshi): PromocionApi {
//        return Retrofit.Builder()
//            .baseUrl("https://ap2ticket.azurewebsites.net/")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(PromocionApi::class.java)
//    }

//    @Provides
//    @Singleton
//    fun provideCarritoApi(moshi: Moshi): CarritoApi {
//        return Retrofit.Builder()
//            .baseUrl("https://ap2ticket.azurewebsites.net/")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(CarritoApi::class.java)
//    }
//    @Provides
//    @Singleton
//    fun providePersonaApi(moshi: Moshi): PersonaApi {
//        return Retrofit.Builder()
//            .baseUrl("https://ap2ticket.azurewebsites.net/")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(PersonaApi::class.java)
//    }

    @Provides
    @Singleton
    fun provideBusinessDb(@ApplicationContext appContext: Context): BusinessDb {
        return Room.databaseBuilder(
            appContext,
            BusinessDb::class.java,
            "BusinessDB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductoDao(database: BusinessDb) = database.productodao()
    @Provides
    fun provideCategoryDao(database: BusinessDb) = database.categorydao()

//    @Provides
//    fun provideFireBase() = FirebaseAuth.getInstance()

//    @Provides
//    fun provideCarritoDao(database: BusinessDb) = database.carritoDao()

//    @Provides
//    fun providePersona(database: GeekMarketDb) = database.personaDao()
//    @Provides
//    fun privedePromocionDao(database: GeekMarketDb) = database.promocionDao()
//
//    @Provides
//    fun provideWishListDao(database: GeekMarketDb) = database.wishListDao()

}