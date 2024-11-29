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

import java.util.UUID;

/**
 * <a href="https://www.rfc-editor.org/rfc/rfc4122#appendix-C">Some Name Space IDs</a>.
 *
 * @author Luis A. Ochoa
 * @since 1.0.0
 */
public enum Namespace {

    /**
     * Fully-qualified domain name: <code>6ba7b810-9dad-11d1-80b4-00c04fd430c8</code>.
     */
    DNS(UUID.fromString("6ba7b810-9dad-11d1-80b4-00c04fd430c8")),

    /**
     * URL: <code>6ba7b811-9dad-11d1-80b4-00c04fd430c8</code>
     */
    URL(UUID.fromString("6ba7b811-9dad-11d1-80b4-00c04fd430c8")),

    /**
     * ISO OID: <code>6ba7b812-9dad-11d1-80b4-00c04fd430c8</code>
     */
    OID(UUID.fromString("6ba7b812-9dad-11d1-80b4-00c04fd430c8")),

    /**
     * X.500 DN: <code>6ba7b814-9dad-11d1-80b4-00c04fd430c8</code>
     */
    X500(UUID.fromString("6ba7b814-9dad-11d1-80b4-00c04fd430c8"));

    private final UUID uuid;

    Namespace(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Retrieves the UUID of the namespace defined (DNS, URL, OID and X500).
     *
     * @return The UUID of the namespace.
     */
    public UUID getUUID() {
        return uuid;
    }
}
