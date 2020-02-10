package fit.tele.com.trainer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.modelBean.CustomerProfileBean;
import fit.tele.com.trainer.utils.CircleTransform;

public class CustomersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Context context;
    private ArrayList<CustomerProfileBean> list;
    private ClickListener clickListener;

    public CustomersAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer_list, parent, false);
        return new Header(view);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Header) {
            ((Header) holder).bindData(position);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addAllList(ArrayList<CustomerProfileBean> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
//            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    private class Header extends RecyclerView.ViewHolder {
        private int pos;
        private ImageView img_profile;
        private TextView txt_user_name, txt_list_desc, txt_program, txt_goal;
        private ImageView img_video, img_msg;

        Header(View v) {
            super(v);
            txt_user_name = (TextView) v.findViewById(R.id.txt_user_name);
            txt_list_desc = (TextView) v.findViewById(R.id.txt_list_desc);
            txt_program = (TextView) v.findViewById(R.id.txt_program);
            txt_goal = (TextView) v.findViewById(R.id.txt_goal);
            img_profile = (ImageView) v.findViewById(R.id.img_profile);
            img_video = (ImageView) v.findViewById(R.id.img_video);
            img_msg = (ImageView) v.findViewById(R.id.img_msg);

            img_msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClickMsg(list.get(pos));
                }
            });

            img_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClickVideo(list.get(pos).getId());
                }
            });

            txt_user_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(list.get(pos).getId());
                }
            });

            img_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(list.get(pos).getId());
                }
            });
        }

        public void bindData(int position) {
            this.pos = position;
            if (list != null && pos >= 0 && pos < list.size() && list.get(pos) != null) {
                if(list.get(pos).getProfilePicUrl() != null && !TextUtils.isEmpty(list.get(pos).getProfilePicUrl()) && list.get(pos).getProfilePicUrl() != "") {
                    Picasso.with(context)
                            .load(list.get(pos).getProfilePicUrl())
                            .error(R.drawable.user_placeholder)
                            .placeholder(R.drawable.user_placeholder)
                            .transform(new CircleTransform())
                            .into(img_profile);
                }
                else
                    img_profile.setImageResource(R.drawable.user_placeholder);

                if(list.get(pos).getName() != null && !TextUtils.isEmpty(list.get(pos).getName()))
                    txt_user_name.setText(list.get(pos).getName()+" "+list.get(pos).getlName());
                if(list.get(pos).getAddress() != null && !TextUtils.isEmpty(list.get(pos).getAddress()))
                    txt_list_desc.setText(list.get(pos).getAddress());
            }
        }
    }

    public interface ClickListener {
        void onClickMsg(CustomerProfileBean customer_profile);
        void onClickVideo(String cust_id);
        void onClick(String cust_id);
    }
}
