/*
 * Copyright (C) 2014 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jqiita.exception;

import lombok.Data;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;


public class QiitaErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError retrofitError) {
        ErrorResponse error = (ErrorResponse) retrofitError.getBodyAs(ErrorResponse.class);
        if (error == null) {
            return new IllegalStateException(retrofitError);
        }
        String message = error.getMessage();
        String type = error.getType();
        try {
            return ErrorType.valueOf(type.toUpperCase()).exception(message, type, retrofitError);
        } catch (IllegalArgumentException ok) {
            return new QiitaException(message, type, retrofitError);
        }
    }

    static enum ErrorType {
        BAD_REQUEST {
            @Override
            QiitaException exception(String message, String type, Throwable cause) {
                return new QiitaBadRequestException(message, type, cause);
            }
        },
        UNAUTHORIZED {
            @Override
            QiitaException exception(String message, String type, Throwable cause) {
                return new QiitaUnauthorizedException(message, type, cause);
            }
        },
        FORBIDDEN {
            @Override
            QiitaException exception(String message, String type, Throwable cause) {
                return new QiitaForbiddenException(message, type, cause);
            }
        },
        NOT_FOUND {
            @Override
            QiitaException exception(String message, String type, Throwable cause) {
                return new QiitaNotFoundException(message, type, cause);
            }
        };

        abstract QiitaException exception(String message, String type, Throwable cause);
    }

    @Data
    static class ErrorResponse {
        private String message;
        private String type;
    }
}
