
package com.tincio.pharmaapp.presentation.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextLightGeneral extends AppCompatAutoCompleteTextView {

  final static String CUSTOM_FONT = "fonts/Calibri.ttf";

  public AutoCompleteTextLightGeneral(Context context) {
    super(context);
    if (!isInEditMode()) {
      init(context);
    }
  }

  public AutoCompleteTextLightGeneral(Context context, AttributeSet attrs) {
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
