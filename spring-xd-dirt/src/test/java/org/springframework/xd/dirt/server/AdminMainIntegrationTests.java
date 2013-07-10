/*
 * Copyright 2011-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.xd.dirt.server;

import org.junit.Test;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.xd.dirt.server.options.AdminOptions;
import org.springframework.xd.dirt.stream.StreamServer;

/**
 * @author Luke Taylor
 */
public class AdminMainIntegrationTests {

	@Test
	public void redisStoreWithLocalTransportConfigurationLoadsSuccessfully() throws Exception {
		AdminOptions opts = AdminMain.parseOptions(new String[] {"--httpPort", "0", "--transport", "local", "--store", "redis", "--disableJmx", "true"});
		StreamServer s = AdminMain.launchStreamServer(opts);
		shutdown(s);
	}

	@Test
	public void inMemoryStoreWithLocalTransportConfigurationLoadsSuccessfully() throws Exception {
		AdminOptions opts = AdminMain.parseOptions(new String[] {"--httpPort", "0", "--transport", "local", "--store", "memory", "--disableJmx", "true"});
		StreamServer s = AdminMain.launchStreamServer(opts);
		shutdown(s);
	}

	private void shutdown(StreamServer s) {
		s.stop();
		DirectFieldAccessor dfa = new DirectFieldAccessor(s);
		((XmlWebApplicationContext)dfa.getPropertyValue("webApplicationContext")).destroy();
	}

}