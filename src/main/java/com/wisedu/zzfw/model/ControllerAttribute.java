package com.wisedu.zzfw.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControllerAttribute{
	
	private String controllerRequestMapping;
	
	private String viewPath;
}
