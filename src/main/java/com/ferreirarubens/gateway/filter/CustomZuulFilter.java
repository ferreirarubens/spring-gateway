package com.ferreirarubens.gateway.filter;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class CustomZuulFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 10000;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		/*
		 * Adding authorization header to zuul request header as zuul omits sensitive
		 * headers
		 */
		Set<String> headers = (Set<String>) RequestContext.getCurrentContext().get("ignoredHeaders");
		headers.remove("authorization");
		return null;
	}
}