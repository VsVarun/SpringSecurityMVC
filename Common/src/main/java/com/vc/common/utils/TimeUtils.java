package com.vc.common.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TimeUtils {
	
	@Value("${ZONE}")
	private String ZONE;
	
	public Timestamp getTimeStamp() {
		return Timestamp.valueOf(LocalDateTime.now(ZoneId.of(ZONE)));
	}
	
}
