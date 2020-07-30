package cn.wildfire.chat.app.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import cn.wildfire.chat.kit.annotation.ExtContextMenuItem;
import cn.wildfire.chat.kit.conversation.ext.core.ConversationExt;
import com.example.plugin_chat_example.R;
import cn.wildfirechat.message.TypingMessageContent;
import cn.wildfirechat.model.Conversation;

//红包扩展 TODO
public class ExampleAudioInputExt extends ConversationExt {

    /**
     * @param containerView 扩展view的container
     * @param conversation
     */
    @ExtContextMenuItem
    public void image(View containerView, Conversation conversation) {
        FrameLayout frameLayout = (FrameLayout) containerView;
        View view = LayoutInflater.from(activity).inflate(R.layout.conversatioin_ext_red_layout, frameLayout, false);
        frameLayout.addView(view);
        extension.disableHideOnScroll();
        EditText inputNum = view.findViewById(R.id.input_num);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extension.reset();
                sendRedMessage(inputNum.getText().toString());
            }
        });
    }

    private void sendRedMessage(String num) {
        TypingMessageContent content = new TypingMessageContent(10);
        messageViewModel.sendMessage(conversation, content);

        RedMessageContent redCon = new RedMessageContent("恭喜发财", num, "1212312");
        messageViewModel.sendRedMessage(conversation, redCon);
    }

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public int iconResId() {
        return R.mipmap.ic_func_voice;
    }

    @Override
    public String title(Context context) {
        return "红包";
    }

    @Override
    public String contextMenuTitle(Context context, String tag) {
        return title(context);
    }
}
