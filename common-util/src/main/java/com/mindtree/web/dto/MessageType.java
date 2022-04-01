package com.mindtree.web.dto;

public enum MessageType {
	LABEL("LB"), ERROR("ER"), INFO("IN"), WARN("WA");

	private String colName;

	private MessageType(String colName) {
		this.colName = colName;
	}

	public String getColName() {
		return colName;
	}

	public static MessageType getMessageType(String name) {
		MessageType msgType = null;
		for (MessageType messageType : MessageType.values()) {
			if (name.equalsIgnoreCase(messageType.name())) {
				msgType = messageType;
			}
		}
		return msgType;
	}

	public static MessageType getMessageTypeByColName(String colName) {
		MessageType msgType = null;
		for (MessageType messageType : MessageType.values()) {
			if (colName.equalsIgnoreCase(messageType.getColName())) {
				msgType = messageType;
			}
		}
		return msgType;
	}
}
