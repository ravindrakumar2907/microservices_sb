package com.tech.gateway.cache;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SPLocalCache implements com.tech.gateway.cache.Cache {

	Logger logger = LoggerFactory.getLogger(SPLocalCache.class);
	
	final LocalCacheRepository<com.tech.gateway.bean.SessionInfo> localCacheRepository;

	@Autowired
	public SPLocalCache(LocalCacheRepository<com.tech.gateway.bean.SessionInfo> localCacheRepository) {
		this.localCacheRepository = localCacheRepository;
	}

	@Override
	public void save(com.tech.gateway.bean.SessionInfo sessionInfo) {
		localCacheRepository.save(sessionInfo);
	}

	@Override
	public Optional<com.tech.gateway.bean.SessionInfo> get(String token) {

		return localCacheRepository.get(token);
	}

	@Override
	public void remove(String token) {
		localCacheRepository.remove(token);
	}

    public int getCount(){
        return localCacheRepository.getCount();
    }

}

//https://medium.com/vedity/spring-boot-caching-mechanism-8ef901147e60#:~:text=Spring%20Boot%20Cache%20Providers,caching%20library%20in%20the%20classpath.
//https://medium.com/tech-it-out/spring-boot-in-memory-caching-f2327f9bfdd6