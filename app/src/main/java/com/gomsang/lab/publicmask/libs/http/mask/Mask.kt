package com.gomsang.lab.publicmask.libs.http.mask

import com.gomsang.lab.publicmask.libs.constants.WebAddresses
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class Mask {
    val api: MaskAPI

    constructor() {

        class StringAdapter : TypeAdapter<String?>() {
            @Throws(IOException::class)
            override fun read(reader: JsonReader): String {
                if (reader.peek() === JsonToken.NULL) {
                    reader.nextNull()
                    return ""
                }
                return reader.nextString()
            }

            @Throws(IOException::class)
            override fun write(writer: JsonWriter, value: String?) {
                if (value == null) {
                    writer.nullValue()
                    return
                }
                writer.value(value)
            }
        }


        class NullStringToEmptyAdapterFactory<T> : TypeAdapterFactory {
            override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
                val rawType = type.rawType as Class<T>
                return if (rawType != String::class.java) {
                    null
                } else StringAdapter() as TypeAdapter<T>
            }
        }

        val gson =
            GsonBuilder().registerTypeAdapterFactory(NullStringToEmptyAdapterFactory<Any>()).create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(WebAddresses.URL_MASK_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        api = retrofit.create<MaskAPI>(MaskAPI::class.java)
    }
}