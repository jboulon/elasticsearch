/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.common.text;

import com.google.common.base.Charsets;
import org.elasticsearch.common.bytes.BytesReference;

/**
 * A {@link BytesReference} representation of the text, will always convert on the fly to a {@link String}.
 */
public class BytesText implements Text {

    private BytesReference bytes;

    public BytesText(BytesReference bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean hasBytes() {
        return true;
    }

    @Override
    public BytesReference bytes() {
        return bytes;
    }

    @Override
    public boolean hasString() {
        return false;
    }

    @Override
    public String string() {
        // TODO: we can optimize the conversion based on the bytes reference API similar to UnicodeUtil
        if (!bytes.hasArray()) {
            bytes = bytes.toBytesArray();
        }
        return new String(bytes.array(), bytes.arrayOffset(), bytes.length(), Charsets.UTF_8);
    }

    @Override
    public String toString() {
        return string();
    }
}
