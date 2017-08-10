[![Build Status](https://travis-ci.org/neuhalje/concordion-executable-documentation-extension.svg?branch=master)](https://travis-ci.org/neuhalje/concordion-executable-documentation-extension)
[![codecov](https://codecov.io/gh/neuhalje/concordion-executable-documentation-extension/branch/master/graph/badge.svg)](https://codecov.io/gh/neuhalje/concordion-executable-documentation-extension)
[![Apache License 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

# Executable Documentation with Concordion

Tired of writing documentation for your library? This [Concordion](http://concordion.org/) plugin can help!

* Never again miss API changes in your documentation!
* Include real code output in your documentation! No more "copy and paste from some old version"!
* Embed code (Groovy) as part of the specification that gets executed in the tests!
* Syntax highlighting by [highlight.js](https://highlightjs.org/)
* xhtml and Markdown!

## HOWTO

To enable extensions, set systemProperty
    `-Dconcordion.extensions=name.neuhalfen.concordion.extension.executablespec.ExecutableSpecExtension` or use
    the annotation
    `@Extensions(ExecutableSpecExtension.class)`.

## Examples

Browse the tests for examples. Here are the appetizers!

### Scripts do not need to return values

    ```groovy
    import name.neuhalfen.concordion.extension.executablespec.ExecutableSpecExtension
    
    System.out.println("Namespace: " + ExecutableSpecExtension.NAMESPACE);
    System.out.println("OUT 1");
    System.err.println("ERR 1");
    System.out.println("OUT 2");
    System.err.println("ERR 2");
    ```
    
[See the result here](https://neuhalje.github.io/concordion-executable-documentation-extension/spec/spec/concordion/command/executablespec/ExecutableSpecReturnsValuesMarkdown.html)

### Scripts with native format can return values

    ```groovy result:#returnValue
    
    return 7*7
    
    ```

This script returns [49]( - "?=#returnValue")!

[See the result here](https://neuhalje.github.io/concordion-executable-documentation-extension/spec/spec/concordion/command/executablespec/ExecutableSpecReturnsValuesMarkdown.html)


### Compile Errors fail the test

    ```groovy
    xxxCompiler error;
    System.out.println("We should never get here!");
    ```

[See the result here](https://neuhalje.github.io/concordion-executable-documentation-extension/spec/spec/concordion/command/executablespec/CompileErrors.html)

### Skip compilation/execution

    ```groovy run:no
    xxxCompiler error;
    System.out.println("We should never get here!");
    ```
 
[See the result here](https://neuhalje.github.io/concordion-executable-documentation-extension/spec/spec/concordion/command/executablespec/MarkDownNoExecution.html)

### Other languages (without execution)
    ``` shell
    total 0
    drwxr-xr-x@ 3 root  wheel  102 Nov 25  2016 ..
    drwxr-xr-x@ 5 jens  staff  170 Aug 10 15:35 .
    drwx------@ 9 jens  staff  306 Aug 10 16:42 demo
    ```
    
[See the result here](https://neuhalje.github.io/concordion-executable-documentation-extension/spec/spec/concordion/command/executablespec/UnknownLanguages.html)

# Thanks

The base skeleton (e.g. build system) has been copied from https://github.com/concordion/concordion-input-style-extension-demo


