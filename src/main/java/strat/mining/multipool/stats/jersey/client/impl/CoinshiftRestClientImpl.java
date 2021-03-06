/**
 * multipool-stats-backend is a web application which collects statistics
 * on several Switching-profit crypto-currencies mining pools and display
 * then in a Browser.
 * Copyright (C) 2014  Stratehm (stratehm@hotmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with multipool-stats-backend. If not, see <http://www.gnu.org/licenses/>.
 */
package strat.mining.multipool.stats.jersey.client.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import strat.mining.multipool.stats.jersey.ObjectMapperResolver;
import strat.mining.multipool.stats.jersey.TextHtmlJsonFilter;
import strat.mining.multipool.stats.jersey.client.iface.CoinshiftRestClient;
import strat.mining.multipool.stats.jersey.model.coinshift.AddressStats;
import strat.mining.multipool.stats.jersey.model.coinshift.GlobalStats;

@Component("coinshiftRestClient")
public class CoinshiftRestClientImpl implements CoinshiftRestClient {

	private static final Logger PERF_LOGGER = LoggerFactory.getLogger("multipoolStatsPerf");

	private static final Logger LOGGER = LoggerFactory.getLogger(CoinshiftRestClientImpl.class);

	private Client client;

	public CoinshiftRestClientImpl() {
		ClientConfig config = new ClientConfig().register(TextHtmlJsonFilter.class).register(JacksonFeature.class);
		config.register(ObjectMapperResolver.class);
		client = ClientBuilder.newClient(config);
		client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
		client.property(ClientProperties.READ_TIMEOUT, 5000);
	}

	@Override
	public GlobalStats getGlobalStats() {
		long start = System.currentTimeMillis();
		LOGGER.debug("Retrieving raw global stats from coinshift.");
		GlobalStats response = client.target("http://coinshift.com").path("api/stats/performance").request(MediaType.APPLICATION_JSON)
				.get(GlobalStats.class);
		PERF_LOGGER.info("Raw global stats retrieved from coinshift in {} ms.", System.currentTimeMillis() - start);
		return response;
	}

	@Override
	public AddressStats getAddressStats(String address) {
		long start = System.currentTimeMillis();
		LOGGER.debug("Retrieving raw address stats from coinshift for address {}.", address);
		AddressStats response = client.target("http://coinshift.com").path("api/stats/address/" + address).request(MediaType.APPLICATION_JSON)
				.get(AddressStats.class);
		PERF_LOGGER.info("Raw stats for address {} retrieved from coinshift in {} ms.", address, System.currentTimeMillis() - start);
		return response;
	}
}
