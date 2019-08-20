/*
 * (c) Copyright 2017 Hewlett-Packard Enterprise Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package io.cloudslang.content.couchbase.entities.couchbase;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Created by TusaM
 * 4/20/2017.
 */
public enum ClusterUri {
    GET_CLUSTER_DETAILS("GetClusterDetails", "/pools/default"),
    GET_CLUSTER_INFO("GetClusterInfo", "/pools"),
    REBALANCING_NODES("RebalancingNodes", "/controller/rebalance");

    private final String key;
    private final String value;

    private static final Map<String, String> CLUSTER_URI_MAP = new HashMap<>();

    static {
        stream(values()).forEach(entry -> CLUSTER_URI_MAP.put(entry.getKey(), entry.getValue()));
    }

    ClusterUri(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String input) {
        return Optional
                .ofNullable(CLUSTER_URI_MAP.get(input))
                .orElse(EMPTY);
    }

    private String getKey() {
        return key;
    }

    private String getValue() {
        return value;
    }
}