package com.wisedu.zzfw.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JavaAttribute {

	protected Clazz service;

	protected Clazz pageModel;

	protected Clazz model;

	protected Clazz controller;

	protected String description;

}
