package com.example.vlayout;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.AnimRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.marqueeview.MarqueeView;

import java.util.List;

/**
 * 封装的ViewHolder方法，只是为了设置TextView和ImageView方便而已，
 * 可以根据自己的需求进行封装
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    public void setTextView(@IdRes int id,String content){
        View view = getView(id);
        if(view instanceof TextView){
            ((TextView) view).setText(content);
        }else{
            try {
                throw new Exception("这个id不是一个TextView视图");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setImageView(@IdRes int id,int imageResource){
        View view = getView(id);
        if(view instanceof ImageView){
            ((ImageView) view).setImageResource(imageResource);
        }else{
            try {
                throw new Exception("这个id不是一个ImageView视图");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMarqueeView(@IdRes int id, List<? extends CharSequence> list,
                               @AnimRes int inAnimId,@AnimRes int outAnimId){
        View view = getView(id);
        if(view instanceof MarqueeView){
            ((MarqueeView) view).startWithList(list,inAnimId,outAnimId);
        }else{
            try {
                throw new Exception("这个id不是一个ImageView视图");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMarqueeView(@IdRes int id, List<? extends CharSequence> list){
        View view = getView(id);
        if(view instanceof MarqueeView){
            ((MarqueeView) view).startWithList(list);
        }else{
            try {
                throw new Exception("这个id不是一个ImageView视图");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  <T extends View> T getView(@IdRes int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }
}
