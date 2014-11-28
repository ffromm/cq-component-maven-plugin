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
package com.citytechinc.cq.component.touchuidialog.widget.maker;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.util.ComponentUtil;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import org.codehaus.plexus.util.StringUtils;

public abstract class AbstractTouchUIWidgetMaker implements TouchUIWidgetMaker {

    protected final TouchUIWidgetMakerParameters parameters;

    public AbstractTouchUIWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        this.parameters = parameters;
    }

    /**
     * <p>
     * Determines and returns the name for the dialog field. The name is the
     * relative path to where any authored input using the resultant widget will
     * be housed in the content repository.
     * </p>
     * <p>
     * When useDotSlashInName is true, the string './' will be prepended to the
     * determined name.
     * </p>
     *
     * @return The name property of the DialogField annotation if one is
     *         provided, otherwise the result of the {@link #getName()} method.
     */
    protected String getNameForField() {
        String overrideName = parameters.getDialogFieldConfig().getName();

        if (StringUtils.isNotEmpty(overrideName)) {
            return overrideName;
        }
        if (parameters.isUseDotSlashInName()) {
            return "./" + getName();
        }
        return getName();
    }

    /**
     *
     * @return The fieldName property of the DialogField annotation if one is
     *         provided, the result of the {@link #getName()} method otherwise.
     */
    protected String getFieldNameForField() {
        String overrideFieldName = parameters.getDialogFieldConfig().getFieldName();

        if (StringUtils.isNotEmpty(overrideFieldName)) {
            return overrideFieldName;
        }

        return getName();
    }

    /**
     *
     * @return The fieldLabel property of the DialogField annotation if one is
     *         provided, null otherwise.
     */
    protected String getFieldLabelForField() {
        String overrideLabel = parameters.getDialogFieldConfig().getFieldLabel();

        if (StringUtils.isNotEmpty(overrideLabel)) {
            return overrideLabel;
        }

        return null;
    }

    /**
     *
     * @return The fieldDescription property of the DialogField annotation if
     *         one is provided, null otherwise.
     */
    protected String getFieldDescriptionForField() {
        String overrideFieldDescription = parameters.getDialogFieldConfig().getFieldDescription();

        if (StringUtils.isNotEmpty(overrideFieldDescription)) {
            return overrideFieldDescription;
        }

        return null;
    }

    protected boolean getRequiredForField() {
        return parameters.getDialogFieldConfig().isRequired();
    }

    protected String getDefaultValueForField() {
        String overrideFieldValue = parameters.getDialogFieldConfig().getDefaultValue();

        if (StringUtils.isNotEmpty(overrideFieldValue)) {
            return overrideFieldValue;
        }

        return null;
    }

    protected String getValueForField() {
        if (StringUtils.isNotBlank(parameters.getDialogFieldConfig().getValue())) {
            return parameters.getDialogFieldConfig().getValue();
        }

        return null;
    }

    protected boolean getDisabledForField() {
        return parameters.getDialogFieldConfig().isDisabled();
    }

    protected String getCssClassForField() {
        if (StringUtils.isNotBlank(parameters.getDialogFieldConfig().getCssClass())) {
            return parameters.getDialogFieldConfig().getCssClass();
        }

        return null;
    }

    /**
     *
     * @return When the widget is represented by a field in the Java class, this
     *         method will return the name of the field. When the widget is
     *         represented by a method in the Java Class, the string 'is' or
     *         'get' is stripped from the method name if it starts with either
     *         of these strings, and then returns the resultant string.
     */
    protected String getName() {
        if (isField()) {
            return parameters.getCtMember().getName();
        } else {
            String tempName = parameters.getCtMember().getName();
            if (tempName.startsWith("is")) {
                return StringUtils.uncapitalise(tempName.substring(2));
            } else if (tempName.startsWith("get")) {
                return StringUtils.uncapitalise(tempName.substring(3));
            } else {
                return StringUtils.uncapitalise(tempName);
            }
        }
    }

    /**
     *
     * @return True if the Widget is represented by a field in the Component
     *         class, false otherwise.
     */
    protected boolean isField() {
        if (parameters.getCtMember() instanceof CtField) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return True if the Widget is represented by a method in the Component
     *         class, false otherwise.
     */
    protected boolean isMethod() {
        return !isField();
    }

    @SuppressWarnings("unchecked")
    public <T> T getAnnotation(Class<T> annotationClass) throws ClassNotFoundException {
        if (parameters.getCtMember().hasAnnotation(annotationClass)) {
            return (T) parameters.getCtMember().getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     *
     * @return THe CtType of the field or method representing the Widget
     * @throws javassist.NotFoundException
     * @throws com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException
     */
    public CtClass getCtType() throws NotFoundException, InvalidComponentFieldException {
        return parameters.getClassPool().getCtClass(getType().getName());
    }

    /**
     *
     * @return The Class of the field or method representing the Widget
     * @throws InvalidComponentFieldException
     */
    public Class<?> getType() throws InvalidComponentFieldException {
        return ComponentUtil.getTypeForMember(parameters.getCtMember(), parameters.getContainingClass());
    }

}
