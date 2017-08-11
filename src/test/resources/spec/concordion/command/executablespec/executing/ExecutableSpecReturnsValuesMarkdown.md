# ExecutableSpec Examples


## [Scripts can return values](-)

<pre><code script:lang="groovy" script:result="#returnValue">

return 42

</code></pre>

This script returns [42]( - "?=#returnValue")!

## [Script do not need to return values](-)
<pre>
<code script:lang="groovy">
import name.neuhalfen.concordion.extension.executablespec.ExecutableSpecExtension

System.out.println("Namespace: " + ExecutableSpecExtension.NAMESPACE);
System.out.println("OUT 1");
System.err.println("ERR 1");
System.out.println("OUT 2");
System.err.println("ERR 2");
</code></pre>

## [Scripts with native format can return values](-)

    ```groovy result:#returnValue
    
    return 7*7
    
    ```

This script returns [49]( - "?=#returnValue")!