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

package io.cloudslang.content.couchbase.utils;

import io.cloudslang.content.couchbase.entities.couchbase.ApiUriSuffix;
import io.cloudslang.content.couchbase.entities.couchbase.AuthType;
import io.cloudslang.content.couchbase.entities.couchbase.BucketType;
import io.cloudslang.content.couchbase.entities.couchbase.ConflictResolutionType;
import io.cloudslang.content.couchbase.entities.couchbase.EvictionPolicy;
import io.cloudslang.content.couchbase.entities.couchbase.RecoveryType;
import io.cloudslang.content.couchbase.entities.inputs.InputsWrapper;
import io.cloudslang.content.httpclient.entities.HttpClientInputs;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

import static io.cloudslang.content.couchbase.entities.constants.Constants.Miscellaneous.BLANK_SPACE;
import static io.cloudslang.content.couchbase.entities.constants.Constants.Miscellaneous.COMMA;
import static io.cloudslang.content.couchbase.entities.constants.Constants.Miscellaneous.SLASH;
import static io.cloudslang.content.couchbase.entities.constants.Constants.Values.INIT_INDEX;
import static io.cloudslang.content.couchbase.entities.couchbase.SuffixUri.getSuffixUriValue;
import static io.cloudslang.content.couchbase.factory.HeadersBuilder.buildHeaders;
import static io.cloudslang.content.couchbase.factory.PayloadBuilder.buildPayload;
import static io.cloudslang.content.couchbase.factory.UriFactory.getUri;
import static io.cloudslang.content.couchbase.validators.Validators.isValidBoolean;
import static io.cloudslang.content.utils.NumberUtilities.isValidInt;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.split;

/**
 * Created by Mihai Tusa
 * 3/26/2017.
 */

public class InputsUtil {
    private InputsUtil() {
        // prevent instantiation
    }

    public static void setupApiCall(HttpClientInputs httpClientInputs, InputsWrapper wrapper) throws MalformedURLException {
        httpClientInputs.setUrl(buildUrl(wrapper));

        buildHeaders(wrapper);
        buildPayload(wrapper);
    }

    public static String appendTo(String prefix, String suffix, String action) {
        return Optional
                .ofNullable(suffix)
                .filter(StringUtils::isNotEmpty)
                .map(s -> prefix + SLASH + suffix + getSuffixUriValue(action))
                .orElse(prefix);
    }

    public static String getPayloadString(Map<String, String> payloadMap, String separator, String suffix, boolean deleteLastChar) {
        return Optional
                .of(payloadMap)
                .filter(f -> !payloadMap.isEmpty())
                .map(s -> buildPayloadString(payloadMap, separator, suffix, deleteLastChar))
                .orElse(EMPTY);
    }

    public static String getEnabledString(String input, boolean enforcedBoolean) {
        return getEnforcedBooleanCondition(input, enforcedBoolean) ? valueOf(1) : valueOf(INIT_INDEX);
    }

    public static String[] getStringsArray(String input, String delimiter) {
        return Optional
                .ofNullable(input)
                .filter(StringUtils::isNotEmpty)
                .map(a -> split(input, delimiter))
                .orElse(null);
    }

    public static int getIntegerWithinValidRange(String input, Integer minAllowed, Integer maxAllowed) {
        return Optional
                .of(input)
                .filter(f -> isValidInt(input, minAllowed, maxAllowed, true, true))
                .map(i -> parseInt(input))
                .orElseThrow(() -> new RuntimeException(format("The provided value: %s is not within valid range. " +
                        "See operation inputs description section for details.", input)));
    }

    public static int getIntegerAboveMinimum(String input, Integer minAllowed) {
        try {
            int validInt = parseInt(input);
            if (validInt < minAllowed) {
                throw new RuntimeException(format("The provided value: %s is bellow minimum allowed. " +
                        "See operation inputs description section for details.", input));
            }

            return validInt;
        } catch (NumberFormatException nfe) {
            throw new RuntimeException(format("The provided input value: %s is not integer.", input));
        }
    }

    /**
     * If enforcedBoolean is "true" and string input is: null, empty, many empty chars, TrUe, tRuE... but not "false"
     * then returns "true".
     * If enforcedBoolean is "false" and string input is: null, empty, many empty chars, FaLsE, fAlSe... but not "true"
     * then returns "false"
     * This behavior is needed for inputs like: "imageNoReboot" when we want them to be set to "true" disregarding the
     * value provided (null, empty, many empty chars, TrUe, tRuE) except the case when is "false"
     *
     * @param input           String to be evaluated.
     * @param enforcedBoolean Enforcement boolean.
     * @return A boolean according with above description.
     */
    public static boolean getEnforcedBooleanCondition(String input, boolean enforcedBoolean) {
        return enforcedBoolean ?
                !isBlank(input) && isValidBoolean(input) == parseBoolean(input) : parseBoolean(input);
    }

    public static <T extends Enum<T>> String buildErrorMessage(Class<T> inputEnum) {
        StringBuilder sb = new StringBuilder();
        stream(inputEnum.getEnumConstants())
                .forEach(enumValue -> {
                    if (AuthType.class.getCanonicalName().equalsIgnoreCase(enumValue.getClass().getCanonicalName())) {
                        sb.append(((AuthType) enumValue).getValue()).append(COMMA).append(BLANK_SPACE);
                    } else if (BucketType.class.getCanonicalName().equalsIgnoreCase(enumValue.getClass().getCanonicalName())) {
                        sb.append(((BucketType) enumValue).getValue()).append(COMMA).append(BLANK_SPACE);
                    } else if (ApiUriSuffix.class.getCanonicalName().equalsIgnoreCase(enumValue.getClass().getCanonicalName())) {
                        sb.append(((ApiUriSuffix) enumValue).getValue()).append(COMMA).append(BLANK_SPACE);
                    } else if (ConflictResolutionType.class.getCanonicalName().equalsIgnoreCase(enumValue.getClass().getCanonicalName())) {
                        sb.append(((ConflictResolutionType) enumValue).getValue()).append(COMMA).append(BLANK_SPACE);
                    } else if (EvictionPolicy.class.getCanonicalName().equalsIgnoreCase(enumValue.getClass().getCanonicalName())) {
                        sb.append(((EvictionPolicy) enumValue).getValue()).append(COMMA).append(BLANK_SPACE);
                    } else if (RecoveryType.class.getCanonicalName().equalsIgnoreCase(enumValue.getClass().getCanonicalName())) {
                        sb.append(((RecoveryType) enumValue).getValue()).append(COMMA).append(BLANK_SPACE);
                    }
                });

        return Optional
                .of(sb.toString())
                .filter(StringUtils::isNotEmpty)
                .map(s -> sb.deleteCharAt(sb.length() - 2).toString().trim())
                .orElse(EMPTY);
    }

    public static void setOptionalMapEntry(Map<String, String> inputMap, String key, String value, boolean condition) {
        if (condition) {
            inputMap.put(key, value);
        }
    }

    @NotNull
    private static String buildPayloadString(Map<String, String> payloadMap, String separator, String suffix, boolean deleteLastChar) {
        StringBuilder sb = new StringBuilder();
        payloadMap.forEach((key, value) -> sb.append(key).append(separator).append(value).append(suffix));

        return deleteLastChar ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();
    }

    private static String buildUrl(InputsWrapper wrapper) throws MalformedURLException {
        return new URL(wrapper.getCommonInputs().getEndpoint() + getUri(wrapper)).toString();
    }
}