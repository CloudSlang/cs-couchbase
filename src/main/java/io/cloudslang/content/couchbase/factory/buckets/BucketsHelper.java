/*
 * (c) Copyright 2017 Hewlett-Packard Enterprise Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */
/*
 * (c) Copyright 2017 EntIT Software LLC, a Micro Focus company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.cloudslang.content.couchbase.factory.buckets;

import io.cloudslang.content.couchbase.entities.inputs.InputsWrapper;

import java.util.HashMap;
import java.util.Map;

import static io.cloudslang.content.couchbase.entities.constants.Constants.Miscellaneous.AMPERSAND;
import static io.cloudslang.content.couchbase.entities.constants.Constants.Miscellaneous.EQUAL;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.BUCKET_TYPE;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.CONFLICT_RESOLUTION_TYPE;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.EVICTION_POLICY;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.FLUSH_ENABLED;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.PARALLEL_DB_VIEW_COMPACTION;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.RAM_QUOTA_DB;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.REPLICA_INDEX;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.REPLICA_NUMBER;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.SASL_PASSWORD;
import static io.cloudslang.content.couchbase.entities.constants.Inputs.BucketInputs.THREADS_NUMBER;
import static io.cloudslang.content.couchbase.utils.InputsUtil.getPayloadString;
import static io.cloudslang.content.couchbase.utils.InputsUtil.setOptionalMapEntry;
import static io.cloudslang.content.couchbase.validators.Validators.validateAuthType;
import static io.cloudslang.content.httpclient.HttpClientInputs.AUTH_TYPE;
import static io.cloudslang.content.httpclient.HttpClientInputs.PROXY_PORT;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by TusaM
 * 4/14/2017.
 */
public class BucketsHelper {
    private static final String NAME = "name";

    public String getCreateBucketPayload(InputsWrapper wrapper) {
        return getPayloadString(getPayloadMap(wrapper), EQUAL, AMPERSAND, true);
    }

    private Map<String, String> getPayloadMap(InputsWrapper wrapper) {
        Map<String, String> payloadMap = new HashMap<>();

        setOptionalMapEntry(payloadMap, PROXY_PORT, valueOf(wrapper.getBucketInputs().getProxyPort()),
                isNotBlank(valueOf(wrapper.getBucketInputs().getProxyPort())));
        setOptionalMapEntry(payloadMap, SASL_PASSWORD, wrapper.getBucketInputs().getSaslPassword(),
                isNotBlank(wrapper.getBucketInputs().getSaslPassword()));

        validateAuthType(payloadMap, wrapper.getBucketInputs().getAuthType());

        payloadMap.put(AUTH_TYPE, wrapper.getBucketInputs().getAuthType());
        payloadMap.put(BUCKET_TYPE, wrapper.getBucketInputs().getBucketType());
        payloadMap.put(CONFLICT_RESOLUTION_TYPE, wrapper.getBucketInputs().getConflictResolutionType());
        payloadMap.put(EVICTION_POLICY, wrapper.getBucketInputs().getEvictionPolicy());
        payloadMap.put(FLUSH_ENABLED, wrapper.getBucketInputs().getFlushEnabled());
        payloadMap.put(NAME, wrapper.getBucketInputs().getBucketName());
        payloadMap.put(PARALLEL_DB_VIEW_COMPACTION, wrapper.getBucketInputs().getParallelDBAndViewCompaction());
        payloadMap.put(RAM_QUOTA_DB, valueOf(wrapper.getBucketInputs().getRamQuotaMB()));
        payloadMap.put(REPLICA_INDEX, valueOf(wrapper.getBucketInputs().getReplicaIndex()));
        payloadMap.put(REPLICA_NUMBER, valueOf(wrapper.getBucketInputs().getReplicaNumber()));
        payloadMap.put(THREADS_NUMBER, valueOf(wrapper.getBucketInputs().getThreadsNumber()));

        return payloadMap;
    }
}