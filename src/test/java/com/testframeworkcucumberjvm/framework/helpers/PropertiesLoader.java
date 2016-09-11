package com.testframeworkdemo.framework.helpers;

import static java.lang.System.out;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader {
	private static final Logger LOG = LoggerFactory
			.getLogger(PropertiesLoader.class);
	private static Properties environmentProps;
	private static Properties properties;

	public static String getValor(String key) {
		if ((key == null) || key.isEmpty()) {
			return "";
		} else {
			return properties.getProperty(key);

		}
	}

	public static void loadRunConfigProps(String configPropertyFileLocation) {
		environmentProps = new Properties();
		try (InputStream inputStream = PropertiesLoader.class
				.getResourceAsStream(configPropertyFileLocation)) {
			if (inputStream == null) {
				LOG.info("InputStream do Properties está NULO " + properties);
			} else {
				environmentProps.load(inputStream);
				environmentProps.list(out);
			}
		} catch (IOException e) {
			LOG.error("Erro ao carregar o arquivo de properties");
			e.printStackTrace();
		}
		properties = new Properties();
		try (InputStream inputStream = PropertiesLoader.class
				.getResourceAsStream(environmentProps
						.getProperty("profile.path"))) {
			if (inputStream == null) {
				LOG.info("InputStream do Properties está NULO " + properties);
			} else {

				properties.load(inputStream);
				properties.list(out);
			}
		} catch (IOException e) {
			LOG.error("Erro ao carregar o arquivo de properties");
			e.printStackTrace();
		}
	}
}
