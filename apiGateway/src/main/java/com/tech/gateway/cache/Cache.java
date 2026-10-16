package com.tech.gateway.cache;

import java.util.Optional;

import com.tech.gateway.bean.SessionInfo;

public interface Cache {

	void save(com.tech.gateway.bean.SessionInfo sessionInfo);

	Optional<com.tech.gateway.bean.SessionInfo> get(String token);

	void remove(String token);

}
