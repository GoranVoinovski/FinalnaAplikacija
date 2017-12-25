package com.mkdingo.goran.finalnaaplikacija.Listeners;

import com.mkdingo.goran.finalnaaplikacija.models.Restorani;

/**
 * Created by goran on 18.12.17.
 */

public interface RestoraniOnClickListener {

    public void onRestoranClick(Restorani restoran, int position);
    public void onRestoranLongClick(Restorani restoran, int position);

}
