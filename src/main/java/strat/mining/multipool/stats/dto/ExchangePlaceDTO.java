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
package strat.mining.multipool.stats.dto;

import java.util.List;

import org.jsonmaker.gwt.client.Jsonizer;

public class ExchangePlaceDTO {

	public interface ExchangePlaceDTOJsonizer extends Jsonizer {
	}

	private String label;
	private String name;
	private List<String> currencyCodes;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCurrencyCodes() {
		return currencyCodes;
	}

	public void setCurrencyCodes(List<String> currencyCodes) {
		this.currencyCodes = currencyCodes;
	}

}
