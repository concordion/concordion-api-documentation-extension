package name.neuhalfen.concordion.extension.executablespec.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownCodeBlockParser {

    private final static Pattern CODEBLOCK = Pattern.compile(
            "^```\\p{Blank}*(?<config>[^\\n\\r]*+)?[\\r\\n]*        # Configuration options from the block\n"
                    + "^(?<code>.*?)[\\r\\n]    # The code block itself, must end on a line end\n"
                    + "^```$                 # Block end"
            ,
            Pattern.MULTILINE | Pattern.COMMENTS | Pattern.DOTALL);


    public final static class Block {
        private final String text;

        public int getWholeBlock_start() {
            return whole_block_start;
        }

        public int getWholeBlock_end() {
            return whole_block_end;
        }

        private final int whole_block_start;
        private final int whole_block_end;

        private final int config_start;
        private final int config_end;

        private final int code_start;
        private final int code_end;

        private Block(String text, int whole_block_start, int whole_block_end, int config_start, int config_end, int code_start, int code_end) {
            this.text = text;
            this.whole_block_start = whole_block_start;
            this.whole_block_end = whole_block_end;
            this.config_start = config_start;
            this.config_end = config_end;
            this.code_start = code_start;
            this.code_end = code_end;
        }

        public String extractConfig() {
            return text.substring(config_start, config_end);
        }

        public String extractCode() {
            return text.substring(code_start, code_end);
        }

        public boolean matches() {
            return whole_block_end > whole_block_start;
        }

        static Block noMatch() {
            return new Block(null, 0, 0, 0, 0, 0, 0);
        }

        static Block match(String text, int whole_block_start, int whole_block_end, int config_start, int config_end, int code_start, int code_end) {
            return new Block(text, whole_block_start, whole_block_end, config_start, config_end, code_start, code_end);
        }


        public Block findNext() {
            final Matcher matcher = CODEBLOCK.matcher(text);
            if (matcher.find(this.getWholeBlock_end())) {
                return Block.match(text, matcher.start(), matcher.end(), matcher.start("config"), matcher.end("config"), matcher.start("code"), matcher.end("code"));
            } else {

                return Block.noMatch();
            }
        }
    }

    public  Block findFirst(String elementValue) {
        final Matcher matcher = CODEBLOCK.matcher(elementValue);
        if (matcher.find()) {
            return Block.match(elementValue, matcher.start(), matcher.end(), matcher.start("config"), matcher.end("config"), matcher.start("code"), matcher.end("code"));
        } else {

            return Block.noMatch();
        }
    }


}
