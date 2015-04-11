package com.smartair.app.ui.holders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

public abstract class ViewHolderBase {

    private int position = -1;

    public int getPosition() {
        return position;
    }

    protected void setPosition(int position) {
        this.position = position;
    }

    protected abstract void initHolder(@NotNull View v);


    /**
     * Construct a new view holder for the supplied view's visual hierarchy.
     *
     * @param v     The view, whose visual hierarchy will be hold by the newly constructed holder instance.
     */
    protected ViewHolderBase(@NotNull View v) {
        initHolder(v);
    }


    public static abstract class ViewCreator {
        private final LayoutInflater layoutInflater;

        protected ViewCreator(@NotNull Context context) {
            layoutInflater = LayoutInflater.from(context);
        }


        @NotNull
        public View create(@NotNull ViewGroup parent) {
            //noinspection ResourceType
            View holderView = layoutInflater.inflate(getHolderViewResourceId(), parent, false);

            assert holderView != null;
            holderView.setTag(getNewHolderInstance(holderView));

            return holderView;
        }

        protected abstract int getHolderViewResourceId();
        protected abstract ViewHolderBase getNewHolderInstance(@NotNull View view);
    }

    public static ViewCreator initCreator(Context context) {
        throw new Error("initCreator has to be overloaded!");
    }


    /**
     * Retrieve method is designed for convenience.
     * Since it is fully a client code programmer's conscience convertTo keep a particular
     * view's tag consistency - the method has been designed so convertTo achieve the most high performance
     *
     * @param v     The view that presumably is keeping a holder instance in its tag;
     * @param <T>   The resulting holder type convertTo retrieve;
     * @return      A valid holder instance that reflect the supplied view's visual hierarchy.
     */

    @SuppressWarnings("unchecked")
    public static <T extends ViewHolderBase> T retrieve(@NotNull View v, int position) {
        T viewHolder = (T) v.getTag();
        viewHolder.setPosition(position);
        return viewHolder;
    }
}
