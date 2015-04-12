package com.smartair.app.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartair.app.R;

@SuppressWarnings("unused")
public class ToolbarWidget extends Toolbar{

	LinearLayout container;
	TextView txtTitle;
	TextView txtSubtitle;

	private final static float defaultTitleSize    = 20.0f;
	private final static float defaultSubtitleSize = 15.0f;

	public ToolbarWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initTitle(context);
		setAttribute(context, attrs);
	}

	public ToolbarWidget(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initTitle(context);
		setAttribute(context, attrs);
	}

	private void initTitle(Context context){
		txtTitle   = new TextView(context);
		txtTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		txtSubtitle= new TextView(context);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, -2, 0, 0);
		txtSubtitle.setLayoutParams(layoutParams);
		txtSubtitle.setVisibility(View.GONE);

		container = new LinearLayout(context);
		LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		container.setOrientation(LinearLayout.VERTICAL);
		container.setLayoutParams(params);
		container.setGravity(Gravity.CENTER_HORIZONTAL);

		container.addView(txtTitle);
		container.addView(txtSubtitle);
		this.addView(container);
	}

	private void setAttribute(Context context, AttributeSet attrs){

		String title, subtitle;
		int titleColor, subtitleColor;
		int gravityValue;
		float titleTextSize, subtitleTextSize;

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.ToolbarWidget,
				0, 0);
		try {
			title            = a.getString(R.styleable.ToolbarWidget_titleText);
			titleColor       = a.getColor(R.styleable.ToolbarWidget_titleColor, context.getResources().getColor(android.R.color.black));
			gravityValue     = a.getInt(R.styleable.ToolbarWidget_gravityTitles, Gravity.CENTER);
			titleTextSize    = a.getDimension(R.styleable.ToolbarWidget_titleTextSize, defaultTitleSize);

			subtitle         = a.getString(R.styleable.ToolbarWidget_subtitleText);
			subtitleColor    = a.getColor(R.styleable.ToolbarWidget_subtitleColor, context.getResources().getColor(android.R.color.black));
			subtitleTextSize = a.getDimension(R.styleable.ToolbarWidget_subtitleTextSize, defaultSubtitleSize);

		} finally {
			a.recycle();
		}

		setAttributes(txtTitle, title, titleTextSize, titleColor);
		setAttributes(txtSubtitle, subtitle, subtitleTextSize, subtitleColor);

		boolean isNeedToVisible = (subtitle != null && !subtitle.isEmpty());
		setVisibleSubtitle(isNeedToVisible);

		LayoutParams params = (LayoutParams) container.getLayoutParams();
		params.gravity = Gravity.CENTER_HORIZONTAL;
		switch (gravityValue){
			case 0:
				params.gravity |= Gravity.LEFT;
				break;
			case 2:
				params.gravity |= Gravity.RIGHT;
				break;
			default:
				params.gravity |= Gravity.CENTER_VERTICAL;
		}
		params.gravity = gravityValue;
	}

	private void setAttributes(TextView view, String text, float textSize, int textColor){
		view.setText(text);
		view.setTextColor(textColor);
		view.setTextSize(textSize);
	}

    @Override
    public void setTitle(int resId) {
        txtTitle.setText(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        txtTitle.setText(title);
    }

	@Override
	public void setSubtitle (CharSequence subtitle){
		txtSubtitle.setText(subtitle);
	}

	public void setVisibleSubtitle(boolean visible){
		if (visible)
			txtSubtitle.setVisibility(View.VISIBLE);
		else
			txtSubtitle.setVisibility(View.GONE);
	}


    public void setTitleColor(@ColorRes int resColor) {
        txtTitle.setTextColor(resColor);
    }

    public void setSubtitleColor(@ColorRes int resColor) {
        txtSubtitle.setTextColor(resColor);
    }
}
