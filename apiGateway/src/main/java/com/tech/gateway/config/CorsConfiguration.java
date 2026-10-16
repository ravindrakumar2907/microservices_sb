package com.tech.gateway.config;

/*
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
*/
public class CorsConfiguration {

	
	//https://reflectoring.io/spring-cors/
	
	/*
	@Bean
	public WebMvcConfigurer corsMappingConfigurer() {
	   return new WebMvcConfigurer() {
	       @Override
	       public void addCorsMappings(CorsRegistry registry) {
	           WebConfigProperties.Cors cors = webConfigProperties.getCors();
	           registry.addMapping("/**")
	             .allowedOrigins(cors.getAllowedOrigins())
	             .allowedMethods(cors.getAllowedMethods())
	             .maxAge(cors.getMaxAge())
	             .allowedHeaders(cors.getAllowedHeaders())
	             .exposedHeaders(cors.getExposedHeaders());
	       }
	   };
	}
	
	@Bean
	public WebFluxConfigurer corsMappingConfigurer() {
	    return new WebFluxConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            WebConfigProperties.Cors cors = webConfigProperties.getCors();
	            registry.addMapping("/**")
	              .allowedOrigins(cors.getAllowedOrigins())
	              .allowedMethods(cors.getAllowedMethods())
	              .maxAge(cors.getMaxAge())
	              .allowedHeaders(cors.getAllowedHeaders())
	              .exposedHeaders(cors.getExposedHeaders());
	        }
	    };
	}
	
	@Bean
	public CorsWebFilter corsWebFilter() {
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	    corsConfig.setMaxAge(3600L);
	    corsConfig.addAllowedMethod("*");
	    corsConfig.addAllowedHeader("Requestor-Type");
	    corsConfig.addExposedHeader("X-Get-Header");

	    UrlBasedCorsConfigurationSource source =
	        new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);

	    return new CorsWebFilter(source);
	}
	
	*/
}
