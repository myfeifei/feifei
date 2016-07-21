package com.qiyi.mvptest.RecycleView;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kezhan_sx on 2016/7/20.
 */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 10000;
    private static final int BASE_ITEM_TYPE_FOOTER = 20000;
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mAdapter;

    public HeaderAndFooterWrapper(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(mHeaderViews.get(viewType) != null) {
                return ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            } else if(mFooterViews.get(viewType) != null) {
                return ViewHolder.createViewHolder(parent.getContext(), mFooterViews.get(viewType));
            } else {
                return mAdapter.onCreateViewHolder(parent, viewType);
            }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!isHeaderViewPos(position) && !isFooterViewPos(position)) {
            mAdapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mAdapter, recyclerView, new WrapperUtils.SpanSizeCallback()
        {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position)
            {
                int viewType = getItemViewType(position);
                if (mHeaderViews.get(viewType) != null)
                {
                    return layoutManager.getSpanCount();
                } else if (mFooterViews.get(viewType) != null)
                {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null)
                    return oldLookup.getSpanSize(position);
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position))
        {
            WrapperUtils.setFullSpan(holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if(isFooterViewPos(position)) {
            return mFooterViews.keyAt(position - getHeaderCount() - getRealCount());
        } else {
            return mAdapter.getItemViewType(position - getHeaderCount());
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mAdapter.getItemCount() + mFooterViews.size();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFooterView(View view) {
        mHeaderViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    private boolean isHeaderViewPos(int pos) {
        return pos < getHeaderCount();
    }

    private boolean isFooterViewPos(int pos) {
        return pos > getHeaderCount() + getRealCount();
    }

    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    public int getFooterCount() {
        return mFooterViews.size();
    }

    public int getRealCount() {
        return mAdapter.getItemCount();
    }
}
