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

package io.cloudslang.content.couchbase.execute;

import io.cloudslang.content.couchbase.entities.inputs.CommonInputs;
import io.cloudslang.content.couchbase.entities.inputs.InputsWrapper;
import io.cloudslang.content.httpclient.entities.HttpClientInputs;
import io.cloudslang.content.httpclient.services.HttpClientService;
import io.cloudslang.content.utils.OutputUtilities;
import io.vavr.control.Try;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static io.cloudslang.content.couchbase.entities.constants.Constants.Values.THREADS_NUMBER;
import static io.cloudslang.content.couchbase.factory.InputsWrapperBuilder.buildWrapper;
import static io.cloudslang.content.couchbase.utils.InputsUtil.setupApiCall;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Created by Mihai Tusa
 * 3/26/2017.
 */
public class CouchbaseService {
    private final ExecutorService executorService = newFixedThreadPool(THREADS_NUMBER);

    @SafeVarargs
    public final <T> Map<String, String> execute(HttpClientInputs httpClientInputs, CommonInputs commonInputs, T... builders) {
        InputsWrapper wrapper = buildWrapper(httpClientInputs, commonInputs, builders);

        return Try
                .of(() -> {
                    setupApiCall(httpClientInputs, wrapper);

                    return asyncCall(httpClientInputs);
                })
                .onFailure(OutputUtilities::getFailureResultsMap)
                .get();
    }

    private Map<String, String> asyncCall(HttpClientInputs httpClientInputs) throws InterruptedException, ExecutionException {
        return CompletableFuture
                .supplyAsync(() -> new HttpClientService().execute(httpClientInputs), executorService)
                .exceptionally(OutputUtilities::getFailureResultsMap)
                .get();
    }
}