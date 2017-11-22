package com.tincio.pharmaapp.presentation.util.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import com.tincio.pharmaapp.R;

import static com.tincio.pharmaapp.presentation.util.MapaUtil.R;

/**
 * Created by juan on 22/11/2017.
 */


public class CustomClusterRenderer extends DefaultClusterRenderer<MyItemCluster> {

    Context context;
    private IconGenerator mClusterIconGenerator = null;

    public CustomClusterRenderer(Context context, GoogleMap map,
                             ClusterManager<MyItemCluster> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
        mClusterIconGenerator = new IconGenerator(context);
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItemCluster item,
                                               MarkerOptions markerOptions) {

        BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
        if(item.getOrder()==0)
            markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
        else
            markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);


        markerOptions.icon(markerDescriptor);
    }

    @Override
    protected void onClusterItemRendered(MyItemCluster clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        // Always render clusters.
        return cluster.getSize() > 1;
    }

  /*  @Override
    protected void onBeforeClusterRendered(Cluster<MyItemCluster> cluster, MarkerOptions markerOptions){

        final Drawable clusterIcon = context.getResources().getDrawable(com.tincio.pharmaapp.R.mipmap.ic_marker);
        clusterIcon.setColorFilter(context.getResources().getColor(android.R.color.holo_green_dark), PorterDuff.Mode.SRC_ATOP);

        mClusterIconGenerator.setBackground(clusterIcon);

        //modify padding for one or two digit numbers
        if (cluster.getSize() < 10) {
            mClusterIconGenerator.setContentPadding(40, 20, 0, 0);
        }
        else {
            mClusterIconGenerator.setContentPadding(30, 20, 0, 0);
        }

        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }*/
   /* private final IconGenerator mIconGenerator;
    private ShapeDrawable mColoredCircleBackground;
    private SparseArray<BitmapDescriptor> mIcons = new SparseArray();
    private final float mDensity;
    private Context mContext;

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<MyItemCluster> clusterManager) {
        super(context, map, clusterManager);
        this.mContext = context;
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.mIconGenerator = new IconGenerator(context);
        this.mIconGenerator.setContentView(this.makeSquareTextView(context));
     //   this.mIconGenerator.setTextAppearance(
       //         com.google.maps.android.R.style.ClusterIcon_TextAppearance);
        this.mIconGenerator.setBackground(this.makeClusterBackground());
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MyItemCluster> cluster,
                                           MarkerOptions markerOptions) {
        // Main color
        int clusterColor = mContext.getResources().getColor(R.color.colorPrimary);

        int bucket = this.getBucket(cluster);
        BitmapDescriptor descriptor = this.mIcons.get(bucket);
        if (descriptor == null) {
            this.mColoredCircleBackground.getPaint().setColor(clusterColor);
            descriptor = BitmapDescriptorFactory.fromBitmap(
                    this.mIconGenerator.makeIcon(this.getClusterText(bucket)));
            this.mIcons.put(bucket, descriptor);
        }

        markerOptions.icon(descriptor);
    }

    private SquareTextView makeSquareTextView(Context context) {
        SquareTextView squareTextView = new SquareTextView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        squareTextView.setLayoutParams(layoutParams);
        squareTextView.setId(com.google.maps.android.R.id.amu_text);
        int twelveDpi = (int) (12.0F * this.mDensity);
        squareTextView.setPadding(twelveDpi, twelveDpi, twelveDpi, twelveDpi);
        return squareTextView;
    }

    private LayerDrawable makeClusterBackground() {
        // Outline color
        int clusterOutlineColor = mContext.getResources().getColor(android.R.color.white);

        this.mColoredCircleBackground = new ShapeDrawable(new OvalShape());
        ShapeDrawable outline = new ShapeDrawable(new OvalShape());
        outline.getPaint().setColor(clusterOutlineColor);
        LayerDrawable background = new LayerDrawable(
                new Drawable[]{outline, this.mColoredCircleBackground});
        int strokeWidth = (int) (this.mDensity * 3.0F);
        background.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth);
        return background;
    }

    @Override
    protected int getColor(int clusterSize) {
        return Color.parseColor("#7CB342");
    }*/





    /*private final IconGenerator mIconGenerator;
    private ShapeDrawable mColoredCircleBackground;
    private SparseArray<BitmapDescriptor> mIcons = new SparseArray();
    private final float mDensity;
    private Context mContext;*/

   /* public CustomClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<GoogleMapMarker> clusterManager) {
        super(context, map, clusterManager);


        this.mContext = context;
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.mIconGenerator = new IconGenerator(context);
        this.mIconGenerator.setContentView(this.makeSquareTextView(context));
        this.mIconGenerator.setTextAppearance(
                com.google.maps.android.R.style.ClusterIcon_TextAppearance);
        this.mIconGenerator.setBackground(this.makeClusterBackground());
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<GoogleMapMarker> cluster,
                                           MarkerOptions markerOptions) {
        // Main color
        int clusterColor = mContext.getResources().getColor(R.color.colorPrimary);

        int bucket = this.getBucket(cluster);
        BitmapDescriptor descriptor = this.mIcons.get(bucket);
        if (descriptor == null) {
            this.mColoredCircleBackground.getPaint().setColor(clusterColor);
            descriptor = BitmapDescriptorFactory.fromBitmap(
                    this.mIconGenerator.makeIcon(this.getClusterText(bucket)));
            this.mIcons.put(bucket, descriptor);
        }

        markerOptions.icon(descriptor);
    }

    private SquareTextView makeSquareTextView(Context context) {
        SquareTextView squareTextView = new SquareTextView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        squareTextView.setLayoutParams(layoutParams);
        squareTextView.setId(com.google.maps.android.R.id.text);
        int twelveDpi = (int) (12.0F * this.mDensity);
        squareTextView.setPadding(twelveDpi, twelveDpi, twelveDpi, twelveDpi);
        return squareTextView;
    }

    private LayerDrawable makeClusterBackground() {
        // Outline color
        int clusterOutlineColor = mContext.getResources().getColor(android.R.color.white);

        this.mColoredCircleBackground = new ShapeDrawable(new OvalShape());
        ShapeDrawable outline = new ShapeDrawable(new OvalShape());
        outline.getPaint().setColor(clusterOutlineColor);
        LayerDrawable background = new LayerDrawable(
                new Drawable[]{outline, this.mColoredCircleBackground});
        int strokeWidth = (int) (this.mDensity * 3.0F);
        background.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth);
        return background;
    }*/
}
