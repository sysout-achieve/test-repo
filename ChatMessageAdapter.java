package guiving.com.guiving_new.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import guiving.com.guiving_new.DTO.GuiverVO;
import guiving.com.guiving_new.R;
import guiving.com.guiving_new.RecyclerItem.ChatMessageRecyclerItem;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {
    private ArrayList<ChatMessageRecyclerItem> chatMessageRecyclerItems;
    Context context;

    GuiverVO guiverVO;

    private static final int ITEM_VIEW_TYPE_RECEIVE_MSG = 0;
    private static final int ITEM_VIEW_TYPE_SEND_MSG= 1;
    private static final int ITEM_VIEW_TYPE_RECEIVE_IMGS  = 2;
    private static final int ITEM_VIEW_TYPE_SEND_IMGS = 3;

    public ChatMessageAdapter(Context context, ArrayList<ChatMessageRecyclerItem> chatMessageRecyclerItems, GuiverVO guiverVO) {
        this.chatMessageRecyclerItems = chatMessageRecyclerItems;
        this.context = context;
        this.guiverVO = guiverVO;
    }


    class ViewHolder0 extends ChatMessageAdapter.ViewHolder {     //receive msg
        public ViewHolder0(View itemView) {
            super(itemView);
            img_receive_profile = itemView.findViewById(R.id.img_receive_profile);
            txt_receive_name = itemView.findViewById(R.id.txt_receive_name);
            txt_receive = itemView.findViewById(R.id.txt_receive);
            txt_receive_time = itemView.findViewById(R.id.txt_receive_time);
        }
    }

    class ViewHolder1 extends ChatMessageAdapter.ViewHolder {     //send msg
        public ViewHolder1(View itemView) {
            super(itemView);
            txt_send = itemView.findViewById(R.id.txt_send);
            txt_send_time = itemView.findViewById(R.id.txt_send_time);
        }
    }

    class ViewHolder2 extends ChatMessageAdapter.ViewHolder {     //receive img
        public ViewHolder2(View itemView) {
            super(itemView);
            img_receive_profile = itemView.findViewById(R.id.img_receive_profile);
            img_receive_image = itemView.findViewById(R.id.img_receive_image);
            txt_receive_name = itemView.findViewById(R.id.txt_receive_name);
            txt_receive_time = itemView.findViewById(R.id.txt_receive_time);
        }
    }

    class ViewHolder3 extends ChatMessageAdapter.ViewHolder {     //send img
        public ViewHolder3(View itemView) {
            super(itemView);
            img_send_image = itemView.findViewById(R.id.img_send_image);
            txt_send_time = itemView.findViewById(R.id.txt_send_time);
        }
    }

    @Override
    public ChatMessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_RECEIVE_MSG:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_receive_msg, parent, false);
                return new ViewHolder0(view);   //0번 뷰타입
            case ITEM_VIEW_TYPE_SEND_MSG:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_send_msg, parent, false);
                return new ViewHolder1(view1);   //1번 뷰타입
            case ITEM_VIEW_TYPE_RECEIVE_IMGS:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_receive_img, parent, false);
                return new ViewHolder2(view2);   //2번 뷰타입
            case ITEM_VIEW_TYPE_SEND_IMGS:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_send_img, parent, false);
                return new ViewHolder3(view3);   //3번 뷰타입
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageAdapter.ViewHolder holder, int position) {
        switch (chatMessageRecyclerItems.get(position).getViewType()){
            case ITEM_VIEW_TYPE_RECEIVE_MSG:
                if (guiverVO != null) {
                    Glide.with(context).load(guiverVO.getGuiverImgUrl()).apply(new RequestOptions().circleCrop()).into(holder.img_receive_profile);
                }
                holder.txt_receive_name.setText(chatMessageRecyclerItems.get(position).getReceiveName());
                holder.txt_receive.setText(chatMessageRecyclerItems.get(position).getReceiveMsg());
                holder.txt_receive_time.setText(chatMessageRecyclerItems.get(position).getReceiveTime());
                break;
            case ITEM_VIEW_TYPE_SEND_MSG:
                holder.txt_send.setText(chatMessageRecyclerItems.get(position).getSendMsg());
                holder.txt_send_time.setText(chatMessageRecyclerItems.get(position).getSendTime());
                break;
            case ITEM_VIEW_TYPE_RECEIVE_IMGS:
                if (guiverVO != null) {
                    Glide.with(context).load(guiverVO.getGuiverImgUrl()).apply(new RequestOptions().circleCrop()).into(holder.img_receive_profile);
                }
                holder.txt_receive_name.setText(chatMessageRecyclerItems.get(position).getReceiveName());
                Glide.with(context).load(chatMessageRecyclerItems.get(position).getReceiveUri()).into(holder.img_receive_image);
                holder.txt_receive_time.setText(chatMessageRecyclerItems.get(position).getReceiveTime());
                break;
            case ITEM_VIEW_TYPE_SEND_IMGS:
                Glide.with(context).load(chatMessageRecyclerItems.get(position).drawImageViewFromUri()).into(holder.img_send_image);
                holder.txt_send_time.setText(chatMessageRecyclerItems.get(position).getSendTime());
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return chatMessageRecyclerItems.get(position).getViewType();
    }


    public void addReceiveMSG(String Name, String msg, String time) {
        ChatMessageRecyclerItem item = new ChatMessageRecyclerItem();

        item.setViewType(ITEM_VIEW_TYPE_RECEIVE_MSG);
        item.setReceiveName(Name);
        item.setReceiveMsg(msg);
        item.setReceiveTime(time);

        chatMessageRecyclerItems.add(item);
    }

    public void addReceiveIMG(String Name, Uri uri, String time) {
        ChatMessageRecyclerItem item = new ChatMessageRecyclerItem();

        item.setViewType(ITEM_VIEW_TYPE_RECEIVE_IMGS);
        item.setReceiveName(Name);
        item.setReceiveUri(uri);
        item.setReceiveTime(time);

        chatMessageRecyclerItems.add(item);
    }

    public void addSendMSG(String msg, String time) {
        ChatMessageRecyclerItem item = new ChatMessageRecyclerItem();

        item.setViewType(ITEM_VIEW_TYPE_SEND_MSG);
        item.setSendMsg(msg);
        item.setSendTime(time);

        chatMessageRecyclerItems.add(item);
    }

    public void addSendIMG(Uri uri, String time) {
        ChatMessageRecyclerItem item = new ChatMessageRecyclerItem();

        item.setViewType(ITEM_VIEW_TYPE_SEND_IMGS);
        item.setSendUri(uri);
        item.setSendTime(time);

        chatMessageRecyclerItems.add(item);
    }

    @Override
    public int getItemCount() {
        return chatMessageRecyclerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_receive_profile;
        TextView txt_receive_name, txt_receive, txt_receive_time;
        TextView txt_send_time, txt_send;
        ImageView img_receive_image;
        ImageView img_send_image;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
//
// switch (chatMessageRecyclerItems.get(position).getViewType()){
//         case ITEM_VIEW_TYPE_RECEIVE_MSG:
//         convertView = inflater.inflate(R.layout.chat_receive_msg,
//         parent, false);
//         TextView txt_receive_name = convertView.findViewById(R.id.txt_receive_name);
//         TextView txt_receive = convertView.findViewById(R.id.txt_receive);
//         TextView txt_receive_time = convertView.findViewById(R.id.txt_receive_time);
//
//         txt_receive_name.setText(chatMessageRecyclerItems.get(position).getReceiveName());
//         txt_receive.setText(chatMessageRecyclerItems.get(position).getReceiveMsg());
//         txt_receive_time.setText(chatMessageRecyclerItems.get(position).getReceiveTime());
//         break;
//         case ITEM_VIEW_TYPE_SEND_MSG:
//         convertView = inflater.inflate(R.layout.chat_send_msg,
//         recyclerView, false);
//         TextView txt_send_time = convertView.findViewById(R.id.txt_send_time);
//         TextView txt_send = convertView.findViewById(R.id.txt_send);
//
//         txt_send.setText(chatMessageRecyclerItems.get(position).getSendMsg());
//         txt_send_time.setText(chatMessageRecyclerItems.get(position).getSendTime());
//         break;
//         case ITEM_VIEW_TYPE_RECEIVE_IMGS:
//         convertView = inflater.inflate(R.layout.chat_receive_img,
//         recyclerView, false);
//         TextView txt_receive_name2 = convertView.findViewById(R.id.txt_receive_name);
//         TextView img_receive_image = convertView.findViewById(R.id.img_receive_image);
//         TextView txt_receive_time2 = convertView.findViewById(R.id.txt_receive_time);
//
//         txt_receive_name2.setText(chatMessageRecyclerItems.get(position).getReceiveName());
//         txt_receive_time2.setText(chatMessageRecyclerItems.get(position).getReceiveTime());
//         if (chatMessageRecyclerItems.get(position).getReceiveUri() != null) {
//         Glide.with(context).load(chatMessageRecyclerItems.get(position).getReceiveUri()).into(holder.img_receive_image);
//         } else if(chatMessageRecyclerItems.get(position).getReceiveUri() != null){
//         Glide.with(context).load(chatMessageRecyclerItems.get(position).getReceiveUri()).into(holder.img_receive_image);
//         }
//         break;
//         case ITEM_VIEW_TYPE_SEND_IMGS:
//         convertView = inflater.inflate(R.layout.chat_send_img,
//         recyclerView, false);
//         break;