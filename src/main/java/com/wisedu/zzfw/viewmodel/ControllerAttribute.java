package com.wisedu.zzfw.viewmodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControllerAttribute{
	
	private String controllerRequestMapping;
	
	private String viewPath;
}
