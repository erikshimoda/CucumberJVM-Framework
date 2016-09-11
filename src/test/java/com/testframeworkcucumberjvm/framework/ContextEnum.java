package com.testframeworkcucumberjvm.framework;

public enum ContextEnum {
	NATIVEAPP(1),
    WEBVIEW(2);
	
	private final int context;

	ContextEnum(int contextOption){
		context = contextOption;
	}
	
	public int getContext() {
		return context;

	}
}