package com.bista.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;



@Configuration
@ConfigurationProperties(prefix = "cart")
@Data
public class PropertiesConfig {

	private Map<String, String> datasource;
	private String ddlAuto;
	private Boolean showSql;
	private String dialect;
}
