/*
 * Copyright 2002-2013 SCOOP Software GmbH
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
package de.scoopgmbh.copper.test.tranzient.simple;

import de.scoopgmbh.copper.AutoWire;
import de.scoopgmbh.copper.InterruptException;
import de.scoopgmbh.copper.WaitMode;
import de.scoopgmbh.copper.Workflow;
import de.scoopgmbh.copper.test.MockAdapter;
import de.scoopgmbh.copper.util.AsyncResponseReceiver;

public class VerySimpleTransientWorkflow extends Workflow<AsyncResponseReceiver<Integer>> {

	private static final long serialVersionUID = 1L;

	private int i;
	private MockAdapter mockAdapter;
	
	@AutoWire
	public void setMockAdapter(MockAdapter mockAdapter) {
		this.mockAdapter = mockAdapter;
	}
	
	@Override
	public void main() throws InterruptException {
		System.out.println("started");
		for (i=0; i<3; i++) {
			final String cid = mockAdapter.foo("foo");
			wait(WaitMode.ALL, 0, cid);
		}
		System.out.println("finished");
		getData().setResponse(Integer.valueOf(1));
	}
}
