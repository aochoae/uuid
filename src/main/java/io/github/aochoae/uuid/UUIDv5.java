/*
 * Copyright 2024 Luis A. Ochoa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.aochoae.uuid;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * A class that creates an immutable Universally Unique Identifier (UUID) URN Namespace.
 * <p>
 * UUIDv5 values are created by computing an SHA-1 hash over a given Namespace ID value concatenated with
 * the desired name value.
 *
 * @author Luis A. Ochoa
 * @since 1.0.0
 */
public final class UUIDv5 {

    /**
     * Constructor
     */
    private UUIDv5() {
        throw new UnsupportedOperationException("Creating an instance of the class 'UUIDv5' is not allowed.");
    }

    /**
     * Static factory to retrieve a type 5 (name based) UUID based on namespace and specified value.
     *
     * @param namespace Namespace ID Value
     * @param name      A string that specifies a UUID
     * @return A UUIDv5 generated with the specified value
     */
    public static UUID fromString(Namespace namespace, String name) {
        return nameUUIDFromBytes(namespace.getUUID(), name.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Static factory to retrieve a type 5 (name based) UUID based on namespace and specified value.
     *
     * @param namespace Namespace ID Value
     * @param name      A string that specifies a UUID
     * @return A UUIDv5 generated with the specified value
     */
    public static UUID fromString(UUID namespace, String name) {
        return nameUUIDFromBytes(namespace, name.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Static factory to retrieve a type 5 (name based) UUID based on namespace and specified byte array.
     *
     * @param namespace Namespace ID Value
     * @param name      A byte array to be used to construct a UUID
     * @return A UUIDv5 generated with the specified array
     */
    public static UUID nameUUIDFromBytes(Namespace namespace, byte[] name) {
        return nameUUIDFromBytes(namespace.getUUID(), name);
    }

    /**
     * Static factory to retrieve a type 5 (name based) UUID based on custom namespace and specified byte array.
     *
     * @param namespace Namespace ID Value
     * @param name      A byte array to be used to construct a UUID
     * @return A UUIDv5 generated with the specified array
     */
    public static UUID nameUUIDFromBytes(UUID namespace, byte[] name) {

        try {

            ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);

            byteBuffer.putLong(namespace.getMostSignificantBits());
            byteBuffer.putLong(namespace.getLeastSignificantBits());

            // Creates a hash value using the SHA-1 algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

            messageDigest.update(byteBuffer.array());
            messageDigest.update(name);

            byte[] hashValue = messageDigest.digest();

            // UUID v5
            hashValue[6] &= 0x0F;        // Clear version
            hashValue[6] |= 0x50;        // Set to version 5
            hashValue[8] &= 0x3F;        // Clear variant
            hashValue[8] |= (byte) 0x80; // Set to IETF variant

            // Creates a new UUID
            ByteBuffer hashValueBuffer = ByteBuffer.wrap(hashValue);

            return new UUID(hashValueBuffer.getLong(), hashValueBuffer.getLong());

        } catch (Exception e) {
            throw new UUIDException("Failed to create a UUIDv5.", e);
        }
    }
}
