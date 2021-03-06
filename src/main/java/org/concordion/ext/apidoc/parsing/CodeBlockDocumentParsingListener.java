package org.concordion.ext.apidoc.parsing;

import nu.xom.*;
import org.concordion.api.listener.DocumentParsingListener;
import org.concordion.ext.apidoc.ExecutableSpecExtension;

public class CodeBlockDocumentParsingListener implements DocumentParsingListener {

    private final MarkdownCodeBlockParser parser = new MarkdownCodeBlockParser();

    @Override
    public void beforeParsing(Document document) {
        recursivelyReplaceCodeBlocks(document.getRootElement());
    }

    private void recursivelyReplaceCodeBlocks(Node node) {


        for (int i = 0; i < node.getChildCount(); i++) {
            Node child = node.getChild(i);
            recursivelyReplaceCodeBlocks(child);
        }

        final boolean isTextNode = node instanceof Text;

        if (isTextNode) {
            final String elementValue = node.getValue();
            int ptr = 0;

            MarkdownCodeBlockParser.Block codeBlock = parser.findFirst(elementValue);

            if (!codeBlock.matches()) {
                // Fast break out
                return;
            }

            // evil, yes. But only Element and Document can be parents.
            Element parent = (Element) node.getParent();

            while ("pre".equalsIgnoreCase(parent.getLocalName()) || "code".equalsIgnoreCase(parent.getLocalName())) {
                node = parent;
                parent = (Element) parent.getParent();
            }
            int insertIndex = parent.indexOf(node);
            parent.removeChild(node);

            while (codeBlock.matches()) {

                final boolean needToInsertTextBeforeBlock = ptr < codeBlock.getWholeBlock_start() - 1;
                if (needToInsertTextBeforeBlock) {
                    parent.insertChild(new Text(elementValue.substring(ptr, codeBlock.getWholeBlock_start() - 1)), insertIndex);
                    insertIndex += 1;
                }

                Element codeElement = buildCodeElement(codeBlock);
                parent.insertChild(codeElement, insertIndex);
                insertIndex += 1;

                ptr = codeBlock.getWholeBlock_end();

                codeBlock = codeBlock.findNext();
            }

            final boolean needToEndTrailingText = ptr < elementValue.length();
            if (needToEndTrailingText) {
                parent.insertChild(new Text(elementValue.substring(ptr)), insertIndex);
            }

        }
    }

    private Element buildCodeElement(MarkdownCodeBlockParser.Block codeBlock) {

        Element pre = new Element("pre");
        Element code = new Element("code");
        code.appendChild(codeBlock.extractCode());

        final MarkdownCodeBlockParser.Block.Config config = codeBlock.parseConfig();
        if (!config.language.isEmpty()) {
            code.addAttribute(new Attribute("script:lang", ExecutableSpecExtension.NAMESPACE, config.language));
        }

        for (String key : config.values.keySet()) {
            code.addAttribute(new Attribute("script:" + key, ExecutableSpecExtension.NAMESPACE, config.values.get(key)));
        }

        pre.appendChild(code);
        return pre;
    }
}
