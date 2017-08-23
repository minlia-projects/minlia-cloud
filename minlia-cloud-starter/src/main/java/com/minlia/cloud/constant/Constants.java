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
package com.minlia.cloud.constant;

public class Constants {

  public enum LanguageTypes {
    ExceptionsApiCode,
    Message,
    ValidationMessages
  }
  public static final String EXCEPTION_PREFIX="Exceptions";
  public static final String EXCEPTIONS_APICODE_PREFIX="ExceptionsApiCode";
  public static final String X_LANG="X-LANG";

    public Constants() {
        throw new AssertionError();
    }

}
