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

To enable extensions use the annotation `@Extensions(ExecutableSpecExtension.class)`.
  
### Maven
```xml
 <dependency>
  <groupId>name.neuhalfen.concordion.extension</groupId>
  <artifactId>ExecutableSpecExtension</artifactId>
  <version>0.0.3</version>
  <type>pom</type>
</dependency>
```

### Gradle
```
compile 'name.neuhalfen.concordion.extension:ExecutableSpecExtension:0.0.3'
```

## Examples

![Screenshot](assets/ExampleScreenshot.png)


Browse the tests for examples. Here are the appetizers!

### Scripts do not need to return values

    ```groovy
    import ExecutableSpecExtension
    
    System.out.println("Namespace: " + ExecutableSpecExtension.NAMESPACE);
    System.out.println("OUT 1");
    System.err.println("ERR 1");
    System.out.println("OUT 2");
    System.err.println("ERR 2");
    ```
    
```java
@RunWith(ConcordionRunner.class)
@Extensions(ExecutableSpecExtension)
class ExecutableSpecReturnsValuesMarkdownTest {
  // no code needed for this
}
```
* [Source file (markdown)](src/test/resources/spec/concordion/command/executablespec/executing/ExecutableSpecReturnsValuesMarkdown.md)
* [Fixture](src/test/groovy/spec/concordion/command/executablespec/executing/ExecutableSpecReturnsValuesMarkdown.groovy)
* [See the result here](https://concordion.github.io/concordion-api-documentation-extension/spec/spec/concordion/command/executablespec/executing/ExecutableSpecReturnsValuesMarkdown.html)

### Scripts with native format can return values

    ```groovy result:#returnValue
    
    return 7*7
    
    ```

    This script returns [49]( - "?=#returnValue") (this check is verified as part of the specification)!

* [Source file (markdown)](src/test/resources/spec/concordion/command/executablespec/executing/ExecutableSpecReturnsValuesMarkdown.md)
* [Fixture](src/test/groovy/spec/concordion/command/executablespec/executing/ExecutableSpecReturnsValuesMarkdown.groovy)
* [See the result here](https://concordion.github.io/concordion-api-documentation-extension/spec/spec/concordion/command/executablespec/executing/ExecutableSpecReturnsValuesMarkdown.html)

### Compile Errors fail the test

    ```groovy
    xxxCompiler error;
    System.out.println("We should never get here!");
    ```


* [Source file (html)](src/test/resources/spec/concordion/command/executablespec/executing/CompileErrors.html)
* [Fixture](src/test/groovy/spec/concordion/command/executablespec/executing/CompileErrorsTest.groovy)
* [See the result here](https://concordion.github.io/concordion-api-documentation-extension/spec/spec/concordion/command/executablespec/executing/CompileErrors.html)

### Skip compilation/execution

    ```groovy run:no
    xxxCompiler error;
    System.out.println("We should never get here!");
    ```
    
* [Source file (markdown)](src/test/resources/spec/concordion/command/executablespec/executing/MarkDownNoExecution.md)
* [Fixture](src/test/groovy/spec/concordion/command/executablespec/executing/MarkDownNoExecutionTest.groovy)
* [See the result here](https://concordion.github.io/concordion-api-documentation-extension/spec/spec/concordion/command/executablespec/executing/MarkDownNoExecution.html)

### Other languages (without execution)
    ``` shell
    total 0
    drwxr-xr-x@ 3 root  wheel  102 Nov 25  2016 ..
    drwxr-xr-x@ 5 jens  staff  170 Aug 10 15:35 .
    drwx------@ 9 jens  staff  306 Aug 10 16:42 demo
    ```
    
* [Source file (html)](src/test/resources/spec/concordion/command/executablespec/executing/UnknownLanguages.html)
* [Fixture](src/test/groovy/spec/concordion/command/executablespec/executing/UnknownLanguagesTest.groovy)
* [See the result here](https://concordion.github.io/concordion-api-documentation-extension/spec/spec/concordion/command/executablespec/executing/UnknownLanguages.html)

# Thanks

The base skeleton (e.g. build system) has been copied from https://github.com/concordion/concordion-input-style-extension-demo


