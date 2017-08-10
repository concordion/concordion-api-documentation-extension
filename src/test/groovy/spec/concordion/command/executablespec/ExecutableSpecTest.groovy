package spec.concordion.command.executablespec

import name.neuhalfen.concordion.extension.executablespec.ExecutableSpecExtension
import org.concordion.api.extension.Extensions
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(ConcordionRunner.class)
@Extensions(ExecutableSpecExtension)
class ExecutableSpecTest {

    Integer add(Integer a, Integer b) {
        a + b
    }
}
