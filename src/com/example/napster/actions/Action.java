package com.example.napster.actions;

import org.apache.http.HttpResponse;

public interface Action {

	HttpResponse makeConnection();
}
