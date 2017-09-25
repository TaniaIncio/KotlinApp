
package com.tincio.pharmaapp.presentation.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class EditTextLightGeneral extends AppCompatEditText {

  final static String CUSTOM_FONT = "fonts/Calibri.ttf";

  public EditTextLightGeneral(Context context) {
    super(context);
    if (!isInEditMode()) {
      init(context);
    }
  }

  public EditTextLightGeneral(Context context, AttributeSet attrs) {
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
