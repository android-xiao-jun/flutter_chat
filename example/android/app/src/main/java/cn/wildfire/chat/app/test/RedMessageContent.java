package cn.wildfire.chat.app.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;


import org.json.JSONException;
import org.json.JSONObject;

import cn.wildfirechat.message.Message;
import cn.wildfirechat.message.MessageContent;
import cn.wildfirechat.message.core.ContentTag;
import cn.wildfirechat.message.core.MessagePayload;
import cn.wildfirechat.message.core.PersistFlag;

/**
 * author        : Jun
 * time          : 2020年07月27日
 * description   : 聊天flutterdemo
 */
@ContentTag(
        type = 1002,
        flag = PersistFlag.Persist
)

public class RedMessageContent extends MessageContent {
     String title;
     String num;
     String id;

    public RedMessageContent() {
        this.title = "恭喜发财";
        this.num = "";
        this.id = "";
    }

    public RedMessageContent(String title, String num, String id) {
        this.title = title;
        this.num = num;
        this.id = id;
    }
    public MessagePayload encode() {
        MessagePayload payload = new MessagePayload();
        payload.searchableContent = this.title;
        try {
            JSONObject objWrite = new JSONObject();
            objWrite.put("num", num);
            objWrite.put("id", id);
            payload.content = objWrite.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return payload;
    }

    public void decode(MessagePayload payload) {
        this.title = payload.searchableContent;
        try {
            if (payload.content != null) {
                JSONObject jsonObject = new JSONObject(payload.content);
                this.num = jsonObject.optString("num");
                this.id = jsonObject.optString("id");
            }
        } catch (JSONException var3) {
            var3.printStackTrace();
        }
    }

    @Override
    public String digest(Message message) {
        return "红包";
    }

    public int describeContents() {
        return 0;
    }

    public static final Creator<RedMessageContent> CREATOR = new Creator<RedMessageContent>() {
        public RedMessageContent createFromParcel(Parcel source) {
            return new RedMessageContent(source);
        }

        public RedMessageContent[] newArray(int size) {
            return new RedMessageContent[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.num);
        dest.writeString(this.title);
        dest.writeString(this.id);
    }

    protected RedMessageContent(Parcel in) {
        super(in);
        this.num = in.readString();
        this.title = in.readString();
        this.id = in.readString();
    }
}
