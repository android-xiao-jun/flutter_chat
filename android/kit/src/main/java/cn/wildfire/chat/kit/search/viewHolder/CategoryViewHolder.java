package cn.wildfire.chat.kit.search.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.hskj.example.R2; import com.hskj.example.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.categoryTextView)
    TextView categoryTextView;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(String category) {
        if (TextUtils.isEmpty(category)) {
            categoryTextView.setVisibility(View.GONE);
            return;
        }
        categoryTextView.setVisibility(View.VISIBLE);
        categoryTextView.setText(category);
    }
}
