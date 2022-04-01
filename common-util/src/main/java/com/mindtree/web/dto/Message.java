package com.mindtree.web.dto;

public class Message {
	/**
	 * type of message like ERROR, WARNING, INFO
	 */
	private MessageType type;
	/**
	 * It tells reason on using the message. Like in the case of Error message, it
	 * tells the reason for the failure.
	 * 
	 */
	private String code;

	/**
	 * The key against which the text message fetched from the body.
	 */
	private String key;

	/**
	 * The text message, description of the message/label.
	 */
	private String text;

	/**
	 * The locale of the text. The locale could be part of the header/meta data of
	 * the response in these cases it necessary to set the locale.
	 */
	private String locale;

	public Message() {
	}

	public Message(String key, String text, MessageType type) {
		this.key = key;
		this.text = text;
		this.type = type;
	}

	public MessageType getType() {
		return type;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

