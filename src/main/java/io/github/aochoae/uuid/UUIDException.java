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

/**
 * @author Luis A. Ochoa
 * @since 1.0.0
 */
public class UUIDException extends RuntimeException {

    /**
     * Constructs an UUIDException with the specified detail message and cause.
     *
     * @param message The detail message
     * @param throwable The cause (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public UUIDException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
