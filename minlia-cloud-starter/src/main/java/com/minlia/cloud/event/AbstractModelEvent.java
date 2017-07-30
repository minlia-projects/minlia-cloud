/*
 * Copyright © 2016 Minlia (http://oss.minlia.com/license/framework/2016)
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
package com.minlia.cloud.event;

public class AbstractModelEvent<T> implements ModelEvent {

    /**
     * <b>Please notice</b> that using domain objects in the events has notable drawback and is not the best idea in many situations.
     * Pseudodomain objects in the code examples were used to not introduce unnecessary complexity.
     */
    private final T source;

    public AbstractModelEvent(T t) {
        this.source = t;
    }

    public T getSource() {
        return source;
    }
}
