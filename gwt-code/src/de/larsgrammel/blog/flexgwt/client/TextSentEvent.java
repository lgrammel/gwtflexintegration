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

import com.google.gwt.event.shared.GwtEvent;

public class TextSentEvent extends GwtEvent<TextSentEventHandler> {

    public static final Type<TextSentEventHandler> TYPE = new Type<TextSentEventHandler>();

    private SampleFlexWrapperWidget swfWidget;

    private String newText;

    public TextSentEvent(SampleFlexWrapperWidget swfWidget, String newText) {
	assert swfWidget != null;
	this.newText = newText;
	this.swfWidget = swfWidget;

    }

    @Override
    protected void dispatch(TextSentEventHandler handler) {
	handler.onTextSent(this);
    }

    public String getNewText() {
	return newText;
    }

    @Override
    public Type<TextSentEventHandler> getAssociatedType() {
	return TYPE;
    }

    public SampleFlexWrapperWidget getSWFWidget() {
	return swfWidget;
    }

}