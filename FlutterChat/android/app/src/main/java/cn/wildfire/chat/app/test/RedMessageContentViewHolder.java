package cn.wildfire.chat.app.test;


import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import butterknife.BindView;
import butterknife.OnClick;
import cn.wildfire.chat.kit.annotation.EnableContextMenu;
import cn.wildfire.chat.kit.annotation.MessageContentType;
import cn.wildfire.chat.kit.conversation.ConversationFragment;
import cn.wildfire.chat.kit.conversation.message.model.UiMessage;
import cn.wildfire.chat.kit.conversation.message.viewholder.NormalMessageContentViewHolder;
import com.example.plugin_chat_example.R;
import cn.wildfirechat.remote.ChatManager;


@MessageContentType(RedMessageContent.class)
@EnableContextMenu
public class RedMessageContentViewHolder extends NormalMessageContentViewHolder {
    @BindView(R.id.num)
    TextView num;

    public RedMessageContentViewHolder(ConversationFragment fragment, Adapter adapter, View itemView) {
        super(fragment, adapter, itemView);
    }

    @Override
    protected void onBind(UiMessage message) {
        RedMessageContent messageContent = (RedMessageContent) message.message.content;
        this.num.setText(messageContent.num);
    }

    @OnClick({R.id.red_bg})
    void preview() {
//        Toast.makeText(this.fragment.getContext(), "点击了红包", 0).show();
        String userId = ChatManager.Instance().getUserId();
        if(message.message.sender.equals(userId)){
            Toast.makeText(this.fragment.getContext(), "点击了红包", 0).show();
            return ;
        }
        messageViewModel.sendRedClickMessage(message);

    }


}
