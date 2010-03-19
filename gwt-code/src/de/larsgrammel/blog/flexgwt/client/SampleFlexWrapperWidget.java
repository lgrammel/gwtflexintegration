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

import java.util.HashMap;
import java.util.Map;

import pl.rmalinowski.gwt2swf.client.ui.SWFWidget;

import com.google.gwt.event.shared.HandlerRegistration;

public class SampleFlexWrapperWidget extends SWFWidget {

    private static Map<String, SampleFlexWrapperWidget> swfWidgets = new HashMap<String, SampleFlexWrapperWidget>();

    static {
	registerCallbackMethods();
    }

    private static native void _displayText(String swfID, String text) /*-{
        $doc.getElementById(swfID).displayText(text);
    }-*/;

    public static void onSwfApplicationComplete(String swfId) {
	_registerSwfListeners(swfId);
	swfWidgets.get(swfId).fireSWFWidgetReady();
    }

    private static native void _registerSwfListeners(String swfID) /*-{
        var swfWidget = $doc.getElementById(swfID);
        swfWidget.addSendListener("_swf_on_text_sent");
    }-*/;

    private static native void registerCallbackMethods() /*-{
        $wnd._swf_on_text_sent=
        @de.larsgrammel.blog.flexgwt.client.SampleFlexWrapperWidget::_onTextSent(Ljava/lang/String;Ljava/lang/String;)
        $wnd._swf_application_complete=
        @de.larsgrammel.blog.flexgwt.client.SampleFlexWrapperWidget::onSwfApplicationComplete(Ljava/lang/String;);
    }-*/;

    public SampleFlexWrapperWidget(int width, int height) {
	super("flexgwtintegration/Test.swf", width, height);

	// addAttribute("wmode", "transparent");

	addFlashVar("swfid", getSwfId());
    }

    public HandlerRegistration addSWFWidgetReadyHandler(
	    SWFWidgetReadyHandler handler) {

	return addHandler(handler, SWFWidgetReadyEvent.TYPE);
    }

    public HandlerRegistration addTextSentEventHandler(
	    TextSentEventHandler handler) {

	return addHandler(handler, TextSentEvent.TYPE);
    }

    public static void _onTextSent(String swfId, String text) {
	swfWidgets.get(swfId).onTextSent(text);
    }

    private void onTextSent(String text) {
	fireEvent(new TextSentEvent(this, text));
    }

    public void displayText(String text) {
	_displayText(getSwfId(), text);
    }

    private void fireSWFWidgetReady() {
	fireEvent(new SWFWidgetReadyEvent(this));
    }

    @Override
    protected void onLoad() {
	super.onLoad();

	SampleFlexWrapperWidget.swfWidgets.put(getSwfId(), this);
    }

    @Override
    protected void onUnload() {
	SampleFlexWrapperWidget.swfWidgets.remove(getSwfId());

	super.onUnload();
    }

}