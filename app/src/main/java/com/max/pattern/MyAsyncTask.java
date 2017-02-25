package com.max.pattern;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

/**
 * AsyncTask ???
 * ???????????:  1 : ????????????   ????????? void
 * 2 : ??��????????????????????   ??????????????? void
 * 3 : ????????????????   ?????????????? void
 *
 * @auther MaxLiu
 * @time 2017/2/24
 */

public class MyAsyncTask extends AsyncTask<Integer, Integer, Boolean> {

    private ProgressBar progressBar;
    private int progress = 0;

    /**
     * ??????????????????
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //TODO ?��?????????????????? ProgressBar
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * ?��??????????????????????��???????
     * ???? {@link #publishProgress} ????? {@link #onProgressUpdate(Integer...)#onProgressUpdate(Object[])}
     *
     * @param params ????????
     * @return ??????????
     */
    @Override
    protected Boolean doInBackground(Integer... params) {
        // ????????????????
        int seconds = params[0];
        do {
            try {
                // ?????????????��???
                if (isCancelled()) return true;
                // ??????????
                progress += 5;
                // ?????? onProgressUpdate ?????????????
                publishProgress(progress);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (progress >= seconds);
        // ??????
        return true;
    }

    /**
     * ?��??????????????????
     *
     * @param values ????
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // ???????
        int progress = values[0];
        // ???????
        progressBar.setProgress(progress);
    }

    /**
     * ??????????????��???
     *
     * @param s ?????????????
     */
    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
        if (s) {
            // ?????????
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(Boolean s) {
        super.onCancelled(s);
    }
}