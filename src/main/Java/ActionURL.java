import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.codehaus.groovy.control.messages.Message;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ActionURL extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Messages.showMessageDialog("Hello!", "its me", Messages.getInformationIcon());
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText != null) {
            String encoded = "";
            try {
                encoded = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            String url = "https://www.phind.com/search?q=" + encoded + "&source=searchbox";
            BrowserUtil.browse(url);
        } else {
            Messages.showMessageDialog("Please, select some text", "AskGPT Plugin", Messages.getInformationIcon());
        }


    }
    @Override
    public boolean isDumbAware() {
        return false;
    }
}
