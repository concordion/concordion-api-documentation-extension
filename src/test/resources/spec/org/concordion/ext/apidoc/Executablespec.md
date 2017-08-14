[![Build Status](https://travis-ci.org/concordion/concordion-api-documentation-extension.svg?branch=master)](https://travis-ci.org/concordion/concordion-api-documentation-extension)
[![codecov](https://codecov.io/gh/concordion/concordion-api-documentation-extension/branch/master/graph/badge.svg)](https://codecov.io/gh/concordion/concordion-api-documentation-extension)
[![Apache License 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Download](https://api.bintray.com/packages/concordion/maven/concordion-api-documentation-extension/images/download.svg) ](https://bintray.com/concordion/maven/concordion-api-documentation-extension/_latestVersion)
<a href="https://github.com/concordion/concordion-api-documentation-extension"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://camo.githubusercontent.com/e7bbb0521b397edbd5fe43e7f760759336b5e05f/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f72696768745f677265656e5f3030373230302e706e67" alt="Fork me on GitHub" data-canonical-src="https://s3.amazonaws.com/github/ribbons/forkme_right_green_007200.png"/></a>

# Executable Documentation with Concordion

Tired of writing documentation for your library? This [Concordion](http://concordion.org/) plugin can help!

* Never again miss API changes in your documentation!
* Include real code output in your documentation! No more "copy and paste from some old version"!
* Embed code (Groovy) as part of the specification that gets executed in the tests!
* Syntax highlighting by [highlight.js](https://highlightjs.org/)
* xhtml and Markdown!

## HOWTO

To enable extension use the annotation `@Extensions(ExecutableSpecExtension.class)`.
  
## Examples

Browse the tests for examples. 

* [Parsing](parsing/Parsing.html)
* [Executing](executing/Executing.html)
