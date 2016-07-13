package com.testframeworkdemo.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader {
	private final static Logger logger = LoggerFactory
			.getLogger(PropertiesLoader.class);
	private static Properties props;
	private static String properties = "ambiente.properties";

	public static void loadRunConfigProps() {
		props = new Properties();
		InputStream in = PropertiesLoader.class.getClassLoader()
				.getResourceAsStream(properties);
		try {
			if (in == null) {
				logger.info("InputStream do Properties está NULO " + properties);
			} else {
				props.load(in);
				in.close();
			}
		} catch (IOException e) {
			logger.error("Erro ao carregar o arquivo de properties");
			e.printStackTrace();
		}
	}

	public static String getValor(String chave) {
		return props.getProperty(chave);
	}
}
