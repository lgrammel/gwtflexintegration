/*
 *   Copyright 2010 Lars Grammel
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 */
package de.larsgrammel.blog.flexgwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class FlexGWTIntegration implements EntryPoint {

    public void onModuleLoad() {
	final Button sendButton = new Button("Send to Flex");
	final TextBox textField = new TextBox();
	textField.setText("from GWT");

	final SampleFlexWrapperWidget flexWidget = new SampleFlexWrapperWidget(
		300, 50);

	RootPanel.get().add(textField);
	RootPanel.get().add(sendButton);

	RootPanel.get().add(flexWidget);

	textField.setFocus(true);
	textField.selectAll();

	class MyHandler implements ClickHandler, KeyUpHandler {

	    public void onClick(ClickEvent event) {
		displayTextInFlex();
	    }

	    public void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		    displayTextInFlex();
		}
	    }

	    private void displayTextInFlex() {
		flexWidget.displayText(textField.getText());
	    }
	}

	MyHandler handler = new MyHandler();
	sendButton.addClickHandler(handler);
	textField.addKeyUpHandler(handler);

	flexWidget.addTextSentEventHandler(new TextSentEventHandler() {
	    @Override
	    public void onTextSent(TextSentEvent event) {
		textField.setText(event.getNewText());
	    }
	});
    }
}
