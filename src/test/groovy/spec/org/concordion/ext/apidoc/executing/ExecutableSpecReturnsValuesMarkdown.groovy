package spec.org.concordion.ext.apidoc.executing

import org.concordion.ext.apidoc.ExecutableSpecExtension
import org.concordion.api.extension.Extensions
import org.concordion.api.option.ConcordionOptions
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

@RunWith(ConcordionRunner.class)
@Extensions(ExecutableSpecExtension)
@ConcordionOptions(declareNamespaces = ["script", "http://neuhalfen.name/concordion/extensions/run"])
class ExecutableSpecReturnsValuesMarkdown {

}
