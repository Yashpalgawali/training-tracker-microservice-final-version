package com.example.demo.audit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

	@Value("${spring.application.name}")
	private String appName;
	
	@Override
	public Optional<String> getCurrentAuditor() {
		
		return Optional.of(appName.toUpperCase()+"_MS" );
//		return Optional.of("COMPANY_MS");
	}

}
