
package com.tincio.pharmaapp.presentation.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TextViewBoldGeneral extends AppCompatTextView {

  final static String CUSTOM_FONT = "fonts/calibrib.ttf";

  public TextViewBoldGeneral(Context context) {
    super(context);
    if (!isInEditMode()) {
      init(context);
    }
  }

  public TextViewBoldGeneral(Context context, AttributeSet attrs) {
    super(context, attrs);
    if (!isInEditMode()) {
      init(context);
    }
  }


  private void init(Context context) {
    this.setTypeface(getTypeFace(context));
  }

  private Typeface getTypeFace(Context context) {
    return Typeface.createFromAsset(context.getAssets(), CUSTOM_FONT);
  }
}
