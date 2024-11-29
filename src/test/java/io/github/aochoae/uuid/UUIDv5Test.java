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

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <pre>
 * >>> import uuid
 * >>> uuid.uuid5(uuid.NAMESPACE_DNS, 'luisalberto.dev')
 * UUID('ae2a9d75-ed89-5e16-9f7c-8fd3399438cd')
 * </pre>
 *
 * @author Luis A. Ochoa
 */
class UUIDv5Test {

    @Test
    void testFromString() {

        String name = "luisalberto.dev";

        UUID actualDNS = UUIDv5.fromString(Namespace.DNS, name);
        UUID actualURL = UUIDv5.fromString(Namespace.URL, name);
        UUID actualOID = UUIDv5.fromString(Namespace.OID, name);
        UUID actualX500 = UUIDv5.fromString(Namespace.X500, name);

        assertAll(
            () -> assertEquals("ae2a9d75-ed89-5e16-9f7c-8fd3399438cd", actualDNS.toString()),
            () -> assertEquals("3d1d8773-cc9c-58c3-a94c-e3aaec1008ef", actualURL.toString()),
            () -> assertEquals("0038394a-29d2-5c19-8dc8-e8ef54b2060e", actualOID.toString()),
            () -> assertEquals("47e469c7-d362-5830-9b95-6f0d40c7f1c7", actualX500.toString())
        );
    }

    @Test
    void testFromStringUUID() {

        String name = "luisalberto.dev";

        UUID namespace = UUID.fromString("25a2cc82-e9c0-40a1-808f-6aa35d16fb2c");

        assertEquals("51eff5a8-d771-53eb-96ce-a4244e52be36", UUIDv5.fromString(namespace, name).toString());
    }

    @Test
    void testNameUUIDFromBytesNamespace() {

        byte[] name = "luisalberto.dev".getBytes(StandardCharsets.UTF_8);

        UUID actualDNS = UUIDv5.nameUUIDFromBytes(Namespace.DNS, name);
        UUID actualURL = UUIDv5.nameUUIDFromBytes(Namespace.URL, name);
        UUID actualOID = UUIDv5.nameUUIDFromBytes(Namespace.OID, name);
        UUID actualX500 = UUIDv5.nameUUIDFromBytes(Namespace.X500, name);

        assertAll(
            () -> assertEquals("ae2a9d75-ed89-5e16-9f7c-8fd3399438cd", actualDNS.toString()),
            () -> assertEquals("3d1d8773-cc9c-58c3-a94c-e3aaec1008ef", actualURL.toString()),
            () -> assertEquals("0038394a-29d2-5c19-8dc8-e8ef54b2060e", actualOID.toString()),
            () -> assertEquals("47e469c7-d362-5830-9b95-6f0d40c7f1c7", actualX500.toString())
        );
    }

    @Test
    void newInstance() throws NoSuchMethodException {

        Constructor<UUIDv5> constructor = UUIDv5.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
}
