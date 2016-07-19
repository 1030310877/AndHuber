package joe.view.webviewcode;

import android.content.Context;
import android.os.Build;
import android.webkit.WebView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CodeView {

    final static String DEFAULT_STYLE = Settings.WithStyle.DEFAULT;

    private static volatile CodeView singleton = null;

    private static String[] htmlWrapper;
    private static List<Content> contents;

    private boolean isTextWrap = false;

    private String style;

    public CodeView(Context context) {
        htmlWrapper = new String[]{"", "", "", "", ""};
        contents = new ArrayList<>();
        style = DEFAULT_STYLE;
    }


    /**
     * @param context
     * @return Double-checked locking  Singleton
     */
    public static CodeView with(Context context) {
        resetCache();
        if (singleton == null) {
            synchronized (CodeView.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }

        return singleton;
    }


    /**
     * @param code the code will be highlight
     * @return
     */
    public CodeView withCode(String code) {
        code = code.replace("<", "&lt;").replace(">", "&gt;");
        appender(new Content(code, Content.TYPE_CODE));
        return this;
    }


    /**
     * @param text will be treated as simple text no highlight
     * @return
     */
    public CodeView withText(String text) {
        appender(new Content(text, Content.TYPE_TEXT));
        return this;
    }

    /**
     * @param html will add html code right after ,
     *             can call multiple times to append extra html code into body section
     * @return
     */
    public CodeView withHtml(String html) {
        appender(new Content(html, Content.TYPE_HTML));
        return this;
    }


    /**
     * @param htmlHeadContent set link,css,javascript tags inside
     * @return
     */
    public CodeView setHtmlHeadContent(String htmlHeadContent) {
        htmlWrapper[1] += htmlHeadContent + " \n";
        return this;
    }

    public CodeView setStyle(String style) {

        if (style != null && !style.isEmpty()) {
            this.style = style;
        } else {
            this.style = DEFAULT_STYLE;
        }

        return this;
    }

    public CodeView setAutoWrap(boolean wrap) {
        this.isTextWrap = wrap;
        return this;
    }

    /**
     * @param webview or any subclass of webview
     * @param <T>
     */
    public <T extends WebView> void into(T webview) {

        if (webview == null) {
            throw new IllegalArgumentException("webview cannot be null");
        }

        //checkWebview(webview);

        if (!webview.getSettings().getJavaScriptEnabled()) {
            webview.getSettings().setJavaScriptEnabled(true);
        }

        String htmlDocument = Builder.makeDocument(htmlWrapper, new Options(this.isTextWrap, this.style, this.contents));
        webview.loadDataWithBaseURL(null, htmlDocument, "text/html", "utf-8", "");

    }

    private void appender(Content con) {
        this.contents.add(con);
    }

    /**
     * builder class...
     */
    private static class Builder {

        private final Context context;

        /**
         * @param context
         */
        public Builder(Context context) {

            if (context == null) {
                throw new IllegalArgumentException("Context can't be null");
            }

            this.context = context.getApplicationContext();
        }


        /**
         * @return
         */
        public CodeView build() {
            return new CodeView(this.context);
        }


        /**
         * @param content the array string containing the code,text and headers
         * @param options the users options
         * @return the complete html document
         */
        static String makeDocument(String content[], Options options) {

            content[0] = HtmlCode.HTML_HEAD + "\n" + HtmlCode.HTML_META + "\n";
            content[1] = content[1] + "<style>" + options.style + "</style> \n";
            content[1] = content[1] + "<script>" + HighlightLib.HIGHLIGHTJS + "</script> \n";

            if (options.isTextWrap) {
                content[1] = content[1] + "<style>" + HtmlCode.STYLE_TEXT_WRAP + "</style> \n";
            }

            content[1] = content[1] + "<script>" + "hljs.initHighlightingOnLoad();" + "</script> \n";
            content[2] = HtmlCode.HTML_HEAD_END + "\n";

            content[3] += extractBuildContent(options.contents, options.isTextWrap);

            content[4] = HtmlCode.HTML_BODY_END + " \n";

            return content[0] + content[1] + content[2] + content[3] + content[4];
        }


    }


    private static class Options {

        private final boolean isTextWrap;
        private final String style;
        private final List<Content> contents;

        public Options(boolean isTextWrap, String style, List<Content> contents) {
            this.isTextWrap = isTextWrap;
            this.style = style;
            this.contents = contents;
        }


    }


    /**
     * @param contents
     * @return
     */
    private static String extractBuildContent(List<Content> contents, boolean isTextWrap) {
        if (!contents.isEmpty()) {

            String content = "";

            for (int i = 0; i < contents.size(); i++) {

                if (contents.get(i).getType().equals(Content.TYPE_CODE)) {

                    if (isTextWrap) {
                        content += HtmlCode.HTML_PRE_CODE_WRAP_START + HtmlCode.HTML_PRE_CODE_CLASS + contents.get(i).getText() + HtmlCode.HTML_PRE_CODE_END;
                    } else {
                        content += HtmlCode.HTML_PRE_CODE_START + HtmlCode.HTML_PRE_CODE_CLASS + contents.get(i).getText() + HtmlCode.HTML_PRE_CODE_END;
                    }

                } else if (contents.get(i).getType().equals(Content.TYPE_HTML)) {
                    content += contents.get(i).getText();
                } else {
                    content += HtmlCode.HTML_PRE_TEXT_START + contents.get(i).getText() + HtmlCode.HTML_PRE_TEXT_END;
                }

            }

            return content;
        }
        return null;
    }


    private <T extends WebView> void checkWebview(T webview) {
        if (Build.VERSION.SDK_INT < 18) {
            webview.clearView();

        } else {
            webview.loadDataWithBaseURL(null, "about:blank", "text/html", "utf-8", "");
        }
    }


    private static void resetCache() {
        contents = new ArrayList<>();
        htmlWrapper = new String[]{"", "", "", "", ""};
    }

    /**
     * @return a list<String> with all supported styles
     */
    static List<String> getAllCodeStyles() {
        Field[] fields = Settings.WithStyle.class.getFields();
        List<String> styles = new ArrayList<>();
        for (Field f : fields) {
            styles.add(f.getName());
        }
        return styles;
    }
}
