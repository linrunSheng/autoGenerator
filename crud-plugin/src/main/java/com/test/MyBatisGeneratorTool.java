package com.test;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MyBatisGeneratorTool {
	private static final Logger  logger = Logger.getLogger(MyBatisGeneratorTool.class);
	
	public static void main(String[] args) throws Exception {
		testGenerator();
	}
	public static void testGenerator() throws Exception{
		logger.info("MyBatisGenerator: start");
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		String gconfig = "/generatorConfig.xml";
		logger.info("user.dir:"+System.getProperty("user.dir"));
		File configFile = new File(MyBatisGeneratorTool.class.getResource(gconfig).getFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration cfg = null;
		try{
			cfg = cp.parseConfiguration(configFile);
		}catch(Exception e){
			logger.error(e,e);
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator mbg = null;
		try {
			mbg = new MyBatisGenerator(cfg, callback, warnings);
		} catch (InvalidConfigurationException e) {
			logger.error(e,e);
		}
		try {
			mbg.generate(null);
		} catch (SQLException e) {
			logger.error(e,e);
		} catch (IOException e) {
			logger.error(e,e);
		} catch (InterruptedException e) {
			logger.error(e,e);
		}
		logger.info("MyBatisGenerator: end");
	}
}
