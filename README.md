<a href="http://cloudslang.io/">
    <img src="https://camo.githubusercontent.com/ece898cfb3a9cc55353e7ab5d9014cc314af0234/687474703a2f2f692e696d6775722e636f6d2f696849353630562e706e67" alt="CloudSlang logo" title="CloudSlang" align="right" height="60"/>
</a>

[![Build Status](https://travis-ci.org/CloudSlang/cs-couchbase.svg?branch=master)](https://travis-ci.org/CloudSlang/cs-couchbase)

**cs-couchbase** repository contains ready-made CloudSlang @Actions, flows, operations and tests for the Couchbase integration.

### Table of contents

* [About](#sbout)
* [Getting Started](#getting-started)
* [Content Structure](#content-structure)
* [Documentation](#documentation)
* [Get Involved](#get-involved)


<a name="#about"/>

```markdown
Couchbase Server is an open source, distributed, NoSQL document-oriented database. 
It exposes a fast key-value store with managed cache for submillisecond data operations,
purpose-built indexers for fast queries and a query engine for executing SQL-like queries. 
For mobile and Internet of Things environments Couchbase Lite runs natively on-device and 
manages synchronization to Couchbase Server.
```


For more information about the integration visit the [Couchbase](https://developer.couchbase.com/documentation/server/current/introduction/intro.html) website. 

<a name="#geting-started"/>

#### Getting started

###### Pre-Requisite: Java JRE >= 7

1. Download the CloudSlang CLI file named cslang-cli-with-content:
    + [Stable release](https://github.com/CloudSlang/cloud-slang/releases/latest)
    + [Latest snapshot](https://github.com/CloudSlang/cloud-slang/releases/)
2. Extract it.
3. Go to the folder `cslang/bin/`
4. Run the executable :
  - For Windows : `cslang.bat`
  - For Linux : `bash cslang`
5. Run a simple example print text flow: `run --f ../content/io/cloudslang/base/print/print_text.sl --i text=first_flow --cp ../content/`

Command line arguments in the above example:

| Argument | Description |
| ----- | ----- |
| --f | Location of the flow to run. |
| --i | Arguments the flow takes as input, for multiple arguments use a comma delimited list (e.g. `var1=value1,var2=value2`). |
| --cp | Classpath for the location of the content. Required when content imports other content. |


<a name="#content-structure"/>

#### Content Structure

The following is an overview of what is included in the ready-made content:
+ **couchbase** 
    + **buckets**
        + **create_or_edit_bucket** Full Administrators and Cluster Administrators can use the Couchbase Web Console, CLI, or REST API to create a new bucket.
        + **delete_bucket** Full Administrators and Cluster Administrators can delete a bucket.
        + **get_al_buckets** Retrieve information about all the buckets from a specified cluster.
        + **get_bucket** Retrieve specified bucket details information.
        + **get_bucket_statistics** Retrieve usage statistics for a specified bucket.
    + **cluster**
        + **get_cluster_details** Retrieve cluster details.
        + **get_cluster_info** Retrieve cluster information.
        + **rebalancing_nodes** Start rebalancing process on all cluster nodes.
    + **nodes**
        + **fail_over_node** Failover is the process in which a node of a Couchbase cluster is removed quickly as opposed to a regular removal and rebalancing.
        + **graceful_fail_over_node**
        + **set_recovery_type**
    + **views**
        + **get_design_dock_info** Retrieve design documents for a specified Couchbase bucket.

<a name="#documentation"/>

#### Documentation

All documentation is available on the [CloudSlang website](http://www.cloudslang.io/#/docs).

<a name="#get-invoved"/>

#### Get Involved

Read our contributing guide [here](CONTRIBUTING.md).

Contact us [here](mailto:support@cloudslang.io).