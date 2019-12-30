package fit.tele.com.trainer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fit.tele.com.trainer.R;
import fit.tele.com.trainer.modelBean.CountryBean;
import fit.tele.com.trainer.modelBean.CustomerProfileBean;
import fit.tele.com.trainer.utils.CircleTransform;

public class RequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Context context;
    private ArrayList<CustomerProfileBean> list;
    private ClickListener clickListener;

    public RequestAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_request_list, parent, false);
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
        private RelativeLayout rl_accept, rl_deny;
        private TextView txt_user_name, txt_list_desc, txt_goal;
        private SwipeLayout swipeLayout;

        Header(View v) {
            super(v);
            swipeLayout = (SwipeLayout) v.findViewById(R.id.swipe);
            txt_user_name = (TextView) v.findViewById(R.id.txt_user_name);
            txt_list_desc = (TextView) v.findViewById(R.id.txt_list_desc);
            txt_goal = (TextView) v.findViewById(R.id.txt_goal);
            img_profile = (ImageView) v.findViewById(R.id.img_profile);
            rl_accept = (RelativeLayout) v.findViewById(R.id.rl_accept);
            rl_deny = (RelativeLayout) v.findViewById(R.id.rl_deny);

            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, swipeLayout.findViewById(R.id.bottom_wrapper));
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.bottom_wrapper1));

            rl_accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClickAccept(list.get(pos).getCustId());
                }
            });

            rl_deny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClickDeny(list.get(pos).getCustId());
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
        void onClickAccept(String cust_id);
        void onClickDeny(String cust_id);
    }
}
