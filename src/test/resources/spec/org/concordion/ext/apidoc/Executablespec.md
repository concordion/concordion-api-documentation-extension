[![Build Status](https://travis-ci.org/concordion/concordion-api-documentation-extension.svg?branch=master)](https://travis-ci.org/concordion/concordion-api-documentation-extension)
[![codecov](https://codecov.io/gh/concordion/concordion-api-documentation-extension/branch/master/graph/badge.svg)](https://codecov.io/gh/concordion/concordion-api-documentation-extension)
[![Apache License 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Download](https://api.bintray.com/packages/concordion/maven/concordion-api-documentation-extension/images/download.svg) ](https://bintray.com/concordion/maven/concordion-api-documentation-extension/_latestVersion)

# Executable Documentation with Concordion

Tired of writing documentation for your library? This [Concordion](http://concordion.org/) plugin can help!

* Never again miss API changes in your documentation!
* Include real code output in your documentation! No more "copy and paste from some old version"!
* Embed code (Groovy) as part of the specification that gets executed in the tests!
* Syntax highlighting by [highlight.js](https://highlightjs.org/)
* xhtml and Markdown!

## HOWTO

To enable extension use the annotation `@Extensions(ExecutableSpecExtension.class)`.
  
### Maven
     <dependency>
      <groupId>org.concordion</groupId>
      <artifactId>concordion-api-documentation-extension</artifactId>
      <version>0.0.2</version>
      <type>pom</type>
    </dependency>


### Gradle

    compile 'org.concordion:concordion-api-documentation-extension:0.0.2'

## Examples

Browse the tests for examples. 

* [Parsing](parsing/Parsing.html)
* [Executing](executing/Executing.html)
