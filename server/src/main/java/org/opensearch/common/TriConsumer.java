/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

package org.opensearch.common;

import java.util.Objects;

/**
 * Represents an operation that accepts three arguments and returns no result.
 *
 * @param <S> the type of the first argument
 * @param <T> the type of the second argument
 * @param <U> the type of the third argument
 *
 * @opensearch.internal
 */
@FunctionalInterface
public interface TriConsumer<S, T, U> {
    /**
     * Applies this function to the given arguments.
     *
     * @param s the first function argument
     * @param t the second function argument
     * @param u the third function argument
     */
    void accept(S s, T t, U u);

    default TriConsumer<S, T, U> andThen(TriConsumer<? super S, ? super T, ? super U> after) {
        Objects.requireNonNull(after);

        return (l, r, s) -> {
            accept(l, r, s);
            after.accept(l, r, s);
        };
    }
}
