package com.taptap;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class RepositoryConfiguration extends RepositoryRestMvcConfiguration {

	Logger logger = LoggerFactory.getLogger(RepositoryConfiguration.class);

	/**
	 * Override REST configuration to expose ID in REST service for all TAPTAP
	 * domains classes.
	 */
	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		// create a Spring Bean scanner for scanning all Spring beans
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
		// we want only class with Entity annotation
		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		for (BeanDefinition bd : scanner.findCandidateComponents("com.taptap.domain")) {
			try {
				Class<?> c = Class.forName(bd.getBeanClassName());
				for (Field field : this.getAllFields(new LinkedList<Field>(), c)) {
					// we want only field with Id annotation
					Id idAnnotation = field.getAnnotation(Id.class);
					// if null, there aren't Id annotation
					if (idAnnotation != null) {
						// expose ID in json for this Entity
						config.exposeIdsFor(c);
					}
				}
			} catch (ClassNotFoundException e) {
				logger.error(e.getLocalizedMessage());
			}
		}
	}

	public List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}
		return fields;
	}
}
