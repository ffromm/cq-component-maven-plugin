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
package com.citytechinc.cq.component.dialog;

import javassist.CtMember;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.Listener;

public class DialogFieldConfig {
	private String xtype;
	private String name;
	private String fieldLabel;
	private String fieldName;
	private String fieldDescription;
	private boolean required;
	private boolean hideLabel;
	private String defaultValue;
	private int tab;
	private FieldProperty[] additionalProperties;
	private Listener[] listeners;
	private double ranking;
	private CtMember member;

	public DialogFieldConfig(DialogField dialogField, CtMember member) {
		this.xtype = dialogField.xtype();
		this.name = dialogField.name();
		this.fieldLabel = dialogField.fieldLabel();
		this.fieldName = dialogField.fieldName();
		this.fieldDescription = dialogField.fieldDescription();
		this.required = dialogField.required();
		this.hideLabel = dialogField.hideLabel();
		this.defaultValue = dialogField.defaultValue();
		this.tab = dialogField.tab();
		this.additionalProperties = dialogField.additionalProperties();
		this.listeners = dialogField.listeners();
		this.ranking = dialogField.ranking();
		this.member = member;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isHideLabel() {
		return hideLabel;
	}

	public void setHideLabel(boolean hideLabel) {
		this.hideLabel = hideLabel;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	public FieldProperty[] getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(FieldProperty[] additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public Listener[] getListeners() {
		return listeners;
	}

	public void setListeners(Listener[] listeners) {
		this.listeners = listeners;
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public CtMember getMember() {
		return member;
	}

	public void setMember(CtMember member) {
		this.member = member;
	}
}
