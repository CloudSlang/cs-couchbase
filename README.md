<a href="http://cloudslang.io/">
    <img src="https://camo.githubusercontent.com/ece898cfb3a9cc55353e7ab5d9014cc314af0234/687474703a2f2f692e696d6775722e636f6d2f696849353630562e706e67" alt="CloudSlang logo" title="CloudSlang" align="left" height="60"/>
</a>

<a href="http://cloudslang.io/">
    <img src="https://cdn.worldvectorlogo.com/logos/couchbase.svg" alt="Couchbase logo" title="Couchbase" align="right" height="60"/>
</a>



This repository contains ready-made CloudSlang @Actions, flows, operations and tests for the Couchbase integration.

| Travis CI (Linux/Maven 3.5.0)| Maven Central | Test Coverage |
| ----- | ----- | ----- |
| [![Build Status](https://travis-ci.org/CloudSlang/cs-couchbase.svg?branch=master)](https://travis-ci.org/CloudSlang/cs-couchbase) | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.cloudslang.content/cs-couchbase/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.cloudslang.content/cs-couchbase) | [![Test Coverage](https://api.codeclimate.com/v1/badges/0aa5148d02dc4fa87c91/test_coverage)](https://codeclimate.com/github/CloudSlang/cs-couchbase/test_coverage) |


### Dependency Information

```xml
<dependency>
    <groupId>io.cloudslang.content</groupId>
    <artifactId>cs-couchbase</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Usage

Pre-requisites: JAVA JRE >=7

1. Download the CloudSlang CLI file named cslang-cli.zip/tar.gz:
    + [Stable release](https://github.com/CloudSlang/cloud-slang/releases/latest)
    + [Latest snapshot](https://github.com/CloudSlang/cloud-slang/releases/)
2. Extract it.
3. Go to the folder `cslang/bin/`
4. Run the executable :
  - For Windows: `cslang.bat`
  - For Linux: `bash cslang`
5. Run a simple `Get design docs info` operation:

`run --f ../slang/io/cloudslang/content/couchbase/views/get_design_docs_info.sl --i 
 endpoint=couchbase_IP,username=couchbase_USERNAME,password=couchbase_PASSWORD,bucket_name=couchbase_BUCKET_NAME`

Command line arguments in the above example:

| Argument | Description |
| ----- | ----- |
| --f | Location of the flow to run. |
| --i | Arguments the flow takes as input, for multiple arguments use a comma delimited list (e.g. `var1=value1,var2=value2`). |

For more information about the integration, content structure, contribution guide visit our wiki page [here](https://github.com/CloudSlang/cs-couchbase/wiki). 