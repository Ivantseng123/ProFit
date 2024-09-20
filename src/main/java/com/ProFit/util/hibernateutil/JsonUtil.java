package com.ProFit.util.hibernateutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JsonUtil {

	private static ThreadLocal<JavaTimeModule> threadLocalJavaTimeModule = ThreadLocal
			.withInitial(() -> new JavaTimeModule());

	// 執行緒不會覆蓋掉其他執行緒中的mapper
	private static ThreadLocal<ObjectMapper> threadLocalMapper = ThreadLocal.withInitial(() -> {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate6Module());
		// 不要使用默認格式
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	});

	private static ThreadLocal<Boolean> hasSetDatePattern = ThreadLocal.withInitial(() -> false);
	private static ThreadLocal<Boolean> hasSetDateTimePattern = ThreadLocal.withInitial(() -> false);

	// 物件轉JSON
	public static String toJson(Object obj) {
		try {
			register();
			return threadLocalMapper.get().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			refresh();
		}
	}

	// JSON轉物件
	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return threadLocalMapper.get().readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			refresh();
		}
	}

	// 檢查模組是否被註冊過
	private static void register() {
		ObjectMapper mapper = threadLocalMapper.get();
		JavaTimeModule javaTimeModule = threadLocalJavaTimeModule.get();
		Boolean hasDate = hasSetDatePattern.get();
		Boolean hasDateTime = hasSetDateTimePattern.get();

		if (!hasDate) {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
			hasSetDatePattern.set(true);
		}

		if (!hasDateTime) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
			hasSetDateTimePattern.set(true);
		}

		mapper.registerModule(javaTimeModule);
	}

	// 重置mapper和module
	private static void refresh() {
		threadLocalJavaTimeModule = ThreadLocal.withInitial(() -> new JavaTimeModule());
		threadLocalMapper = ThreadLocal.withInitial(() -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Hibernate6Module());
			// 不要使用默認格式
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			return mapper;
		});
		hasSetDatePattern.set(false);
		hasSetDateTimePattern.set(false);
	}

	// 設置date格式
	public static void setDatePattern(String pattern) {
		JavaTimeModule javaTimeModule = threadLocalJavaTimeModule.get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateSerializer localDateTimeSerializer = new LocalDateSerializer(formatter);
		javaTimeModule.addSerializer(LocalDate.class, localDateTimeSerializer);
		hasSetDatePattern.set(true);
	}

	// 設置datetime格式
	public static void setDateTimePattern(String pattern) {
		JavaTimeModule javaTimeModule = threadLocalJavaTimeModule.get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(formatter);
		javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);
		hasSetDateTimePattern.set(true);
	}

	public static void setNonNull() {
		ObjectMapper mapper = threadLocalMapper.get();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}
}