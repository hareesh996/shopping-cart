package com.mindtree.web.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class Response<T> {

    private T body;
    private HttpStatus status;
    private List<Message> messages;

    public static <T> ResponseBuilder<T> builder() {
        return new ResponseBuilder<T>();
    }

    public static class ResponseBuilder<T> {

        private Response<T> response;

        ResponseBuilder() {
            this.response = new Response<T>();
        }

        public ResponseEntity<Response<T>> ok(T body) {
            this.response.status = HttpStatus.OK;
            return this.build(body);
        }

        public ResponseBuilder<T> status(HttpStatus status, String... errorKeys) {
            this.response.status = status;
            this.response.messages = convertToMessages(errorKeys);
            return this;
        }

		private List<Message> convertToMessages(String... errorKeys) {
			return Stream.of(errorKeys).map((errorKey) -> {
            	Message message = new Message();
            	message.setKey(errorKey);
            	message.setType(MessageType.ERROR);
            	return message;
            }).collect(Collectors.toList());
		}

        public ResponseEntity<Response<T>> withError(String... errorKeys) {
            this.response.messages = convertToMessages(errorKeys);
            return this.build(null);
        }

        public ResponseEntity<Response<T>> build(T body) {
            this.response.body = body;
            return ResponseEntity.status(this.response.status).body(this.response);
        }

    }

}
