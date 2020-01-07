/* By obtaining and/or copying this work, you agree that you have read,
 * understood and will comply with the following terms and conditions.
 *
 * Copyright (c) 2020 Mesibo
 * https://mesibo.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the terms and condition mentioned
 * on https://mesibo.com as well as following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions, the following disclaimer and links to documentation and
 * source code repository.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of Mesibo nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior
 * written permission.
 *
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Documentation
 * https://mesibo.com/documentation/
 *
 * Source Code Repository
 * https://github.com/mesibo/ui-modules-android

 */
package com.mesibo.mediapicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class GalleryPhotoGridAdapter extends RecyclerView.Adapter<GalleryPhotoGridAdapter.PhotosViewHolder> {
    private List<AlbumPhotosData> mPhotoList;
    private static Context mContext;


    public GalleryPhotoGridAdapter(Context context, List<AlbumPhotosData> photoList) {
        this.mPhotoList = photoList;
        this.mContext = context;

    }


    public static class PhotosViewHolder extends RecyclerView.ViewHolder {


        ImageView mAlbumFrontPicture;
        ProgressBar mPhotoProgress;
        ImageView mVideoLayer;

        public PhotosViewHolder(View v) {
            super(v);

            mAlbumFrontPicture = (ImageView) v.findViewById(R.id.photogrid_picture);
            mPhotoProgress = (ProgressBar) v.findViewById(R.id.pl_progress);
            mVideoLayer = (ImageView) v.findViewById(R.id.photogrid_video_layer);
            //mAlbumFrontPicture.getLayoutParams().height = mAlbumFrontPicture.getMeasuredWidth();


        }

    }

    @Override
    public PhotosViewHolder  onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_photogrid_rv_item, viewGroup, false);
        PhotosViewHolder tvh = new PhotosViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(PhotosViewHolder holder, int i) {
        AlbumPhotosData td = mPhotoList.get(i);
        holder.mVideoLayer.setVisibility(View.GONE);

        String s = td.getmSourceUrl();
        if(!(s.toLowerCase().startsWith("http://")||s.toLowerCase().startsWith("https://"))) {
            holder.mPhotoProgress.setVisibility(View.GONE);
            File playFile = new File(s);
            if(playFile.exists()) {
                if(SocialUtilities.isImageFile(s)) {
                    holder.mAlbumFrontPicture.setImageBitmap(BitmapFactory.decodeFile(s));
                }else if(SocialUtilities.isVideoFile(s)) {
                    Bitmap b = SocialUtilities.createThumbnailAtTime(s,0);
                    holder.mAlbumFrontPicture.setImageBitmap(b);
                    holder.mVideoLayer.setVisibility(View.VISIBLE);

                }
            }else {
                holder.mAlbumFrontPicture.setImageBitmap(null);
            }
            //holder.mAlbumFrontPicture.setImageBitmap(BitmapFactory.decodeFile(td.getmSourceUrl()));
        } else {

            if (!td.getmHasDownloadedGP()) {

                DownloadImage request = new DownloadImage();
                request.setAlbumData(td);
                request.setHandler(holder);
                request.execute(td.getmSourceUrl());

            } else {
                holder.mPhotoProgress.setVisibility(View.GONE);
                holder.mAlbumFrontPicture.setImageBitmap(td.getmGridPicture());
            }
        }


    }

    
    public void addItem(AlbumPhotosData dataObj, int index) {
        mPhotoList.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mPhotoList.remove(index);
        notifyItemRemoved(index);
    }


    @Override
    public int getItemCount() {
        return (mPhotoList.size());
    }

    // DownloadImage AsyncTask
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {


        PhotosViewHolder handler;
        AlbumPhotosData albumData;
        public void setHandler (PhotosViewHolder handler1) {
            this.handler = handler1;
        }

        public void setAlbumData (AlbumPhotosData ad1) {this.albumData = ad1;}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            /*
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Download Image Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show(); */
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];
            Bitmap bitmap = null;
            String s = imageURL.trim().toLowerCase();


            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            handler.mAlbumFrontPicture.setImageBitmap(result);
            handler.mPhotoProgress.setVisibility(View.GONE);

            albumData.setmHasDownloadedGP(true);
            albumData.setmGridPicture(result);


        }
    }




}


