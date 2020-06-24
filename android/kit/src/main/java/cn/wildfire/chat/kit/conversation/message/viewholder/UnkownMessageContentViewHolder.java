package cn.wildfire.chat.kit.conversation.message.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cn.wildfire.chat.kit.annotation.EnableContextMenu;
import cn.wildfire.chat.kit.annotation.MessageContentType;
import cn.wildfire.chat.kit.annotation.ReceiveLayoutRes;
import cn.wildfire.chat.kit.annotation.SendLayoutRes;
import cn.wildfire.chat.kit.conversation.ConversationFragment;
import cn.wildfire.chat.kit.conversation.message.model.UiMessage;
import com.hskj.example.R2; import com.hskj.example.R;
import cn.wildfirechat.message.UnknownMessageContent;

@MessageContentType(UnknownMessageContent.class)
@SendLayoutRes(resId = R2.layout.conversation_item_unknown_send)
@ReceiveLayoutRes(resId = R2.layout.conversation_item_unknown_receive)
@EnableContextMenu
public class UnkownMessageContentViewHolder extends NormalMessageContentViewHolder {
    @BindView(R2.id.contentTextView)
    TextView contentTextView;

    public UnkownMessageContentViewHolder(ConversationFragment fragment, RecyclerView.Adapter adapter, View itemView) {
        super(fragment, adapter, itemView);
    }

    @Override
    public void onBind(UiMessage message) {
        contentTextView.setText("unknown message");
    }
}
