This repository contains ready-made CloudSlang @Actions, flows, operations and tests for the Couchbase integration.

| Travis CI| Maven Central | Rating | Test coverage | Dependencies | 
| ----- | ----- | ----- | ----- | ----- |
| [![Build Status](https://travis-ci.org/CloudSlang/cs-couchbase.svg?branch=master)](https://travis-ci.org/CloudSlang/cs-couchbase) | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.cloudslang.content/cs-couchbase/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.cloudslang.content/cs-couchbase) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/020187edd3d448bf87695b1424a60f52)](https://www.codacy.com/app/tethryus/cs-couchbase?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=CloudSlang/cs-couchbase&amp;utm_campaign=Badge_Grade) | [![Codacy Badge](https://api.codacy.com/project/badge/Coverage/020187edd3d448bf87695b1424a60f52)](https://www.codacy.com/app/tethryus/cs-couchbase?utm_source=github.com&utm_medium=referral&utm_content=CloudSlang/cs-couchbase&utm_campaign=Badge_Coverage) | [![Dependency Status](https://www.versioneye.com/user/projects/59ef193f0fb24f1045307fc2/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/59ef193f0fb24f1045307fc2) |

# Integration

```markdown
Couchbase Server is an open source, distributed, NoSQL document-oriented database. 
It exposes a fast key-value store with managed cache for submillisecond data operations,
purpose-built indexers for fast queries and a query engine for executing SQL-like queries. 
For mobile and Internet of Things environments Couchbase Lite runs natively on-device and 
manages synchronization to Couchbase Server.
```


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
    + For Linux: `bash cslang`
    + For Windows: `cslang.bat`
5. Run a simple `Get design docs info` operation:

`run --f ../slang/io/cloudslang/content/couchbase/views/get_design_docs_info.sl --i 
 endpoint=couchbase_IP,username=couchbase_USERNAME,password=couchbase_PASSWORD,bucket_name=couchbase_BUCKET_NAME`

Command line arguments in the above example:

| Argument | Description |
| ----- | ----- |
| --f | Location of the flow to run. |
| --i | Arguments the flow takes as input, for multiple arguments use a comma delimited list (e.g. `var1=value1,var2=value2`). |

For more information about the integration, content structure, contribution guide visit our wiki page [here](https://github.com/CloudSlang/cs-couchbase/wiki). 
