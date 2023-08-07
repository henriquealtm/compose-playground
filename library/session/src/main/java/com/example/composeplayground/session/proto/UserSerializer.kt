package com.example.composeplayground.session.proto

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.composeplayground.proto.StoredUser
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

/**
 * User serializer serializes the [StoredUser] object when saving it into the application cache and
 * deserializes the cache information into [StoredUser]
 *
 * @constructor Create empty User serializer
 */
object UserSerializer : Serializer<StoredUser> {
    override val defaultValue: StoredUser = StoredUser.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): StoredUser {
        try {
            return StoredUser.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: StoredUser,
        output: OutputStream,
    ) = t.writeTo(output)
}