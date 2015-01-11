/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.touchuidialog.widget.checkbox;

import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class CheckboxWidgetMaker extends AbstractTouchUIWidgetMaker {

    public CheckboxWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make() throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException {

        CheckboxWidgetParameters widgetParameters = new CheckboxWidgetParameters();

        widgetParameters.setFieldName(getFieldNameForField());
        widgetParameters.setName(getNameForField());
        widgetParameters.setFieldLabel(getFieldLabelForField());
        widgetParameters.setFieldDescription(getFieldDescriptionForField());
        widgetParameters.setRequired(getRequiredForField());
        widgetParameters.setDefaultValue(getDefaultValueForField());
        widgetParameters.setValue(getValueForField());
        widgetParameters.setDisabled(getDisabledForField());
        widgetParameters.setCssClass(getCssClassForField());

        widgetParameters.setResourceType(CheckboxWidget.RESOURCE_TYPE);

        CheckBox checkboxAnnotation = getAnnotation(CheckBox.class);

        widgetParameters.setText(getTextForField(checkboxAnnotation));
        widgetParameters.setTitle(getTitleForField(checkboxAnnotation));
        widgetParameters.setChecked(getCheckedForField(checkboxAnnotation));

        return new CheckboxWidget(widgetParameters);
    }

    public String getTextForField(CheckBox annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.text())) {
            return annotation.text();
        }

        return null;
    }

    public String getTitleForField(CheckBox annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.title())) {
            return annotation.title();
        }

        return null;
    }

    public boolean getCheckedForField(CheckBox annotation) {
        if (annotation != null) {
            return annotation.checked();
        }

        return false;
    }

}
