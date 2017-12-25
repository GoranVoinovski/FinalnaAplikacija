package com.mkdingo.goran.finalnaaplikacija.Listeners;

import com.mkdingo.goran.finalnaaplikacija.models.Menu;

/**
 * Created by goran on 19.12.17.
 */

public interface OnImageClickListener {

    public void onImageClick(Menu meni, int position);
    public void onImageLongClick(Menu meni, int position);

}
