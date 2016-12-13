package com.example.demo;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import server.mycomponent.MyComponent;
import com.vaadin.annotations.Theme;

@Theme("Demo")
public class DemoUI extends UI{
	
	@Override
	protected void init(VaadinRequest request){
		MyComponent addon = new MyComponent();
		setContent(addon);
	}
}
