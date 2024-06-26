/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.extensions.action;

import org.opensearch.common.bytes.BytesReference;
import org.opensearch.common.io.stream.BytesStreamInput;
import org.opensearch.common.io.stream.BytesStreamOutput;
import org.opensearch.test.OpenSearchTestCase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TransportActionResponseToExtensionTests extends OpenSearchTestCase {
    public void testTransportActionRequestToExtension() throws IOException {
        byte[] expectedResponseBytes = "response-bytes".getBytes(StandardCharsets.UTF_8);
        TransportActionResponseToExtension response = new TransportActionResponseToExtension(expectedResponseBytes);

        assertEquals(expectedResponseBytes, response.getResponseBytes());

        BytesStreamOutput out = new BytesStreamOutput();
        response.writeTo(out);
        BytesStreamInput in = new BytesStreamInput(BytesReference.toBytes(out.bytes()));
        response = new TransportActionResponseToExtension(in);

        assertArrayEquals(expectedResponseBytes, response.getResponseBytes());
    }

    public void testSetBytes() {
        byte[] expectedResponseBytes = "response-bytes".getBytes(StandardCharsets.UTF_8);
        byte[] expectedEmptyBytes = new byte[0];
        TransportActionResponseToExtension response = new TransportActionResponseToExtension(expectedEmptyBytes);
        assertArrayEquals(expectedEmptyBytes, response.getResponseBytes());

        response.setResponseBytes(expectedResponseBytes);
        assertArrayEquals(expectedResponseBytes, response.getResponseBytes());
    }
}
